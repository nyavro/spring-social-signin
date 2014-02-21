package net.nyavro.spring.social.signinmvc.services;

import net.nyavro.spring.social.signinmvc.model.dto.Auth;

/**
 * Created by eny on 2/21/14.
 */
public interface AuthenticationManager {

    AuthResult authenticate(Auth auth);
}
