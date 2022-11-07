package com.api.peliculas.service.implementation;

import com.api.peliculas.dto.request.JwtAuthResponseDTO;
import com.api.peliculas.dto.request.LoginDTO;
import com.api.peliculas.dto.request.RegisterDTO;
import com.api.peliculas.dto.response.RegisterDTOResponse;
import com.api.peliculas.email.SendEmail;
import com.api.peliculas.exception.EmailException;
import com.api.peliculas.model.Role;
import com.api.peliculas.model.User;
import com.api.peliculas.repository.IRoleRepository;
import com.api.peliculas.repository.IUserRepository;
import com.api.peliculas.security.JwtTokenProvider;
import com.api.peliculas.service.abstraction.IUserService;
import com.api.peliculas.validation.EmailValidation;
import com.api.peliculas.validation.PasswordValidation;
import java.io.IOException;
import java.util.Collections;
import org.apache.http.auth.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

  @Autowired
  private IUserRepository userRepository;

  @Autowired
  IRoleRepository roleRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private SendEmail sendEmail;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;


  @Override
  public RegisterDTOResponse login(LoginDTO loginDTO) throws InvalidCredentialsException, EmailException {
    if (!EmailValidation.isValid(loginDTO.getEmail())
        || !PasswordValidation.isValid(loginDTO.getPassword())) {
      throw new InvalidCredentialsException("Usuario o contraseña invalida.");
    }


    User user = userRepository.findByEmail(loginDTO.getEmail())
        .orElseThrow( () -> new UsernameNotFoundException("Usuario no encontrado con email: "+ loginDTO.getEmail()));


    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);

    //Obtener el token y el jwtTokenProvider
    String token = jwtTokenProvider.generateToken(authentication);
    /*new JwtAuthResponseDTO(token);*/

    return new RegisterDTOResponse(
      user.getId(),
        user.getName(),
        user.getEmail(),
        user.getPassword(),
        token

    );
  }

  @Override
  public RegisterDTO create(RegisterDTO registerDTO) throws EmailException, IOException {

    if(userRepository.existsByEmail(registerDTO.getEmail())){
      throw new EmailException();
    }

    User user = new User();
    user.setName(registerDTO.getName());
    user.setEmail(registerDTO.getEmail());
    user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
    Role roles = roleRepository.findByRol("ROLE_ADMIN").get();
    user.setRoles(Collections.singleton(roles));

    User newUser = userRepository.save(user);
    /*sendEmail.send(newUser.getEmail());*/

    return mapearRegisterDTO(newUser);
  }

  private RegisterDTO mapearRegisterDTO (User user){

    RegisterDTO registerDTO = new RegisterDTO();
    registerDTO.setName(user.getName());
    registerDTO.setEmail(user.getEmail());
    registerDTO.setPassword(user.getPassword());
    return registerDTO;
  }

}
