package net.nyavro.spring.social.signinmvc.services;

import net.nyavro.spring.social.signinmvc.model.User;
import net.nyavro.spring.social.signinmvc.model.dto.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by eny on 3/27/14.
 */
@Service
public class AuthWorkflowImpl implements AuthWorkflow {

    @Autowired
    private UserStorage storage;

    @Autowired
    private AuthChecker checker;

    @Autowired
    private ExternalProvider provider;

    @Override
    public AuthResult localLogIn(final String login, final String password) {
        final User user = storage.findLocalById(login);
        if(user != null) {
            return checker.check(user, password);
        }
        return AuthResult.REGISTER;
    }

    @Override
    public AuthResult externalLogIn(String providerName) {
        final Auth auth = provider.authenticate(providerName);
        final User user = storage.findByExternalId(auth.getId());
        return AuthResult.GRANTED;
    }
}
