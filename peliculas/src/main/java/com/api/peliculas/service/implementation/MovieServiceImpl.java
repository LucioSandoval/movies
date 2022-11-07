package com.api.peliculas.service.implementation;

import com.api.peliculas.dto.request.CharacterDTO;
import com.api.peliculas.dto.response.DetailsMovieDTO;
import com.api.peliculas.dto.response.ListMovieDTO;
import com.api.peliculas.dto.request.MovieDTO;
import com.api.peliculas.exception.OrderByIncorrectException;
import com.api.peliculas.exception.ResourceNotFoundException;
import com.api.peliculas.model.Character;
import com.api.peliculas.model.Genero;
import com.api.peliculas.model.Movie;
import com.api.peliculas.repository.IGeneroRepository;
import com.api.peliculas.repository.IMovieRepository;
import com.api.peliculas.service.abstraction.IMovieService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements IMovieService {
  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private IMovieRepository movieRepository;

  @Autowired
  IGeneroRepository generoRepository;

  @Override
  public List<ListMovieDTO> listMovie() {
    List<Movie> listMovie = movieRepository.findAll();
    return listMovie.stream().map( (movie) -> mapearListMovieDTO(movie)).collect(Collectors.toList());
  }

  @Override
  public List<DetailsMovieDTO> detailsMovie() {
    List<Movie> movieList = movieRepository.findAll();

    return movieList.stream().map( (movie) -> mapearDetailsMovieDTO(movie)).collect(Collectors.toList());
  }

  @Override
  public MovieDTO create(MovieDTO movieDTO, long idGenero) {
    Genero genero = generoRepository.findById(idGenero).orElseThrow( () -> new ResourceNotFoundException("Genero", "id",idGenero));

    // entregando el objeto genero
    movieDTO.setIdGenero(genero);
    Movie movie = mapearMovie(movieDTO);

    Movie newMovie = movieRepository.save(movie);
    return mapearMovieDTO(newMovie);
  }

  @Override
  public MovieDTO update(MovieDTO movieDTO, long id, long idGenero) {
    Genero genero = generoRepository.findById(idGenero).orElseThrow(() -> new ResourceNotFoundException("Genero", "id", idGenero));
    Movie movie = movieRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Movie", "id", id));

    movie.setImage(movieDTO.getImage());
    movie.setTitle(movieDTO.getTitle());
    movie.setQualification(movieDTO.getQualification());
    movie.setIdGenero(genero);
    Movie newMovie = movieRepository.save(movie);
    return mapearMovieDTO(newMovie);
  }


  @Override
  public void delete(Long id) {
    Movie  movie= movieRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Movie", "id",id));
    movieRepository.delete(movie);
  }

  @Override
  public List<MovieDTO> findByTitle(String title) {
    List<Movie> movieList = movieRepository.findByTitle(title);
    return movieList.stream().map( (movie) -> mapearMovieDTO(movie)).collect(Collectors.toList());
  }

  @Override
  public List<MovieDTO> findByIdGenero(long id) {
    List<Movie> movieList = movieRepository.findByIdGenero(id);
    if (movieList.size() == 0) {
      throw new EntityNotFoundException("Genero not found");
    }

    return movieList.stream().map( (movie) -> mapearMovieDTO(movie)).collect(Collectors.toList());
  }

  @Override
  public List<MovieDTO> listOrderBy(String order) throws OrderByIncorrectException {
    List<Movie> movieList = null;

    if(order.equalsIgnoreCase("asc") || order.equalsIgnoreCase("desc")){
      if(order.equalsIgnoreCase("asc")){
        movieList = movieRepository.orderASC();
      }else if(order.equalsIgnoreCase("desc")){
        movieList= movieRepository.orderDESC();
      }
    }else{
      throw new OrderByIncorrectException("El parametro order es incorrecto, debe ser ASC o DESC");
    }

    return movieList.stream().map( (movie) -> mapearMovieDTO(movie)).collect(Collectors.toList());
  }


  private ListMovieDTO mapearListMovieDTO(Movie movie){
    ListMovieDTO listMovieDTO = new ListMovieDTO();
    listMovieDTO.setImage(movie.getImage());
    listMovieDTO.setTitle(movie.getTitle());
    listMovieDTO.setCreationDate(movie.getCreationDate());
    return listMovieDTO;

  }

  private DetailsMovieDTO mapearDetailsMovieDTO (Movie movie){
    DetailsMovieDTO detailsMovieDTO = new DetailsMovieDTO();
    detailsMovieDTO.setId(movie.getId());
    detailsMovieDTO.setImage(movie.getImage());
    detailsMovieDTO.setTitle(movie.getTitle());
    detailsMovieDTO.setQualification(movie.getQualification());

    Set<Character> listCharacter = movie.getCharacters();
    Set<CharacterDTO> listCharacterDTO = new HashSet<>();

    for (Character character:listCharacter) {
      listCharacterDTO.add(mapearCharacterDTO(character));
    }
    detailsMovieDTO.setCharacters(listCharacterDTO);
    return detailsMovieDTO;
  }
  private CharacterDTO mapearCharacterDTO (Character character){

    CharacterDTO characterDTO = new CharacterDTO();
    characterDTO.setId(character.getId());
    characterDTO.setImage(character.getImage());
    characterDTO.setName(character.getName());
    characterDTO.setAge(character.getAge());
    characterDTO.setWeighs(character.getWeighs());
    characterDTO.setHistory(character.getHistory());
    return characterDTO;
  }

  private MovieDTO mapearMovieDTO(Movie movie){
    MovieDTO movieDTO = new MovieDTO();
    movieDTO.setId(movie.getId());
    movieDTO.setImage(movie.getImage());
    movieDTO.setTitle(movie.getTitle());
    movieDTO.setQualification(movie.getQualification());
    movieDTO.setIdGenero(movie.getIdGenero());
    return movieDTO;
  }
  private Movie mapearMovie(MovieDTO movieDTO){
    Movie movie = new Movie();
    movie.setId(movieDTO.getId());
    movie.setImage(movieDTO.getImage());
    movie.setTitle(movieDTO.getTitle());
    movie.setQualification(movieDTO.getQualification());
    movie.setIdGenero(movieDTO.getIdGenero());

    return movie;
  }
}
