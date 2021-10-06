package com.uniamerica.unijobsbackend.repositories;

import com.uniamerica.unijobsbackend.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {


//    @Query("" +
//    "SELECT CASE WHEN COUNT(user) > 0 THEN " +
//    "TRUE ELSE FALSE END " +
//    "FROM Usuario user " +
//    "WHERE user.email = ?1"
//    )
//    Boolean selectExistEmail(String email);
    Optional<Usuario> findByEmail(String email);
}
