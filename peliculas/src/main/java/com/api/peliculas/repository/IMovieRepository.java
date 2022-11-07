package com.api.peliculas.repository;

import com.api.peliculas.model.Character;
import com.api.peliculas.model.Genero;
import com.api.peliculas.model.Movie;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IMovieRepository extends JpaRepository<Movie, Long> {

  public List<Movie> findByTitle(@Param("title") String title);

  @Query(value = "SELECT * FROM  peliculas where genero_id = :idGenero", nativeQuery = true)
  public List<Movie> findByIdGenero(@Param(value = "idGenero") long idGenero);


  @Query(value = "SELECT * FROM peliculas order by creation_date asc", nativeQuery = true)
  public List<Movie>orderASC();

  @Query(value = "SELECT * FROM peliculas order by creation_date desc", nativeQuery = true)
  public List<Movie>orderDESC();



  /*@Query(value = "from Comment c where c.newsId.id = :id")
  List<Comment> findByNewsId(@Param("id") Long id);*/

}
