package com.api.peliculas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

  public Character() {
  }

  public Character(long id, String image, String name, int age, double weighs, String history) {
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
