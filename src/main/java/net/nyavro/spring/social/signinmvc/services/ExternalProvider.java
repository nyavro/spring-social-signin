package net.nyavro.spring.social.signinmvc.services;

import net.nyavro.spring.social.signinmvc.model.dto.Auth;

/**
 * Created by eny on 3/27/14.
 */
public interface ExternalProvider {
    Auth authenticate(String providerName);
}
