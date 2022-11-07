package com.api.peliculas.exception;

public class EmailException extends Exception{
  private static final String EMAIL_ALREADY_EXIST_MESSAGE = "El email ya existe.";
  private static final long serialVersionUID = 1L;

  public EmailException (){

    super(EMAIL_ALREADY_EXIST_MESSAGE);
  }
}
