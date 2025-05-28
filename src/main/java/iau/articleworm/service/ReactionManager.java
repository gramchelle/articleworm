package iau.articleworm.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iau.articleworm.service.ReactionService;
import iau.articleworm.repository.ReactionRepository;
import iau.articleworm.model.Reaction;

@Service
public class ReactionManager implements ReactionService {

    private ReactionDao reactionDao; //final keyword gerekli değil mi? -> Hayır, çünkü bu sınıfın constructor'ında bir değer atanıyor.

    @Autowired
    public ReactionManager(ReactionDao reactionDao) {
        this.reactionDao = reactionDao;
    }

    @Override
    public List<Reaction> getAllReactions() {
        return reactionDao.findAll();
    }


}
