package net.nyavro.spring.social.signinmvc.services;

public class DuplicateEmailException extends Exception {

    public DuplicateEmailException(String message) {
        super(message);
    }
}
