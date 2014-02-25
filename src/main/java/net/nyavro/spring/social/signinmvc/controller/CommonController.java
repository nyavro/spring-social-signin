package net.nyavro.spring.social.signinmvc.controller;

import net.nyavro.spring.social.signinmvc.model.dto.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;

public class CommonController {

    @ModelAttribute("principal")
    public CustomUserDetails user() {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return new CustomUserDetails(auth.getName(), auth.getCredentials()==null ? "" : auth.getCredentials().toString(), auth.getAuthorities());
    }
}