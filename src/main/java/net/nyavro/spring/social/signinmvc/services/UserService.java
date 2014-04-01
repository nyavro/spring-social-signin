package net.nyavro.spring.social.signinmvc.services;

import net.nyavro.spring.social.signinmvc.model.dto.Contact;
import net.nyavro.spring.social.signinmvc.model.ProviderIdMapping;
import net.nyavro.spring.social.signinmvc.model.User;

public interface UserService {

    User create(User user) throws DuplicateEmailException;

    Contact getContact(String creator);

    User findByProviderIdMappings(ProviderIdMapping mapping);
}
