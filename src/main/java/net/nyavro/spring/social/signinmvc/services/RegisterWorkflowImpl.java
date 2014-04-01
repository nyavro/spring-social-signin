package net.nyavro.spring.social.signinmvc.services;

import net.nyavro.spring.social.signinmvc.model.SocialMediaService;
import net.nyavro.spring.social.signinmvc.model.User;
import net.nyavro.spring.social.signinmvc.model.dto.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.Validator;

@Service
public class RegisterWorkflowImpl implements RegisterWorkflow {

    @Autowired
    private UserStorage storage;

    @Autowired
    private ExternalProvider provider;

    @Override
    public RegistrationForm registerLocalUser(User user) {
        final RegistrationForm form = new RegistrationForm();
        form.setEmail(user.getEmail());
        form.setFirstName(user.getFirst());
        form.setLastName(user.getLast());
        form.setPassword(user.getPassword());
        form.setSignInProvider(SocialMediaService.LOCAL);
        storage.save(user);
        return form;
    }
}
