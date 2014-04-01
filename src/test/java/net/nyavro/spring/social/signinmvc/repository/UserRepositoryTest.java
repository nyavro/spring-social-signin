package net.nyavro.spring.social.signinmvc.repository;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Iterator;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationTestContext.class, loader = AnnotationConfigContextLoader.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Before
    public void setUp() {
        repository.deleteAll();
    }

    @Test
    public void savesObject() {
        final User user = new User();
        final String login = "eny";
        user.setLogin(login);
        user.setEmail("user@mail.ru");
        repository.save(user);
        final Iterator<User> all = repository.findAll().iterator();
        MatcherAssert.assertThat(all.hasNext(), Matchers.is(true));
        MatcherAssert.assertThat(all.next().getLogin(), Matchers.is(login));
        MatcherAssert.assertThat(all.hasNext(), Matchers.is(false));
    }

    @Test
    public void findsByEmail() {
        repository.save(init("other1@mail.ru", "other1"));
        final String email = "eny@mail.ru";
        final String last = "nyavro";
        repository.save(init(email, last));
        repository.save(init("other2@mail.ru", "other2"));
        MatcherAssert.assertThat(
            repository.findByEmail(email).getLast(),
            Matchers.is(last)
        );
    }

    @Test
    public void findsByProviderIdMapping() {
        repository.save(
            init(
                "other123@mail.ru", "other123",
                new ImmutableList.Builder<ProviderIdMapping>()
                    .add(new ProviderIdMapping("social_id_123", SocialMediaService.VKONTAKTE))
                    .build()
            )
        );
        final ProviderIdMapping mapping = new ProviderIdMapping("social_id_234", SocialMediaService.VKONTAKTE);
        repository.save(
            init(
                "other234@mail.ru", "other234",
                new ImmutableList.Builder<ProviderIdMapping>()
                    .add(mapping)
                    .build()
            )
        );
        MatcherAssert.assertThat(
            repository.findByProviderIdMappings(mapping).getLast(),
            Matchers.is("other234")
        );
    }

    @Test(expected = ConstraintViolationException.class)
    public void saveWithNullEmailFails() {
        repository.save(
            init(null, "other345")
        );
    }

    @Test(expected = ConstraintViolationException.class)
    public void saveWithEmptyEmailFails() {
        repository.save(
            init("", "other456")
        );
    }

    @Test(expected = ConstraintViolationException.class)
    public void saveWithInvalidEmailFails() {
        repository.save(
                init("invalidEmail", "other567")
        );
    }

    private User init(String email, String last) {
        final User user = new User();
        user.setEmail(email);
        user.setLast(last);
        return user;
    }

    private User init(String email, String last, List<ProviderIdMapping> mappings) {
        final User user = init(email, last);
        user.setProviderIdMappings(mappings);
        return user;
    }
}