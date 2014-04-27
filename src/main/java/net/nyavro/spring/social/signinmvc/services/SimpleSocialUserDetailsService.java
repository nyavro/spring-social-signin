package net.nyavro.spring.social.signinmvc.services;

import net.nyavro.spring.social.signinmvc.model.ProviderIdMapping;
import net.nyavro.spring.social.signinmvc.model.SocialMediaService;
import net.nyavro.spring.social.signinmvc.model.User;
import net.nyavro.spring.social.signinmvc.model.dto.CustomUserDetails;
import net.nyavro.spring.social.signinmvc.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

public class SimpleSocialUserDetailsService implements SocialUserDetailsService, UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleSocialUserDetailsService.class);

    @Autowired
    private UserRepository repository;

    @Override
    public SocialUserDetails loadUserByUserId(final String userId) throws UsernameNotFoundException, DataAccessException {
        final User user = findByCompositeId(userId);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with user id: " + userId);
        }
        return CustomUserDetails.getBuilder().user(user).build();
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final User user = findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + username);
        }
        return CustomUserDetails.getBuilder().user(user).build();
    }

    private User findByCompositeId(final String compositeId) {
        LOGGER.debug("Searching user by compositeId: {}", compositeId);
        final String[] components = compositeId.split(":");
        return repository.findByProviderIdMappings(
            new ProviderIdMapping(components[1], SocialMediaService.resolve(components[0]))
        );
    }

    private User findByLogin(final String login) {
        LOGGER.debug("Searching user by login: {}", login);
        return repository.findByLogin(login);
    }
}
