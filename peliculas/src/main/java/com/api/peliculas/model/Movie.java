package com.api.peliculas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name = "peliculas")
public class Movie {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String image;
  private String title;
  @CreationTimestamp
  private Timestamp creationDate;
  private int qualification;

  @ManyToMany( mappedBy = "movies") //, fetch = FetchType.LAZY, cascade = CascadeType.ALL
  @JsonIgnore
  private Set<Character> characters = new HashSet<>();

  @JoinColumn(name = "genero_id")
  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)

  private Genero idGenero;

  public Movie() {
  }

  public Movie(long id, String image, String title, Timestamp creationDate, int qualification,
      Set<Character> characters, Genero idGenero) {
    this.id = id;
    this.image = image;
    this.title = title;
    this.creationDate = creationDate;
    this.qualification = qualification;
    this.characters = characters;
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

  public Date getCreationDate() {
    return creationDate;
  }



  public int getQualification() {
    return qualification;
  }

  public void setQualification(int qualification) {
    this.qualification = qualification;
  }

  public Set<Character> getCharacters() {
    return characters;
  }

  public void setCharacters(Set<Character> characters) {
    this.characters = characters;
  }


  public Genero getIdGenero() {
    return idGenero;
  }

  public void setIdGenero(Genero idGenero) {
    this.idGenero = idGenero;
  }
}
