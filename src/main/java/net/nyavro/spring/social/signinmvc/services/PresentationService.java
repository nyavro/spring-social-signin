package net.nyavro.spring.social.signinmvc.services;

import com.nyavro.dienstleustigen.model.Presentation;

import java.util.List;

public interface PresentationService {

    List<Presentation> findAll(int page, int batchSize);

    Presentation findById(String id);

    Presentation findByCreator(String id);

    Presentation save(Presentation presentation);

    List<Presentation> findByCategory(String id, int page, int batchSize);
}
