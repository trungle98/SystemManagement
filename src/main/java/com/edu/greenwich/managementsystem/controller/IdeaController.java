package com.edu.greenwich.managementsystem.controller;

import com.edu.greenwich.managementsystem.Repository.CommentRepository;
import com.edu.greenwich.managementsystem.Repository.IdeaRepository;
import com.edu.greenwich.managementsystem.Repository.TopicRepository;
import com.edu.greenwich.managementsystem.Repository.impl.CustomIdeaRepoImpl;
import com.edu.greenwich.managementsystem.dto.request.IdeaRequest;
import com.edu.greenwich.managementsystem.dto.response.ReactionWithIdeaIdResponse;
import com.edu.greenwich.managementsystem.dto.response.ResponseMessage;
import com.edu.greenwich.managementsystem.model.Comment;
import com.edu.greenwich.managementsystem.model.Idea;
import com.edu.greenwich.managementsystem.model.Topic;
import com.edu.greenwich.managementsystem.service.ExcelExporterService;
import com.edu.greenwich.managementsystem.service.StorageService;
import com.edu.greenwich.managementsystem.service.impl.DetectorService;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8002", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/idea")
@PreAuthorize("hasRole('ROLE_STAFF') or hasRole('ROLE_DEPT') or hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
public class IdeaController {
    private static Logger logger = LoggerFactory.getLogger(IdeaController.class);
    @Autowired
    IdeaRepository ideaRepository;
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    private CustomIdeaRepoImpl customIdeaRepo;

    @Autowired
    StorageService storageService;

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    ExcelExporterService excelExporterService;


    @GetMapping("/all")
    public List<Idea> getAll() {
        return ideaRepository.findAll();
    }

    @GetMapping("/get")
    public Optional<Idea> findById(@RequestParam int id) {
        Optional<Idea>  idea = ideaRepository.findById(id);
        if (idea.isPresent()) {
            int views = idea.get().getViews();
            views += 1;
            idea.get().setViews(views);
            ideaRepository.save(idea.get());
        }
        return idea;
    }

    @GetMapping("/getByTopicId")
    public Optional<List<ReactionWithIdeaIdResponse>> findByTopicId(@RequestParam int topicId) {
        return Optional.ofNullable(customIdeaRepo.getReactionByTopicId(topicId));
    }
    /*
     * need to check if the topic is closure
     */
    @PostMapping("/save")
    public ResponseEntity<ResponseMessage> save(@ModelAttribute IdeaRequest ideaRequest, @RequestParam int topicId) {
        logger.info("receive save request");
        //check if submission is meet closure date
        if (!checkMeetClosureDateByTopicId(topicId)) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Topic is expired"));
        }
        String fileName ="";
        Idea savedIdea;
        try {
            Idea idea = Idea.builder().brief(ideaRequest.getBrief()).categoryId(ideaRequest.getCategoryId())
                    .topicId(ideaRequest.getTopicId()).content(ideaRequest.getContent()).author(ideaRequest.getAuthor()).id(ideaRequest.getId()).build();
            if(!ideaRequest.getFile().isEmpty()){
                MultipartFile file = ideaRequest.getFile();
                fileName = storageService.save(file, ideaRequest.getTopicId());
            }
            idea.setFileLocation(fileName);

            savedIdea = ideaRepository.save(idea);
        }catch (Exception e){
            logger.error("save error caused ={}",e);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).contentType(MediaType.APPLICATION_JSON).body(new ResponseMessage("Error when upload file, please try again!"));
        }
        logger.info("end request");
        return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON).body(new ResponseMessage(savedIdea));
    }


    @DeleteMapping("/delete")
    public void delete(@RequestParam int id) {
        ideaRepository.deleteById(id);
    }

    @GetMapping("/getAllComment")
    public List<Comment> getAllComment(@RequestParam long ideaId){
        return commentRepository.findAllByIdeaId(ideaId);
    }

    @GetMapping("/files")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@RequestParam String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    private boolean checkMeetClosureDateByTopicId(int topicId) {
        boolean isMeet = false;
        //get topic by id
        Topic topic = topicRepository.getById(topicId);
        Date closureDate = topic.getClosure();
        Date now = new Date();
        if(closureDate.after(now)){
            return true;
        }
        return isMeet;
    }
    @GetMapping("/exportExcel")
    public ResponseEntity<ByteArrayResource> exportToExcelByTopicId(@RequestParam int topicId) throws IOException {
        Optional<List<ReactionWithIdeaIdResponse>> ideas = Optional.ofNullable(customIdeaRepo.getReactionByTopicId(topicId));
        if (ideas.isPresent()){
            ByteArrayInputStream inputStream = excelExporterService.export(ideas.get());
            ByteArrayResource resource = new ByteArrayResource(IOUtils.toByteArray(inputStream));
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+topicId+".xlsx")
                    .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                    .contentLength(resource.contentLength())
                    .body(resource);
        }
        return null;
    }
}
