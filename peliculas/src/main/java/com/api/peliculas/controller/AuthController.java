package com.api.peliculas.controller;

import com.api.peliculas.dto.request.LoginDTO;
import com.api.peliculas.dto.request.RegisterDTO;
import com.api.peliculas.dto.response.RegisterDTOResponse;
import com.api.peliculas.exception.EmailException;
import com.api.peliculas.service.abstraction.IUserService;
import java.io.IOException;
import javax.validation.Valid;
import org.apache.http.auth.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

  @Autowired
  private IUserService userService;

  @PostMapping(value = "/login",  consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<RegisterDTOResponse> login(@RequestBody LoginDTO loginDTO)
      throws InvalidCredentialsException, EmailException {

    return ResponseEntity.ok(userService.login(loginDTO));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping(value = "/register",  consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<RegisterDTO> register(@Valid @RequestBody RegisterDTO registerDTO)
      throws EmailException, IOException {
    return new ResponseEntity<>(userService.create(registerDTO), HttpStatus.CREATED);

  }


}
