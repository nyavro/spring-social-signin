package net.nyavro.spring.social.signinmvc.services;

import net.nyavro.spring.social.signinmvc.model.User;

/**
 * Created by eny on 3/27/14.
 */
public interface AuthChecker {
    AuthResult check(User user, String password);
}
