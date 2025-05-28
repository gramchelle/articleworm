package iau.articleworm.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iau.articleworm.service.CommentService;
import iau.articleworm.repository.CommentRepository;
import iau.articleworm.model.Comment;

@Service
public class CommentManager{

    private CommentRepository commentDao;

    @Autowired
    public CommentManager(CommentRepository commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public List<Comment> getAllComments(){
        return this.commentDao.findAll();
    }


}
