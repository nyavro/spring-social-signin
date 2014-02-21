package net.nyavro.spring.social.signinmvc.services;

import net.nyavro.spring.social.signinmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Service;

/**
 * Created by eny on 2/21/14.
 */
@Service
public class AuthenticationManagerImpl implements AuthenticationManager {

    @Autowired
    private RepositoryUserService repositoryUserService;

    @Override
    public AuthResult authenticate(final User user) {
//        repositoryUserService.findByProviderIdMappings(user.)
        return AuthResult.GRANTED;
    }
}
