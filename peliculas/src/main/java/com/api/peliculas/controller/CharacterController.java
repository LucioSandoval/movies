package com.api.peliculas.controller;

import com.api.peliculas.dto.request.CharacterDTO;
import com.api.peliculas.dto.response.ListCharacterDTO;
import com.api.peliculas.service.abstraction.ICharacterService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CharacterController {

  @Autowired
  private ICharacterService characterService;

  @PostMapping(value = "/characters", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CharacterDTO> save(@RequestBody CharacterDTO characterDTO) {

    return new ResponseEntity<>(characterService.create(characterDTO), HttpStatus.CREATED);

  }

  @GetMapping(value = "/characters", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<ListCharacterDTO> findAll() {

    return characterService.list();
  }

  @GetMapping(value = "/characters/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CharacterDTO> findById(@PathVariable(value = "id") long id) {

    return new ResponseEntity<>(characterService.findById(id), HttpStatus.OK);
  }

  @PutMapping(value = "/characters/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CharacterDTO> update(@RequestBody CharacterDTO characterDTO,
      @PathVariable(value = "id") long id) {

    return new ResponseEntity<>(characterService.update(characterDTO, id), HttpStatus.OK);
  }

  @DeleteMapping(value = "/characters/{id}")
  public ResponseEntity<String> delete(@PathVariable(value = "id") long id) {
    characterService.delete(id);

    return new ResponseEntity<>("Character eliminado con exito", HttpStatus.OK);
  }

}
