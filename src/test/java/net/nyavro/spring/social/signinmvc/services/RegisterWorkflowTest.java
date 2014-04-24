package net.nyavro.spring.social.signinmvc.services;

import net.nyavro.spring.social.signinmvc.config.ApplicationTestContext;
import net.nyavro.spring.social.signinmvc.model.ProviderIdMapping;
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
        workflow.registerLocalUser();
        Mockito.verify(authHolder, Mockito.times(1)).unAuthenticate();
    }

    @Test
    public void userRegistersExternally() {
        final String service = "odnotwit";
        final Auth auth = new Auth();
        auth.setId("id567");
        auth.setSignInProvider(SocialMediaService.FACEBOOK);
        Mockito.when(provider.authenticate(service)).thenReturn(auth);
        final User user = workflow.registerExternalUser(service);
        Mockito.verify(authHolder, Mockito.times(1)).unAuthenticate();
        MatcherAssert.assertThat(user.getProviderIdMappings(), Matchers.hasItem(new ProviderIdMapping("id567", SocialMediaService.FACEBOOK)));
    }
}
