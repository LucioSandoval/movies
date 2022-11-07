package com.api.peliculas.service.implementation;

import com.api.peliculas.dto.request.CharacterDTO;
import com.api.peliculas.dto.response.DetailsCharacterDTO;
import com.api.peliculas.dto.response.ListCharacterDTO;
import com.api.peliculas.dto.request.MovieDTO;
import com.api.peliculas.dto.response.ListMovieDTO;
import com.api.peliculas.exception.ResourceNotFoundException;
import com.api.peliculas.model.Character;
import com.api.peliculas.model.Movie;
import com.api.peliculas.repository.ICharacterRepository;
import com.api.peliculas.repository.IMovieRepository;
import com.api.peliculas.service.abstraction.ICharacterService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterServiceImpl implements ICharacterService {

  @Autowired
  private ICharacterRepository characterRepository;

 /* @Autowired
  private IMovieRepository movieRepository;*/

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public CharacterDTO create(CharacterDTO characterDTO) {

    Character character = mapearEntidad(characterDTO);

    Character newCharacter = characterRepository.save(character);
    CharacterDTO characterDTOResponse = mapearDTO(newCharacter);
    return characterDTOResponse;
  }


  @Override
  public List<ListCharacterDTO> list() {
    List<Character> characterList = characterRepository.findAll();
    List<ListCharacterDTO> listCharacterDTO = characterList.stream()
        .map((personaje) -> mapLisCharacterDTO(personaje)).collect(Collectors.toList());
    return listCharacterDTO;
  }


  @Override
  public CharacterDTO update(CharacterDTO characterDTO, long id) {
    //Buscamos el id
    Character character = characterRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Character", "id", id));

    //seteamos los campos
    character.setImage(characterDTO.getImage());
    character.setName(characterDTO.getName());
    character.setAge(characterDTO.getAge());
    character.setWeighs(characterDTO.getWeighs());
    character.setHistory(characterDTO.getHistory());

    // Guardamos los cambios
    Character newCharacter = characterRepository.save(character);

    //Convertimos el moledo en DTO y lo retonamos el DTO
    return mapearDTO(newCharacter);
  }

  @Override
  public void delete(long id) {
    Character character = characterRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Character", "id", id));
    characterRepository.delete(character);
  }

  @Override
  public List<DetailsCharacterDTO> detailsCharacter() {

    List<Character> characterList = characterRepository.findAll();
    List<DetailsCharacterDTO> returnDetailsCharacterDTO = characterList.stream()
        .map((character) -> meaparDetailsDTO(character)).collect(Collectors.toList());
    return returnDetailsCharacterDTO;
  }

  @Override
  public List<CharacterDTO> characterFindByName(String name) {
    List<Character> characterList = characterRepository.findByName(name);

    return characterList.stream().map( (character) -> mapearDTO(character)).collect(Collectors.toList());
  }

  @Override
  public List<CharacterDTO> FindByAge(int age) {
    List<Character> characterList = characterRepository.findByAge(age);

    return characterList.stream().map( (character) -> mapearDTO(character)).collect(Collectors.toList());
  }

  @Override
  public CharacterDTO findById(long id) {
    Character character = characterRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Character", "id", id));
    return mapearDTO(character);
  }

  private DetailsCharacterDTO meaparDetailsDTO(Character character) {
    DetailsCharacterDTO detailsCharacterDTO = new DetailsCharacterDTO();
    detailsCharacterDTO.setId(character.getId());
    detailsCharacterDTO.setImage(character.getImage());
    detailsCharacterDTO.setName(character.getName());
    detailsCharacterDTO.setAge(character.getAge());
    detailsCharacterDTO.setWeighs(character.getWeighs());
    detailsCharacterDTO.setHistory(character.getHistory());

    Set<Movie> movieList = character.getMovies();
    Set<MovieDTO> movieDTOS = new HashSet<>();

    for (Movie movie : movieList) {
      movieDTOS.add(mepearMovieDTO(movie));
    }

    detailsCharacterDTO.setMovies(movieDTOS);
    return detailsCharacterDTO;
  }


  private MovieDTO mepearMovieDTO(Movie movie) {
    MovieDTO listMovieDTO = new MovieDTO();
    listMovieDTO.setId(movie.getId());
    listMovieDTO.setImage(movie.getImage());
    listMovieDTO.setTitle(movie.getTitle());
    listMovieDTO.setQualification(movie.getQualification());
    listMovieDTO.setIdGenero(movie.getIdGenero());
    return listMovieDTO;
  }

  // Convierte entidad a DTO
  private CharacterDTO mapearDTO(Character character) {
    CharacterDTO characterDTO = modelMapper.map(character, CharacterDTO.class);
    return characterDTO;
  }

  // Convierte de DTO a Entidad
  private Character mapearEntidad(CharacterDTO characterDTO) {
    Character character = modelMapper.map(characterDTO, Character.class);
    return character;
  }

  private ListCharacterDTO mapLisCharacterDTO(Character character) {
    ListCharacterDTO listCharacterDTO = new ListCharacterDTO();

    listCharacterDTO.setImage(character.getImage());
    listCharacterDTO.setName(character.getName());

    return listCharacterDTO;
  }


}
