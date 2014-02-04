package net.nyavro.spring.social.signinmvc.services;

import net.nyavro.spring.social.signinmvc.model.Presentation;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PresentationService {

    List<Presentation> findAll(int page, int batchSize);

    Presentation findById(String id);

    Presentation findByCreator(String id);

    Presentation save(Presentation presentation);

    List<Presentation> findByCategory(String id, int page, int batchSize);
}
