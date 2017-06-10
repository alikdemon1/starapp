package kz.alisher.greetgo.validators;

import kz.alisher.greetgo.utils.Constants;
import kz.alisher.greetgo.domain.Login;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by alisher on 6/7/17.
 */
@Component
public class LoginValidator implements Validator {
    @Value("${login.invalid.email}")
    private String emailMsg;
    @Value("${login.invalid.password}")
    private String passMsg;

    @Override
    public boolean supports(Class<?> aClass) {
        return Login.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Login login = (Login) o;

        if (!login.getEmail().equals(Constants.EMAIL)) {
            errors.rejectValue("email", emailMsg);
        }

        if (!login.getPassword().equals(Constants.PASSWORD)) {
            errors.rejectValue("password", passMsg);
        }
    }
}
