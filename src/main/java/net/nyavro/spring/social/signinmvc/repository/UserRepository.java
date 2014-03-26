package net.nyavro.spring.social.signinmvc.repository;

import net.nyavro.spring.social.signinmvc.model.ProviderIdMapping;
import net.nyavro.spring.social.signinmvc.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, ObjectId> {

    User findByEmail(String email);

    User findByProviderIdMappings(ProviderIdMapping mapping);

    User findByLogin(String login);
}
