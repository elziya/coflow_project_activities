package com.technokratos.validation;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.technokratos.consts.CoflowValidationConstants.*;

@Component
public class PasswordValidatorImpl implements ConstraintValidator<ValidPassword, Object> {

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        boolean isValid = true;

        final BeanWrapperImpl bean = new BeanWrapperImpl(object);
        String password = (String) bean.getPropertyValue("password");

        context.disableDefaultConstraintViolation();

        if (password.length() < PASSWORD_MIN_LENGTH) {
            isValid = false;
            buildConstraint("Password length is less than : " + PASSWORD_MIN_LENGTH, "password", context);
        }

        if (password.length() > PASSWORD_MAX_LENGTH) {
            isValid = false;
            buildConstraint("Password length is more than : " + PASSWORD_MAX_LENGTH, "password", context);
        }

        Pattern p = Pattern.compile(PASSWORD_PATTERN);
        Matcher m = p.matcher(password);

        if (!m.find()) {
            isValid = false;
            buildConstraint("The password does not match the pattern" + PASSWORD_PATTERN, "password", context);
        }

        return isValid;
    }

    private void buildConstraint(String template, String node, ConstraintValidatorContext context) {
        context.buildConstraintViolationWithTemplate(template)
                .addPropertyNode(node)
                .addConstraintViolation();
    }
}

