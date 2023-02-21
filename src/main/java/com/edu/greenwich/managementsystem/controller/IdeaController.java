package com.edu.greenwich.managementsystem.controller;

import com.edu.greenwich.managementsystem.Repository.CommentRepository;
import com.edu.greenwich.managementsystem.Repository.IdeaRepository;
import com.edu.greenwich.managementsystem.dto.request.IdeaRequest;
import com.edu.greenwich.managementsystem.dto.response.ResponseMessage;
import com.edu.greenwich.managementsystem.model.Comment;
import com.edu.greenwich.managementsystem.model.Idea;
import com.edu.greenwich.managementsystem.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8002", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/idea")
public class IdeaController {
    @Autowired
    IdeaRepository ideaRepository;
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    StorageService storageService;

    @GetMapping("/all")
    public List<Idea> getAll() {
        return ideaRepository.findAll();
    }

    @GetMapping("/get")
    public Optional<Idea> findById(@RequestParam long id) {
        return ideaRepository.findById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseMessage> save(@ModelAttribute IdeaRequest ideaRequest) {
        String fileName ="";
        try {
            Idea idea = Idea.builder().brief(ideaRequest.getBrief()).categoryId(ideaRequest.getCategoryId())
                    .topicId(ideaRequest.getTopicId()).content(ideaRequest.getContent()).author(ideaRequest.getAuthor()).id(ideaRequest.getId()).build();
            MultipartFile file = ideaRequest.getFile();
            fileName = storageService.save(file);
            idea.setFileLocation(fileName);
            ideaRepository.save(idea);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Error when upload file, please try again!"));
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseMessage(ideaRequest.toString()));
    }

    @GetMapping("/delete")
    public void delete(@RequestParam long id) {
        ideaRepository.deleteById(id);
    }

    @GetMapping("/getAllComment")
    public List<Comment> getAllComment(@RequestParam long ideaId){
        return commentRepository.findAllByIdeaId(ideaId);
    }

}
