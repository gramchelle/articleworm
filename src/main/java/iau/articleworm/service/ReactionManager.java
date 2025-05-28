package iau.articleworm.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iau.articleworm.business.abstracts.ReactionService;
import iau.articleworm.entities.abstracts.ReactionDao;
import iau.articleworm.entities.concretes.Reaction;

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
