package net.nyavro.spring.social.signinmvc.user.service;

public class DuplicateEmailException extends Exception {

    public DuplicateEmailException(String message) {
        super(message);
    }
}
