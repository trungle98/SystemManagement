package com.edu.greenwich.managementsystem.controller;

import com.edu.greenwich.managementsystem.Repository.ReactionRepository;
import com.edu.greenwich.managementsystem.dto.response.ReactionWithIdeaIdResponse;
import com.edu.greenwich.managementsystem.model.Reaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8002", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/reaction")
@PreAuthorize("hasRole('ROLE_STAFF') or hasRole('ROLE_DEPT') or hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
public class ReactionController {
    @Autowired
    ReactionRepository reactionRepository;

    @PostMapping("/save")
    public void save(@RequestBody Reaction reaction) {
        boolean isLike = reaction.isLike();
        String ideaId = reaction.getIdeaId();
        String userId = reaction.getUserId();
        reactionRepository.saveReaction(isLike, ideaId, userId);
    }

    @GetMapping("/getByTopicId")
    public List<ReactionWithIdeaIdResponse> getReactionByTopicId(@RequestParam int topicId) {
       return reactionRepository.getReactionByTopicId(topicId);
    }

}
