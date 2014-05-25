package net.nyavro.spring.social.signinmvc.services;

import com.nyavro.dienstleustigen.model.Suggestion;

import java.util.List;

public interface SuggestionService {

    List<Suggestion> findAll(int page, int batchSize);

    Suggestion findById(String id);

    Suggestion findByCreator(String id);

    Suggestion save(Suggestion suggestion);

    List<Suggestion> findByCategory(String id, int page, int batchSize);
}
