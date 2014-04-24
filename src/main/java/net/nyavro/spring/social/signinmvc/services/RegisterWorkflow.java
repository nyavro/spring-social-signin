package net.nyavro.spring.social.signinmvc.services;

import net.nyavro.spring.social.signinmvc.model.User;
import net.nyavro.spring.social.signinmvc.model.dto.RegistrationForm;

/**
 * Created by eny on 3/28/14.
 */
public interface RegisterWorkflow {

    User registerLocalUser();

    User registerExternalUser(String service);
}
