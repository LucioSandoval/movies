package com.api.peliculas.service.abstraction;

import com.api.peliculas.dto.request.CharacterDTO;
import com.api.peliculas.dto.response.DetailsCharacterDTO;
import com.api.peliculas.dto.response.ListCharacterDTO;
import java.util.List;

public interface ICharacterService {
  public CharacterDTO create(CharacterDTO characterDTO);
  public List<ListCharacterDTO> list();

  public CharacterDTO update(CharacterDTO characterDTO, long id);
  public void delete(long id);
  public List<DetailsCharacterDTO> detailsCharacter();
  public List<CharacterDTO> characterFindByName(String name);
  public List<CharacterDTO> FindByAge(int age);
  public CharacterDTO findById(long id);

}
