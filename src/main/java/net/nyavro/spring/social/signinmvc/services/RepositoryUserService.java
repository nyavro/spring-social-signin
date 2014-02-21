package net.nyavro.spring.social.signinmvc.services;

import net.nyavro.spring.social.signinmvc.model.Contact;
import net.nyavro.spring.social.signinmvc.model.ProviderIdMapping;
import net.nyavro.spring.social.signinmvc.repository.UserRepository;
import net.nyavro.spring.social.signinmvc.model.dto.RegistrationForm;
import net.nyavro.spring.social.signinmvc.model.User;
import net.nyavro.spring.social.signinmvc.services.DuplicateEmailException;
import net.nyavro.spring.social.signinmvc.utils.Converter;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RepositoryUserService implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryUserService.class);

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    @Transactional
    public User create(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public Contact getContact(String creator) {
        return new Converter().convert(repository.findOne(new ObjectId(creator)));
    }

    @Override
    public User findByProviderIdMappings(final ProviderIdMapping mapping) {
        return repository.findByProviderIdMappings(mapping);
    }
}
