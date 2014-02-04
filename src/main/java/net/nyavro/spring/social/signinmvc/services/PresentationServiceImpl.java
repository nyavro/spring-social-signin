package net.nyavro.spring.social.signinmvc.services;

import net.nyavro.spring.social.signinmvc.model.Presentation;
import net.nyavro.spring.social.signinmvc.repository.PresentationRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PresentationServiceImpl implements PresentationService {

    @Autowired
    private PresentationRepository repository;

    @Override
    public List<Presentation> findAll(int page, int batchSize) {
        return repository.findAll(
            new PageRequest(
                page, batchSize, new Sort(Sort.Direction.DESC, "published")
            )
        ).getContent();
    }

    @Override
    public Presentation findById(String id) {
        return repository.findOne(new ObjectId(id));
    }

    @Override
    public Presentation findByCreator(String id) {
        return repository.findByCreator(id);
    }

    @Override
    public Presentation save(Presentation presentation) {
        return repository.save(presentation);
    }

    @Override
    public List<Presentation> findByCategory(String category, int page, int batchSize) {
        return repository.findByCategory(
            new PageRequest(
                page, batchSize, new Sort(Sort.Direction.DESC, "published")
            ),
            category
        ).getContent();
    }
}
