package net.nyavro.spring.social.signinmvc.repository;

import com.nyavro.dienstleustigen.model.Presentation;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PresentationRepository extends PagingAndSortingRepository<Presentation, ObjectId> {

    Presentation findByCreator(String id);

    Page<Presentation> findByCategory(Pageable pageable, String category);
}
