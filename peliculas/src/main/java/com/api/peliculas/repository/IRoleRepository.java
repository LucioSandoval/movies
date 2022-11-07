package com.api.peliculas.repository;

import com.api.peliculas.model.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
  public Optional<Role> findByRol(String rol);

}
