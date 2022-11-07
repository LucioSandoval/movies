package com.api.peliculas.repository;

import com.api.peliculas.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
  public Optional<User> findByEmail(String email);
  /*public Optional<User> findByUsernameOrEmail(String username, String email);*/
 /* public Optional<User> findByUsername(String username);*/
  /*public Boolean existsByUsername(String username);*/
  public Boolean existsByEmail(String email);
}
