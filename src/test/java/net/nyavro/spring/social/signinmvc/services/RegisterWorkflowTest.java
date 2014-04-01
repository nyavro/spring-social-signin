package net.nyavro.spring.social.signinmvc.services;

import net.nyavro.spring.social.signinmvc.config.ApplicationTestContext;
import net.nyavro.spring.social.signinmvc.model.SocialMediaService;
import net.nyavro.spring.social.signinmvc.model.User;
import net.nyavro.spring.social.signinmvc.model.dto.Auth;
import net.nyavro.spring.social.signinmvc.model.dto.RegistrationForm;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationTestContext.class, loader = AnnotationConfigContextLoader.class)
public class RegisterWorkflowTest {
     /*
        2. Регистрация
        2.1. Локальная регистрация. Разлогиниваемся. Проходим регистрацию
        2.2. Внешняя регистрация. Разлогиниваемся. Проходим внешнюю авторизацию, открываем страницу регистрации с заполненными полями,
        создаём профиль с указанием провайдера.

        3. Связывание. Пользователь залогинен
        Пользователь переходит на внешнюю авторизацию. возвращается профиль, проверяем наличие профиля в базе. Если найден - объединяем
        2 записи, если нет - заполняем нужные поля у локального профиля.

         */

    @Mock
    private UserStorage storage;

    @Mock
    private ExternalProvider provider;

    @Mock
    private AuthHolder authHolder;

    @Autowired
    @InjectMocks
    private RegisterWorkflow workflow;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void init() {
        MatcherAssert.assertThat(workflow, Matchers.notNullValue());
    }

    /*

     2. Регистрация
        2.1. Локальная регистрация. Разлогиниваемся. Проходим регистрацию
        2.2. Внешняя регистрация. Разлогиниваемся. Проходим внешнюю авторизацию, открываем страницу регистрации с заполненными полями,
        создаём профиль с указанием провайдера.

    */

    @Test
    public void userRegistersLocally() {
        final String email = "mail@somemail.ru";
        final User user = new User();
        user.setEmail(email);
        final RegistrationForm form = workflow.registerLocalUser(user);
        MatcherAssert.assertThat(form.getEmail(), Matchers.equalTo(email));
        MatcherAssert.assertThat(form.getSignInProvider(), Matchers.equalTo(SocialMediaService.LOCAL));
    }

    @Test
    public void userRegistersExternally() {
        final String service = "odnotwit";
        final Auth auth = new Auth();
        auth.setId("id567");
        Mockito.when(provider.authenticate(service)).thenReturn(auth);
//        MatcherAssert.assertThat(workflow.externalLogIn(service), Matchers.equalTo(AuthResult.REGISTER));

    }
}
