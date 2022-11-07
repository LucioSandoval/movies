package com.api.peliculas.dto.response;

import com.api.peliculas.dto.request.CharacterDTO;
import com.api.peliculas.model.Character;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

public class DetailsMovieDTO {
  private long id;
  private String image;
  private String title;

  private int qualification;
  private Set<CharacterDTO> characters;

  public DetailsMovieDTO() {
  }

  public DetailsMovieDTO(long id, String image, String title,
      int qualification, Set<CharacterDTO> characters) {
    this.id = id;
    this.image = image;
    this.title = title;
    this.qualification = qualification;
    this.characters = characters;
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

  public Set<CharacterDTO> getCharacters() {
    return characters;
  }

  public void setCharacters(Set<CharacterDTO> characters) {
    this.characters = characters;
  }
}
