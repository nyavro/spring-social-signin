package net.nyavro.spring.social.signinmvc.services;

import com.nyavro.dienstleustigen.model.Suggestion;
import net.nyavro.spring.social.signinmvc.repository.SuggestionRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuggestionServiceImpl implements SuggestionService {

    @Autowired
    private SuggestionRepository repository;

    @Override
    public List<Suggestion> findAll(int page, int batchSize) {
        return repository.findAll(
            new PageRequest(
                page, batchSize, new Sort(Sort.Direction.DESC, "published")
            )
        ).getContent();
    }

    @Override
    public Suggestion findById(String id) {
        return repository.findOne(new ObjectId(id));
    }

    @Override
    public Suggestion findByCreator(String id) {
        return repository.findByCreator(id);
    }

    @Override
    public Suggestion save(Suggestion suggestion) {
        return repository.save(suggestion);
    }

    @Override
    public List<Suggestion> findByCategory(String category, int page, int batchSize) {
        return repository.findByCategory(
            new PageRequest(
                page, batchSize, new Sort(Sort.Direction.DESC, "published")
            ),
            category
        ).getContent();
    }
}
