package net.nyavro.spring.social.signinmvc.repository;

import net.nyavro.spring.social.signinmvc.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, ObjectId> {
    public User findByEmail(String email);
}
