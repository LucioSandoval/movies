package com.api.peliculas.security;

import com.api.peliculas.model.Role;
import com.api.peliculas.model.User;
import com.api.peliculas.repository.IUserRepository;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
/*import org.springframework.security.core.userdetails.User;*/
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService{

  @Autowired
  private IUserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    User user = userRepository.findByEmail( email)
        .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado  con email : " + email));
    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapearRoles(user.getRoles()));
  }

  private Collection<? extends GrantedAuthority> mapearRoles(Set<Role> roles){
    return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getRol())).collect(Collectors.toList());
  }
}
