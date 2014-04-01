package net.nyavro.spring.social.signinmvc.services;

import net.nyavro.spring.social.signinmvc.model.dto.Auth;

public interface AuthHolder {

    void authenticate(Auth auth);

    void unAuthenticate();
}
