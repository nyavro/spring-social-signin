package net.nyavro.spring.social.signinmvc.services;

import net.nyavro.spring.social.signinmvc.model.User;

/**
 * Created by eny on 3/28/14.
 */
public interface RegisterWorkflow {
    void registerLocalUser(User user);
}
