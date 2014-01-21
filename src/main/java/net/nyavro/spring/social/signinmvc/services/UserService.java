package net.nyavro.spring.social.signinmvc.services;

import net.nyavro.spring.social.signinmvc.model.dto.RegistrationForm;
import net.nyavro.spring.social.signinmvc.model.User;
import net.nyavro.spring.social.signinmvc.services.DuplicateEmailException;

public interface UserService {

    /**
     * Creates a new user account to the service.
     * @param userAccountData   The information of the created user account.
     * @return  The information of the created user account.
     * @throws net.nyavro.spring.social.signinmvc.services.DuplicateEmailException Thrown when the email address is found from the database.
     */
    public User registerNewUserAccount(RegistrationForm userAccountData) throws DuplicateEmailException;
}
