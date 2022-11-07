package com.api.peliculas.exception;

public class OrderByIncorrectException extends Exception{
  private static final long serialVersionUID = 1L;

  public OrderByIncorrectException(String message) {
    super(message);
  }
}
