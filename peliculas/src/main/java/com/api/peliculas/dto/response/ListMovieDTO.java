package com.api.peliculas.dto.response;

import java.util.Date;

public class ListMovieDTO {
  private String image;
  private String title;
  private Date creationDate;

  public ListMovieDTO(String image, String title, Date creationDate) {
    this.image = image;
    this.title = title;
    this.creationDate = creationDate;
  }

  public ListMovieDTO() {
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

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }
}
