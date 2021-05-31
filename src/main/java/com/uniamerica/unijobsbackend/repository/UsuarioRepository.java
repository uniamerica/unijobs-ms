package com.uniamerica.unijobsbackend.repository;

import com.fasterxml.jackson.annotation.OptBoolean;
import com.uniamerica.unijobsbackend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);
}
