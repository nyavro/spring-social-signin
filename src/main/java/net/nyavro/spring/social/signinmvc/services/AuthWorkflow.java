package net.nyavro.spring.social.signinmvc.services;

/**
 * Created by eny on 3/27/14.
 */
public interface AuthWorkflow {
    void localLogIn(String user, String password);
}
