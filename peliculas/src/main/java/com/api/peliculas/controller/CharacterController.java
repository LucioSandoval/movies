package com.api.peliculas.controller;

import com.api.peliculas.dto.request.CharacterDTO;
import com.api.peliculas.dto.response.DetailsCharacterDTO;
import com.api.peliculas.dto.response.ListCharacterDTO;
import com.api.peliculas.service.abstraction.ICharacterService;

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
public class CharacterController {

  @Autowired
  private ICharacterService characterService;

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping(value = "/characters", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CharacterDTO> save(@Valid @RequestBody CharacterDTO characterDTO) {

    return new ResponseEntity<>(characterService.create(characterDTO), HttpStatus.CREATED);

  }

  @GetMapping(value = "/characters", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<ListCharacterDTO> findAll() {

    return characterService.list();
  }


  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping(value = "/characters/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CharacterDTO> update( @Valid @RequestBody CharacterDTO characterDTO,
      @PathVariable(value = "id") long id) {

    return new ResponseEntity<>(characterService.update(characterDTO, id), HttpStatus.OK);
  }


  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping(value = "/characters/{id}")
  public ResponseEntity<String> delete(@PathVariable(value = "id") long id) {
    characterService.delete(id);

    return new ResponseEntity<>("Character eliminado con exito", HttpStatus.OK);
  }

  @GetMapping(value = "/characterss", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<DetailsCharacterDTO> details(){

    return characterService.detailsCharacter();
  }

  @GetMapping(value = "/characters",params = {"name"}, produces = MediaType.APPLICATION_JSON_VALUE)
  public List<CharacterDTO> listFindByName(@RequestParam(value = "name") String name){
    return characterService.characterFindByName(name);

  }

  @GetMapping(value = "/characters", params = {"age"},produces = MediaType.APPLICATION_JSON_VALUE)
  public List<CharacterDTO> listFindByAge(@RequestParam(value = "age") int age) {

    return characterService.FindByAge(age);
  }

  @GetMapping(value = "/characters", params = {"id"},produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CharacterDTO> listFindById(@RequestParam(value = "id") long id) {

    return new ResponseEntity<>(characterService.findById(id), HttpStatus.OK);
  }
}
