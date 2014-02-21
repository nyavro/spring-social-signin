package net.nyavro.spring.social.signinmvc.services;

import net.nyavro.spring.social.signinmvc.model.ProviderIdMapping;
import net.nyavro.spring.social.signinmvc.model.User;
import net.nyavro.spring.social.signinmvc.model.dto.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by eny on 2/21/14.
 */
@Service
public class AuthenticationManagerImpl implements AuthenticationManager {

    @Autowired
    private UserService userService;

    @Override
    public AuthResult authenticate(final Auth auth) {
        final User user = userService.findByProviderIdMappings(new ProviderIdMapping(auth.getId(), auth.getSignInProvider()));
        if(user!=null) {
            return AuthResult.GRANTED;
        }
        return AuthResult.REGISTER;
    }
}
