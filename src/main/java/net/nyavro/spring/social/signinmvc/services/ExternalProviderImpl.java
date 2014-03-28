package net.nyavro.spring.social.signinmvc.services;

import net.nyavro.spring.social.signinmvc.model.dto.Auth;
import org.springframework.stereotype.Service;

@Service
public class ExternalProviderImpl implements ExternalProvider {
    @Override
    public Auth authenticate(String providerName) {
        return null;
    }
}
