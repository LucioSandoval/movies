package com.api.peliculas.dto.request;

import com.api.peliculas.validation.ValidationMessages;
import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CharacterDTO {
  private long id;

  @NotBlank(message = ValidationMessages.REQUEST_PARAM_EMPTY_ERROR_MESSAGE)
  @Size(max = 250, message = ValidationMessages.REQUEST_PARAM_SIZE_MAX_ERROR_MESSAGE)
  private String image;


  @NotBlank(message = ValidationMessages.REQUEST_PARAM_EMPTY_ERROR_MESSAGE)
  @Pattern(regexp = ValidationMessages.REGEX_VALIDATION_STRING, message = ValidationMessages.REGEX_VALIDATION_STRING_MESSAGE)
  @Size(max = 250, message = ValidationMessages.REQUEST_PARAM_MAX_ERROR_MESSAGE)
  private String name;


  @NotNull(message = ValidationMessages.REQUEST_PARAM_EMPTY_ERROR_MESSAGE)
  @Min(value = 0, message = ValidationMessages.REQUEST_PARAM_MIN_ERROR_MESSAGE)
  /*@Max(value = 3, message = ValidationMessages.REQUEST_PARAM_SIZE_MAX_ERROR_MESSAGE)*/
  private int age;

  @NotNull(message = ValidationMessages.REQUEST_PARAM_EMPTY_ERROR_MESSAGE)
  private Double weighs;


  @NotBlank(message = ValidationMessages.REQUEST_PARAM_EMPTY_ERROR_MESSAGE)
  @Size(max = 250, message = ValidationMessages.REQUEST_PARAM_MAX_ERROR_MESSAGE)
  private String history;

  public CharacterDTO() {
  }

  public CharacterDTO(long id, String image, String name, int age, double weighs,
      String history) {
    this.id = id;
    this.image = image;
    this.name = name;
    this.age = age;
    this.weighs = weighs;
    this.history = history;


  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public double getWeighs() {
    return weighs;
  }

  public void setWeighs(double weighs) {
    this.weighs = weighs;
  }

  public String getHistory() {
    return history;
  }

  public void setHistory(String history) {
    this.history = history;
  }
}
