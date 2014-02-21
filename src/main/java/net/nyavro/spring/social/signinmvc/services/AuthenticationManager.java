package net.nyavro.spring.social.signinmvc.services;

import net.nyavro.spring.social.signinmvc.model.User;
import org.springframework.social.connect.UserProfile;

/**
 * Created by eny on 2/21/14.
 */
public interface AuthenticationManager {

    AuthResult authenticate(User user);
}
