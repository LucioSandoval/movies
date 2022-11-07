package com.api.peliculas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "personajes")
public class Character {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "imagen")
  private String image;

  @Column(name = "nombre")
  private String name;

  @Column(name = "edad")
  private int age;

  @Column(name = "peso")
  private double weighs;

  @Column(name = "historia")
  private String history;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(name = "personajes_peliculas",
      joinColumns = @JoinColumn(name = "personaje_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "pelicula_id", referencedColumnName = "id"))

    //fetch = FetchType.LAZY, cascade = CascadeType.ALL

  @JsonIgnore
  private Set<Movie> movies = new HashSet<>();

  public Character() {
  }

  public Character(long id, String image, String name, int age, double weighs,
      String history, Set<Movie> movies) {
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

  public Set<Movie> getMovies() {
    return movies;
  }

  public void setMovies(Set<Movie> movies) {
    this.movies = movies;
  }
}
