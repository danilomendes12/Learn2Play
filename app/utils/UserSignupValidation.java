package utils;

import models.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by danilomendes on 11/7/14.
 */
public enum UserSignupValidation {

    COMPLETE_NAME(1, "Nome inválido ou vazio"),

    NICKNAME(2, "Nickname inválido"),

    EMAIL(3, "Email inválido ou vazio"),

    PASSWORD(4, "Senha invalida ou diferente da confirmacao. A senha deve possuir ao menos 6 caracteres."),

    NICKNAMEUSED(5, "Nickname já utilizado"),

    CONFIRMPASSWORD(6, "Senha e confirmação de senha diferentes");

    Integer code;
    String message;

    public static UserSignupValidation validate(String name, String nickname, String email, String password, String confirmationPassword) {

        EmailValidator emailValidator = new EmailValidator();

        if (name == null || name.isEmpty()) {

            return UserSignupValidation.COMPLETE_NAME;

        } else if (nickname == null || nickname.isEmpty()) {

            return UserSignupValidation.NICKNAME;

        } else if (User.authenticateNickname(nickname)) {

            return UserSignupValidation.NICKNAMEUSED;

        } else if (email == null || email.isEmpty() || !emailValidator.validate(email)) {

            return UserSignupValidation.EMAIL;

        } else if (password == null || confirmationPassword == null || password.isEmpty() || confirmationPassword.isEmpty()) {

            return UserSignupValidation.PASSWORD;

        }  else if (!password.equals(confirmationPassword)) {

            return UserSignupValidation.CONFIRMPASSWORD;

        } else {

            return null;
        }
    }

    public static UserSignupValidation fromCode(Integer code) {
        for (UserSignupValidation e : values()) {
            if (e.code.equals(code))
                return e;
        }
        return null;
    }

    UserSignupValidation(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

    public static class EmailValidator {

        private Pattern pattern;
        private Matcher matcher;

        private static final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        public EmailValidator() {
            pattern = Pattern.compile(EMAIL_PATTERN);
        }

        /**
         * Validate hex with regular expression
         *
         * @param hex hex for validation
         * @return true valid hex, false invalid hex
         */
        boolean validate(final String hex) {

            matcher = pattern.matcher(hex);
            return matcher.matches();

        }
    }
}
