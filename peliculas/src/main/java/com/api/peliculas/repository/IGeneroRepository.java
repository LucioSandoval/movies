package com.api.peliculas.repository;

import com.api.peliculas.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGeneroRepository extends JpaRepository<Genero, Long> {

}
