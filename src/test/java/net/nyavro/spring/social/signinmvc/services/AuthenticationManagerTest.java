package net.nyavro.spring.social.signinmvc.services;

import net.nyavro.spring.social.signinmvc.config.ApplicationTestContext;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
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
    @Autowired
    private AuthenticationManager authenticationManager;

    @Test
    public void init() {
        MatcherAssert.assertThat(authenticationManager, Matchers.notNullValue());
    }
}
