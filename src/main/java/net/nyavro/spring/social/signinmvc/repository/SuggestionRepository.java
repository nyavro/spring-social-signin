package net.nyavro.spring.social.signinmvc.repository;

import com.nyavro.dienstleustigen.model.Suggestion;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SuggestionRepository extends PagingAndSortingRepository<Suggestion, ObjectId> {

    Suggestion findByCreator(String id);

    Page<Suggestion> findByCategory(Pageable pageable, String category);
}
