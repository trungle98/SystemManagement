package com.edu.greenwich.managementsystem.controller;

import com.edu.greenwich.managementsystem.Repository.ReactionRepository;
import com.edu.greenwich.managementsystem.model.Reaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8002", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/reaction")
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

}
