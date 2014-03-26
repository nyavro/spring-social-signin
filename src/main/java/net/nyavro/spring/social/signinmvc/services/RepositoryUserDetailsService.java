package net.nyavro.spring.social.signinmvc.services;

import net.nyavro.spring.social.signinmvc.model.dto.CustomUserDetails;
import net.nyavro.spring.social.signinmvc.repository.UserRepository;
import net.nyavro.spring.social.signinmvc.model.User;
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
        final User user = repository.findByLogin(username);
        LOGGER.debug("Found user: {}", user);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + username);
        }
        return CustomUserDetails.getBuilder().user(user).build();
    }
}
