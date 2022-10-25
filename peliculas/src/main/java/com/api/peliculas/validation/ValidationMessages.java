package com.api.peliculas.validation;

public class ValidationMessages {
  public static final String REQUEST_PARAM_EMPTY_ERROR_MESSAGE = "El atributo %s no debe estar vacío";

  public static final String REQUEST_PARAM_NULL_ERROR_MESSAGE = "El atributo %s es obligatorio";

  public static final String REQUEST_PARAM_EMAIL_ERROR_MESSAGE = "El atributo %s no tiene el formato correcto";

  public static final String REQUEST_PARAM_MIN_SIZE_ERROR_MESSAGE = "El atributo %s no debe tener menos de {min} caracteres";

  public static final String REQUEST_PARAM_MAX_ERROR_MESSAGE = "El atributo %s no debe tener más de {max} caracteres";

  public static final String REQUEST_PARAM_RANGE_ERROR_MESSAGE = "El atributo %s debe tener más de {min} caracteres y no debe tener más de {max} caracteres";

  public static final String REGEX_VALIDATION_STRING = "^([a-zA-Z]+)$";

  public static final String REGEX_VALIDATION_STRING_MESSAGE = "Este campo no debe contener ningún número";

  public static final String REQUEST_PARAM_SIZE_MIN_ERROR_MESSAGE = "El el campo debe contener minimo 1 digito mayor a cero";

  public static final String REQUEST_PARAM_SIZE_MAX_ERROR_MESSAGE = "El el campo no debe ser mayor a 3 digitos";

}
