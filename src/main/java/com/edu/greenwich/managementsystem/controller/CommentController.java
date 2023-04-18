package com.edu.greenwich.managementsystem.controller;

import com.edu.greenwich.managementsystem.Repository.CommentRepository;
import com.edu.greenwich.managementsystem.Repository.TopicRepository;
import com.edu.greenwich.managementsystem.Repository.UserRepository;
import com.edu.greenwich.managementsystem.dto.response.ResponseMessage;
import com.edu.greenwich.managementsystem.model.Comment;
import com.edu.greenwich.managementsystem.model.EmailDetails;
import com.edu.greenwich.managementsystem.model.Topic;
import com.edu.greenwich.managementsystem.model.User;
import com.edu.greenwich.managementsystem.service.EmailService;
import com.edu.greenwich.managementsystem.service.impl.DetectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8002", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/comment")
@PreAuthorize("hasRole('ROLE_STAFF') or hasRole('ROLE_DEPT') or hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
public class CommentController {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    TopicRepository topicRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DetectorService detectorService;
    @GetMapping("/all")
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseMessage> save(@RequestBody Comment comment) {
        if(checkMeetClosureDateByTopicId(comment.getIdeaId())) {
            Date now = new Date();
            comment.setCreatedDate(now);
//            set bullying type
            String isContentBullying = detectorService.detectComment(comment.getCommentContent());
            comment.setBullying(isContentBullying);
            Comment save = commentRepository.save(comment);
            //send warning email to current user email if this comment is cyberbullying, else send notification to idea owner
            if (isContentBullying.equals("is_cyberbullying")) {
                sendEmail(comment.getIdeaId(), "is_cyberbullying");
            } else {
                //send notification to idea owner
                sendEmail(comment.getIdeaId(), "not_cyberbullying");
            }


            return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON).body(new ResponseMessage(save));
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).contentType(MediaType.APPLICATION_JSON).body(new ResponseMessage("Topic is expired"));
    }

    @GetMapping("/delete")
    public void delete(@RequestParam int id) {
        commentRepository.deleteById(id);
    }

    private boolean checkMeetClosureDateByTopicId(long ideaId) {
        boolean isMeet = false;
        //get topic by id
        Topic topic = topicRepository.getByIdeaId(ideaId);
        Date closureDate = topic.getFinalClosure();
        Date now = new Date();
        if(closureDate.after(now)){
            return true;
        }
        return isMeet;
    }
    private void sendEmail(long ideaId, String type) {
        String subject = "Your idea have new comment";
        String msgBody = "ideaId with ID: "+ideaId+" have new comment";
        if (type.equals("is_cyberbullying")){
            subject = "Your comment may be is cyber-bullying comment";
            msgBody = "your comment at idea id: "+ideaId+" is detected as cyber-bullying, it will not appeared at idea page. But administrator can monitor and see all of them, feel free to complain if you think it's not any type of cyber-bullying";
        }
        Topic topic = topicRepository.getByIdeaId(ideaId);
        Optional<User> user = userRepository.findByUserId((long) topic.getAuthorId());
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(user.get().getEmail());
        emailDetails.setSubject(subject);
        emailDetails.setMsgBody(msgBody);
        System.out.println(emailService.sendSimpleMail(emailDetails));
    }
}
