package com.amisoft.apiregistry.validator;

import org.springframework.context.annotation.Configuration;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

@Configuration
public class ApplicationValidator {

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
}
