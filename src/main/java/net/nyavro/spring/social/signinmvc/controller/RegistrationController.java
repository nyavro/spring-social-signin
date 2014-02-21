package net.nyavro.spring.social.signinmvc.controller;

import net.nyavro.spring.social.signinmvc.utils.SecurityUtil;
import net.nyavro.spring.social.signinmvc.model.dto.RegistrationForm;
import net.nyavro.spring.social.signinmvc.model.SocialMediaService;
import net.nyavro.spring.social.signinmvc.model.User;
import net.nyavro.spring.social.signinmvc.services.DuplicateEmailException;
import net.nyavro.spring.social.signinmvc.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@SessionAttributes("user")
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistrationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

    protected static final String ERROR_CODE_EMAIL_EXIST = "NotExist.user.email";
    protected static final String MODEL_NAME_REGISTRATION_DTO = "user";
    protected static final String VIEW_NAME_REGISTRATION_PAGE = "user/registrationForm";

    @Value("${user.private.default}")
    private boolean userPrivate;

    @Autowired
    private UserService service;

/*
    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public String showRegistrationForm(WebRequest request, Model model) {
        LOGGER.debug("Rendering registration page.");
        model.addAttribute(MODEL_NAME_REGISTRATION_DTO, createRegistrationDTO(ProviderSignInUtils.getConnection(request)));
        return VIEW_NAME_REGISTRATION_PAGE;
    }*/

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String createForm(Model model, WebRequest request) throws IOException {
        final User user = createRegistrationDTO(ProviderSignInUtils.getConnection(request));
        model.addAttribute("user", user);
        return "/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String create(final @Valid User user, final BindingResult result, final Model model)
            throws Exception {
        if (!result.hasErrors()) {
            user.setRegistered(new Date());
            service.create(user);
        } else {
            final List<String> errors = new ArrayList<String>();
            for (ObjectError objError : result.getAllErrors()) {
                errors.add(objError.getDefaultMessage());
            }
            model.addAttribute("errors", errors);
        }
        return "/index";
    }

    private User createRegistrationDTO(final Connection<?> connection) {
        final User dto = new User();
        if (connection != null) {
            final UserProfile profile = connection.fetchUserProfile();
            dto.setEmail(profile.getEmail());
            dto.setFirst(profile.getFirstName());
            dto.setLast(profile.getLastName());
//            dto.setSignInProvider(SocialMediaService.valueOf(connection.getKey().getProviderId().toUpperCase()));
            dto.setLogin(profile.getUsername());
        }
        dto.setPrivate(userPrivate);
        return dto;
    }

    @RequestMapping(value ="/user/register", method = RequestMethod.POST)
    public String registerUserAccount(final @Valid @ModelAttribute("user") User userAccountData,
                                      final BindingResult result,
                                      final WebRequest request) throws DuplicateEmailException {
        LOGGER.debug("Registering user account with information: {}", userAccountData);
        if (result.hasErrors()) {
            LOGGER.debug("Validation errors found. Rendering form view.");
            return VIEW_NAME_REGISTRATION_PAGE;
        }
        LOGGER.debug("No validation errors found. Continuing registration process.");
        final User registered = createUserAccount(userAccountData, result);
        //If email address was already found from the database, render the form view.
        if (registered == null) {
            LOGGER.debug("An email address was found from the database. Rendering form view.");
            return VIEW_NAME_REGISTRATION_PAGE;
        }

        LOGGER.debug("Registered user account with information: {}", registered);

        //Logs the user in.
        SecurityUtil.logInUser(registered);
        LOGGER.debug("User {} has been signed in");
        //If the user is signing in by using a social provider, this method call stores
        //the connection to the UserConnection table. Otherwise, this method does not
        //do anything.
        ProviderSignInUtils.handlePostSignUp(registered.getEmail(), request);
        return "redirect:/";
    }

    /**
     * Creates a new user account by calling the service method. If the email address is found
     * from the database, this method adds a field error to the email field of the form object.
     */
    private User createUserAccount(User user, BindingResult result) {
        LOGGER.debug("Creating user account with information: {}", user);
        User registered = null;
        try {
            registered = service.create(user);
        }
        catch (DuplicateEmailException ex) {
            LOGGER.debug("An email address: {} exists.", user.getEmail());
            addFieldError(
                MODEL_NAME_REGISTRATION_DTO,
                RegistrationForm.FIELD_NAME_EMAIL,
                user.getEmail(),
                ERROR_CODE_EMAIL_EXIST,
                result
            );
        }
        return registered;
    }

    private void addFieldError(String objectName, String fieldName, String fieldValue,  String errorCode, BindingResult result) {
        LOGGER.debug(
            "Adding field error object's: {} field: {} with error code: {}",
            objectName,
            fieldName,
            errorCode
        );
        final FieldError error = new FieldError(
            objectName,
            fieldName,
            fieldValue,
            false,
            new String[]{errorCode},
            new Object[]{},
            errorCode
        );
        result.addError(error);
        LOGGER.debug("Added field error: {} to binding result: {}", error, result);
    }
}
