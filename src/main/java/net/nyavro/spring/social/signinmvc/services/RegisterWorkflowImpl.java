package net.nyavro.spring.social.signinmvc.services;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import net.nyavro.spring.social.signinmvc.model.ProviderIdMapping;
import net.nyavro.spring.social.signinmvc.model.SocialMediaService;
import net.nyavro.spring.social.signinmvc.model.User;
import net.nyavro.spring.social.signinmvc.model.dto.Auth;
import net.nyavro.spring.social.signinmvc.model.dto.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.Validator;

@Service
public class RegisterWorkflowImpl implements RegisterWorkflow {

    @Autowired
    private UserStorage storage;

    @Autowired
    private AuthHolder holder;

    @Autowired
    private ExternalProvider provider;

    @Override
    public User registerLocalUser() {
        holder.unAuthenticate();
        return new User();
    }

    @Override
    public User registerExternalUser(String service) {
        holder.unAuthenticate();
        final Auth auth = provider.authenticate(service);
        final User user = new User();
        user.setLogin(auth.getLogin());
        user.setProviderIdMappings(
            new ImmutableList.Builder<ProviderIdMapping>()
                .add(new ProviderIdMapping(auth.getId(), auth.getSignInProvider()))
                .build()
        );
        return user;
    }
}
