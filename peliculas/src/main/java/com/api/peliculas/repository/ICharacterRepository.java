package com.api.peliculas.repository;

import com.api.peliculas.dto.request.CharacterDTO;
import com.api.peliculas.model.Character;
import com.api.peliculas.model.Movie;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

public interface ICharacterRepository extends JpaRepository<Character, Long> {

  public List<Character> findByName(@Param("name") String name);
  public List<Character> findByAge(@Param("age") int age);
  public List<Character> findByAge(@Param("id") long id);


}
