package com.api.peliculas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
  private static final long serialVersionUID = 1L;

  private String resourceName;
  private String fieldName;
  private long valueOfField;

  public ResourceNotFoundException() {
  }

  public ResourceNotFoundException(String resourceName, String fieldName, long valueOfField) {
    super(String.format("%s no encontrada con : %s : '%s'", resourceName, fieldName, valueOfField));
    this.resourceName = resourceName;
    this.fieldName = fieldName;
    this.valueOfField = valueOfField;
  }

  public String getResourceName() {
    return resourceName;
  }

  public void setResourceName(String resourceName) {
    this.resourceName = resourceName;
  }

  public String getFieldName() {
    return fieldName;
  }

  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }

  public long getValueOfField() {
    return valueOfField;
  }

  public void setValueOfField(long valueOfField) {
    this.valueOfField = valueOfField;
  }
}
