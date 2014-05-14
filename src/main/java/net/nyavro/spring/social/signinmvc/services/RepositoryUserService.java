package net.nyavro.spring.social.signinmvc.services;

import net.nyavro.spring.social.signinmvc.model.dto.Contact;
import net.nyavro.spring.social.signinmvc.model.ProviderIdMapping;
import net.nyavro.spring.social.signinmvc.repository.UserRepository;
import net.nyavro.spring.social.signinmvc.model.User;
import net.nyavro.spring.social.signinmvc.utils.Converter;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class RepositoryUserService implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryUserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setLastSeen(new Date());
        return userRepository.save(user);
    }

    @Override
    public Contact getContact(String creator) {
        return new Converter().convert(userRepository.findOne(new ObjectId(creator)));
    }

    @Override
    public User findByProviderIdMappings(final ProviderIdMapping mapping) {
        return userRepository.findByProviderIdMappings(mapping);
    }
}
