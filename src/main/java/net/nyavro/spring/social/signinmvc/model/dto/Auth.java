package net.nyavro.spring.social.signinmvc.model.dto;

import net.nyavro.spring.social.signinmvc.model.SocialMediaService;

/**
 * Created by eny on 2/21/14.
 */
public class Auth {

    private String email;

    private String first;

    private String last;

    private String login;

    private String city;

    private String company;

    private String password;

    private boolean isPrivate;
    private SocialMediaService signInProvider;
    private String id;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String getLogin() {
        return login;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public void setSignInProvider(SocialMediaService signInProvider) {
        this.signInProvider = signInProvider;
    }

    public SocialMediaService getSignInProvider() {
        return signInProvider;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
