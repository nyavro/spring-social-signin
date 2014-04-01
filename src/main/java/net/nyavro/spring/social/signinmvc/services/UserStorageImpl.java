package net.nyavro.spring.social.signinmvc.services;

import net.nyavro.spring.social.signinmvc.model.User;
import org.springframework.stereotype.Service;

@Deprecated
@Service
public class UserStorageImpl implements UserStorage {
    @Override
    public User findLocalById(String id) {
        return null;
    }

    @Override
    public User findByExternalId(String id) {
        return null;
    }

    @Override
    public void save(User user) {
    }
}
