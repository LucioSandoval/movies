package com.api.peliculas.service.abstraction;

import com.api.peliculas.dto.request.LoginDTO;
import com.api.peliculas.dto.request.RegisterDTO;
import com.api.peliculas.dto.response.RegisterDTOResponse;
import com.api.peliculas.exception.EmailException;
import java.io.IOException;
import org.apache.http.auth.InvalidCredentialsException;

public interface IUserService {
  public RegisterDTOResponse login(LoginDTO loginDTO) throws InvalidCredentialsException, EmailException;
  public RegisterDTO create(RegisterDTO registerDTO) throws EmailException, IOException;

}
