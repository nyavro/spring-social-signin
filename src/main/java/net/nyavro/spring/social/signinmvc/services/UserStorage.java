package net.nyavro.spring.social.signinmvc.services;

import net.nyavro.spring.social.signinmvc.model.User;

/**
 * Deprecated. Use UserRepository instead
 */
@Deprecated
public interface UserStorage {

    User findLocalById(String id);

    User findByExternalId(String id);

    void save(User user);
}
