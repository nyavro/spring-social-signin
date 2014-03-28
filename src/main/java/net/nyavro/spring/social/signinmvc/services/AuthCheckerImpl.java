package net.nyavro.spring.social.signinmvc.services;

import net.nyavro.spring.social.signinmvc.model.User;
import org.springframework.stereotype.Service;

@Service
public class AuthCheckerImpl implements AuthChecker {

    @Override
    public AuthResult check(User user, String password) {
        return null;
    }
}
