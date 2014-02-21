package net.nyavro.spring.social.signinmvc.services;

import net.nyavro.spring.social.signinmvc.model.Contact;
import net.nyavro.spring.social.signinmvc.model.ProviderIdMapping;
import net.nyavro.spring.social.signinmvc.model.dto.RegistrationForm;
import net.nyavro.spring.social.signinmvc.model.User;
import net.nyavro.spring.social.signinmvc.services.DuplicateEmailException;

public interface UserService {

    User create(User user) throws DuplicateEmailException;

    Contact getContact(String creator);

    User findByProviderIdMappings(ProviderIdMapping mapping);
}
