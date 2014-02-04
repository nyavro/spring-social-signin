package net.nyavro.spring.social.signinmvc.utils;

import net.nyavro.spring.social.signinmvc.model.dto.CustomUserDetails;
import net.nyavro.spring.social.signinmvc.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityUtil.class);

    public static void logInUser(User user) {
        LOGGER.info("Logging in user: {}", user);
        final CustomUserDetails userDetails = CustomUserDetails.getBuilder().user(user).build();
        LOGGER.debug("Logging in principal: {}", userDetails);
        SecurityContextHolder.getContext().setAuthentication(
            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities())
        );
        LOGGER.info("User: {} has been logged in.", userDetails);
    }
}
