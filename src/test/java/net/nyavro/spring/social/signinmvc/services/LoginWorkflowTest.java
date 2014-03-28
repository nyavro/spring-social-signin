package net.nyavro.spring.social.signinmvc.services;

import net.nyavro.spring.social.signinmvc.config.ApplicationTestContext;
import net.nyavro.spring.social.signinmvc.model.User;
import net.nyavro.spring.social.signinmvc.model.dto.Auth;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Ignore;
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
public class LoginWorkflowTest {
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
    private AuthChecker checker;

    @Mock
    private ExternalProvider provider;

    @Autowired
    @InjectMocks
    private AuthWorkflow workflow;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void init() {
        MatcherAssert.assertThat(workflow, Matchers.notNullValue());
    }

    @Test
    public void logsInLocally() {
        final User user = new User();
        final String login = "user";
        final String password = "password";
        Mockito.when(storage.findLocalById(login)).thenReturn(user);
        Mockito.when(checker.check(user, password)).thenReturn(AuthResult.GRANTED);
        MatcherAssert.assertThat(workflow.localLogIn(login, password), Matchers.equalTo(AuthResult.GRANTED));
    }

    @Test
    public void localAuthFailsWhenInvalidPassword() {
        final User user = new User();
        final String login = "login";
        final String password = "pass";
        Mockito.when(storage.findLocalById(login)).thenReturn(user);
        Mockito.when(checker.check(user, password)).thenReturn(AuthResult.FAILED);
        MatcherAssert.assertThat(workflow.localLogIn(login, password), Matchers.equalTo(AuthResult.FAILED));
    }

    @Test
    public void localRegistrationRequestedWhenUserNotFound() {
        MatcherAssert.assertThat(workflow.localLogIn("lgn", "pwd"), Matchers.equalTo(AuthResult.REGISTER));
    }

//    1. Вход
//    1.2. Внешний вход - пользователь перенаправляется на страницу авторизации (внешнюю), возвращается информация о
//    пользователе. По этой информации пытаемся достать из базы профиль. Профиль найден - авторизация пройдена. Не найден -
//    Открываем страницу регистрации с заполненными полями из внешнего источника.
//
    @Test
    public void logsInExternallyMatchesLocalUser() {
        final String service = "facekontakt";
        final Auth auth = new Auth();
        auth.setId("id123");
        Mockito.when(provider.authenticate(service)).thenReturn(auth);
        Mockito.when(storage.findByExternalId(auth.getId())).thenReturn(new User());
        MatcherAssert.assertThat(workflow.externalLogIn(service), Matchers.equalTo(AuthResult.GRANTED));
    }

    @Test
    public void logsInExternallyDoesNotMatchLocalUser() {
        final String service = "kontwitter";
        final Auth auth = new Auth();
        auth.setId("id456");
        Mockito.when(provider.authenticate(service)).thenReturn(auth);
        MatcherAssert.assertThat(workflow.externalLogIn(service), Matchers.equalTo(AuthResult.REGISTER));
    }
}
