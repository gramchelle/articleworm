package iau.articleworm.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import iau.articleworm.business.abstracts.CommentService;
import iau.articleworm.entities.concretes.Comment;

@RestController
@RequestMapping("/api/comments")
public class CommentsController {

    private CommentService commentService;

    @Autowired
    public CommentsController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/getall")
    public List<Comment> getAll() {
        return this.commentService.getAllComments();
    }

}
