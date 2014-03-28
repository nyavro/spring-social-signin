package net.nyavro.spring.social.signinmvc.services;

import net.nyavro.spring.social.signinmvc.model.User;

/**
 * Created by eny on 3/27/14.
 */
public interface UserStorage {
    User findLocalById(String id);

    User findByExternalId(String id);

    void save(User user);
}
