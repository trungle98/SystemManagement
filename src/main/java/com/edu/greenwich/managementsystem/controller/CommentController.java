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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8002", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    TopicRepository topicRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    UserRepository userRepository;
    @GetMapping("/all")
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseMessage> save(@RequestBody Comment comment) {
        if(checkMeetClosureDateByTopicId(comment.getIdeaId())) {
            Date now = new Date();
            comment.setCreatedDate(now);
            Comment save = commentRepository.save(comment);
            sendEmail(comment.getIdeaId());
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
    private void sendEmail(long ideaId) {
        Topic topic = topicRepository.getByIdeaId(ideaId);
        Optional<User> user = userRepository.findByUserId((long) topic.getAuthorId());
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(user.get().getEmail());
        emailDetails.setSubject("Your idea have new comment");
        emailDetails.setMsgBody("ideaId with ID: "+ideaId+" have new comment");
        System.out.println(emailService.sendSimpleMail(emailDetails));
    }
}
