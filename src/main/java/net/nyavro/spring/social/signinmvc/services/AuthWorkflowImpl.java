package net.nyavro.spring.social.signinmvc.services;

import net.nyavro.spring.social.signinmvc.model.SocialMediaService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by eny on 3/27/14.
 */
public class AuthWorkflowImpl implements AuthWorkflow {

    @Autowired
    private UserStorage storage;

    @Override
    public void localLogIn(String user, String password) {
        storage.findLocalById(user);
    }
}
