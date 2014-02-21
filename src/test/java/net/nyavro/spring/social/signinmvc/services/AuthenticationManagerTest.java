package net.nyavro.spring.social.signinmvc.services;

import com.google.common.collect.ImmutableList;
import net.nyavro.spring.social.signinmvc.config.ApplicationTestContext;
import net.nyavro.spring.social.signinmvc.model.ProviderIdMapping;
import net.nyavro.spring.social.signinmvc.model.SocialMediaService;
import net.nyavro.spring.social.signinmvc.model.User;
import net.nyavro.spring.social.signinmvc.model.dto.Auth;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;


/**
 * Created by eny on 2/21/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationTestContext.class, loader = AnnotationConfigContextLoader.class)
public class AuthenticationManagerTest {

    @Mock
    private UserService userService;

    @Autowired
    @InjectMocks
    private AuthenticationManager authenticationManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void init() {
        MatcherAssert.assertThat(authenticationManager, Matchers.notNullValue());
    }

    @Test
    public void grantsIfUserFoundByProviderId() {
        final User user = new User();
        final String socialId = "social_123";
        final ProviderIdMapping mapping = new ProviderIdMapping(socialId, SocialMediaService.VKONTAKTE);
        user.setProviderIdMappings(
            new ImmutableList.Builder<ProviderIdMapping>().add(mapping).build()
        );
        Mockito.when(userService.findByProviderIdMappings(mapping)).thenReturn(user);
        final Auth auth = new Auth();
        auth.setId(socialId);
        auth.setSignInProvider(SocialMediaService.VKONTAKTE);
        MatcherAssert.assertThat(authenticationManager.authenticate(auth), Matchers.equalTo(AuthResult.GRANTED));
    }

    @Test
    public void redirectsIfUserNotFoundByProviderId() {
        final User user = new User();
        final String socialId = "social_234";
        user.setProviderIdMappings(
            new ImmutableList.Builder<ProviderIdMapping>()
                .add(new ProviderIdMapping(socialId, SocialMediaService.VKONTAKTE))
                .build()
        );
        final Auth auth = new Auth();
        auth.setId(socialId);
        auth.setSignInProvider(SocialMediaService.VKONTAKTE);
        MatcherAssert.assertThat(authenticationManager.authenticate(auth), Matchers.equalTo(AuthResult.REGISTER));
    }
}
