package net.nyavro.spring.social.signinmvc.security.service;

import net.nyavro.spring.social.signinmvc.repository.UserRepository;
import net.nyavro.spring.social.signinmvc.security.dto.ExampleUserDetails;
import net.nyavro.spring.social.signinmvc.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class RepositoryUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryUserDetailsService.class);

    private UserRepository repository;

    @Autowired
    public RepositoryUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.debug("Loading user by username: {}", username);
        User user = repository.findByEmail(username);
        LOGGER.debug("Found user: {}", user);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + username);
        }
        return ExampleUserDetails.getBuilder()
                .firstName(user.getFirstName())
                .id(user.getId())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .role(user.getRole())
                .socialSignInProvider(user.getSignInProvider())
                .username(user.getEmail())
                .build();
    }
}
