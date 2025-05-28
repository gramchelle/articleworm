package iau.articleworm.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iau.articleworm.business.abstracts.CommentService;
import iau.articleworm.entities.abstracts.CommentDao;
import iau.articleworm.entities.concretes.Comment;

@Service
public class CommentManager implements CommentService {

    private CommentDao commentDao;

    @Autowired
    public CommentManager(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public List<Comment> getAllComments(){
        return this.commentDao.findAll();
    }


}
