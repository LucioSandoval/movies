package com.api.peliculas.service.abstraction;

import com.api.peliculas.dto.response.DetailsMovieDTO;
import com.api.peliculas.dto.response.ListMovieDTO;
import com.api.peliculas.dto.request.MovieDTO;
import com.api.peliculas.exception.OrderByIncorrectException;
import java.util.List;

public interface IMovieService {
  public List<ListMovieDTO> listMovie();
  public List<DetailsMovieDTO> detailsMovie();
  public MovieDTO create(MovieDTO movieDTO, long idGenero);
  public MovieDTO update(MovieDTO movieDTO, long id, long idGenero);
  public void delete(Long id);
  public List<MovieDTO> findByTitle(String title);
  public List<MovieDTO>findByIdGenero(long id);
  public List<MovieDTO>listOrderBy(String  order) throws OrderByIncorrectException;

}
