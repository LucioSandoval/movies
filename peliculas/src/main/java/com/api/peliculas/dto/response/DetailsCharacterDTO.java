package com.api.peliculas.dto.response;

import com.api.peliculas.dto.request.MovieDTO;
import java.util.Set;

public class DetailsCharacterDTO {
  private long id;
  private String  image;
  private String  name;
  private int  age;
  private double  weighs;
  private String  history;
  private Set<MovieDTO> movies;

  public DetailsCharacterDTO() {
  }

  public DetailsCharacterDTO(long id, String image, String name, int age, double weighs,
      String history, Set<MovieDTO> movies) {
    this.id = id;
    this.image = image;
    this.name = name;
    this.age = age;
    this.weighs = weighs;
    this.history = history;
    this.movies = movies;
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

  public Set<MovieDTO> getMovies() {
    return movies;
  }

  public void setMovies(Set<MovieDTO> movies) {
    this.movies = movies;
  }
}
