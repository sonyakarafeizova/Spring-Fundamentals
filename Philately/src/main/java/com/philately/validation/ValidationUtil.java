package com.philately.validation;

public class ValidationUtil {
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    public static boolean isValidEmail(String email) {
        return email != null && email.matches(EMAIL_PATTERN);
    }

    public static boolean isValidUsername(String username) {
        return username != null && username.length() >= 3 && username.length() <= 20;
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 3 && password.length() <= 20;
    }
}

