package com.api.peliculas.validation;

public class PasswordValidation {

  public static boolean isValid(String password) {
    return password.length() >= 8;
  }

}
