package net.nyavro.spring.social.signinmvc.services;

import net.nyavro.spring.social.signinmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.Validator;

/**
 * Created by eny on 3/27/14.
 */
@Service
public class RegisterWorkflowImpl implements RegisterWorkflow {

    @Autowired
    private UserStorage storage;

    @Override
    public void registerLocalUser(User user) {
        storage.save(user);
    }
}
