package net.nyavro.spring.social.signinmvc.services;

import com.google.common.collect.ImmutableList;
import net.nyavro.spring.social.signinmvc.config.ApplicationTestContext;
import net.nyavro.spring.social.signinmvc.model.ProviderIdMapping;
import net.nyavro.spring.social.signinmvc.model.SocialMediaService;
import net.nyavro.spring.social.signinmvc.model.User;
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
    private RepositoryUserService repositoryUserService;

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
        final ProviderIdMapping mapping = new ProviderIdMapping("social_123", SocialMediaService.VKONTAKTE);
        user.setProviderIdMappings(
            new ImmutableList.Builder<ProviderIdMapping>().add(mapping).build()
        );
        Mockito.when(repositoryUserService.findByProviderIdMappings(mapping)).thenReturn(user);
        MatcherAssert.assertThat(authenticationManager.authenticate(user), Matchers.equalTo(AuthResult.GRANTED));
    }

    @Test
    public void redirectsIfUserNotFoundByProviderId() {
        final User user = new User();
        user.setProviderIdMappings(
            new ImmutableList.Builder<ProviderIdMapping>()
                .add(new ProviderIdMapping("social_123", SocialMediaService.VKONTAKTE))
                .build()
        );
        MatcherAssert.assertThat(authenticationManager.authenticate(user), Matchers.equalTo(AuthResult.REGISTER));
    }
}
