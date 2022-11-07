package com.api.peliculas.dto.request;

import com.api.peliculas.model.Genero;
import com.api.peliculas.validation.ValidationMessages;
import java.util.Date;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MovieDTO {
  private long id;

  @NotBlank(message = ValidationMessages.REQUEST_PARAM_EMPTY_ERROR_MESSAGE)
  @Size(max = 250, message = ValidationMessages.REQUEST_PARAM_MAX_ERROR_MESSAGE)
  private String image;

  @NotBlank(message = ValidationMessages.REQUEST_PARAM_EMPTY_ERROR_MESSAGE)
  @Size(max = 250, message = ValidationMessages.REQUEST_PARAM_MAX_ERROR_MESSAGE)
  private String title;

  @NotNull(message = ValidationMessages.REQUEST_PARAM_EMPTY_ERROR_MESSAGE)
  @Min(value = 1, message = ValidationMessages.REQUEST_PARAM_MIN_ERROR_MESSAGE)
  @Max(value = 6, message = ValidationMessages.REQUEST_PARAM_MAX_ERROR_MESSAGE)
  private int qualification;

  private Genero idGenero;

  public MovieDTO() {
  }

  public MovieDTO(long id, String image, String title, int qualification,
      Genero idGenero) {
    this.id = id;
    this.image = image;
    this.title = title;
    this.qualification = qualification;
    this.idGenero = idGenero;
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

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public int getQualification() {
    return qualification;
  }

  public void setQualification(int qualification) {
    this.qualification = qualification;
  }

  public Genero getIdGenero() {
    return idGenero;
  }

  public void setIdGenero(Genero idGenero) {
    this.idGenero = idGenero;
  }
}
