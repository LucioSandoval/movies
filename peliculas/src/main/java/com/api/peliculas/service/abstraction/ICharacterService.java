package com.api.peliculas.service.abstraction;

import com.api.peliculas.dto.request.CharacterDTO;
import com.api.peliculas.dto.response.ListCharacterDTO;
import java.util.List;

public interface ICharacterService {
  public CharacterDTO create(CharacterDTO characterDTO);
  public List<ListCharacterDTO> list();
  public CharacterDTO findById(long id);
  public CharacterDTO update(CharacterDTO characterDTO, long id);
  public void delete(long id);

}
