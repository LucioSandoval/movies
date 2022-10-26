package com.api.peliculas.repository;

import com.api.peliculas.dto.request.CharacterDTO;
import com.api.peliculas.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICharacterRepository extends JpaRepository<Character, Long> {

}
