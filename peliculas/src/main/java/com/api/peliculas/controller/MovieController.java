package com.api.peliculas.controller;

import com.api.peliculas.dto.request.MovieDTO;
import com.api.peliculas.dto.response.DetailsMovieDTO;
import com.api.peliculas.dto.response.ListMovieDTO;
import com.api.peliculas.exception.OrderByIncorrectException;
import com.api.peliculas.service.abstraction.IMovieService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")

public class MovieController {

  @Autowired
  private IMovieService movieService;


  @GetMapping(value = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<ListMovieDTO> listmovie(){

    return movieService.listMovie();
  }

  @GetMapping(value = "/moviess", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<DetailsMovieDTO> detailsMovie(){
    return movieService.detailsMovie();

  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping(value = "/movies/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<MovieDTO> create(@Valid @RequestBody MovieDTO movieDTO, @PathVariable(value = "id") long id){
    return new ResponseEntity<>(movieService.create(movieDTO, id), HttpStatus.CREATED);

  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping(value = "/movies/{id}/{idGenero}", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<MovieDTO> update(@Valid @RequestBody MovieDTO movieDTO, @PathVariable(value = "id") long id, @PathVariable(value = "idGenero") long idGenero){

  return new ResponseEntity<>(movieService.update(movieDTO, id, idGenero), HttpStatus.OK);
  }

  @DeleteMapping(value = "/movies/{id}")
  public ResponseEntity<String> deleteMovie(@PathVariable(value = "id") long id){
    movieService.delete(id);
    return new ResponseEntity<>("Movie eliminado exitosamente", HttpStatus.OK);
  }

  @GetMapping(value = "/movies", params = {"title"})
  public List<MovieDTO> getFindByTitle(@RequestParam(value = "title") String title){
    return movieService.findByTitle(title);

  }

  @GetMapping(value = "/movies", params = {"idGenero"})
  public List<MovieDTO> getFindByIdGenero(@RequestParam(value = "idGenero") long idGenero){
    return movieService.findByIdGenero(idGenero);

  }

  @GetMapping(value = "/movies", params = {"order"})
  public List<MovieDTO> listOrder(@RequestParam(value = "order") String order)
      throws OrderByIncorrectException {
    return movieService.listOrderBy(order);

  }
}
