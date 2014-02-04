package net.nyavro.spring.social.signinmvc.model.dto;

import net.nyavro.spring.social.signinmvc.model.SocialMediaService;
import net.nyavro.spring.social.signinmvc.model.User;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUser;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CustomUserDetails extends SocialUser {

    private final String id;

    private final String firstName;

    private final String lastName;

    private final SocialMediaService socialSignInProvider;

    private CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, String id, String first, String last, SocialMediaService provider) {
        super(username, password, authorities);
        this.id = id;
        this.firstName = first;
        this.lastName = last;
        this.socialSignInProvider = provider;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public SocialMediaService getSocialSignInProvider() {
        return socialSignInProvider;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("username", getUsername())
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("socialSignInProvider", socialSignInProvider)
                .toString();
    }

    public static class Builder {

        private String id;

        private String username;

        private String firstName;

        private String lastName;

        private String password;

        private SocialMediaService socialSignInProvider;

        private Set<GrantedAuthority> authorities;

        public Builder() {
            this.authorities = new HashSet<>();
        }

        public Builder user(User user) {
            this.firstName = user.getFirst();
            this.lastName = user.getLast();
            this.id = user.getId();
            this.authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            this.password = user.getPassword();
            if(user.isProvider()) {
                this.authorities.add(new SimpleGrantedAuthority("ROLE_PROVIDER"));
            }
            return this;
        }

        public CustomUserDetails build() {
            return new CustomUserDetails(username, password, authorities, id, firstName, lastName, socialSignInProvider);
        }
    }
}
