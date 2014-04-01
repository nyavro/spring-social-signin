package net.nyavro.spring.social.signinmvc.config;

import com.mongodb.DBObject;
import net.nyavro.spring.social.signinmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.Set;

@Component
public class UserValidation extends AbstractMongoEventListener<User> {

    @Autowired
    private Validator validator;

    @Override
    public void onBeforeSave(final User user, final DBObject dbo) {
        final Set<ConstraintViolation<User>> violations = validator.validate(user);
        if(!violations.isEmpty()) {
            throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
        }
    }
}
