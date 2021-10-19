package com.uniamerica.unijobsbackend.repositories;

import com.uniamerica.unijobsbackend.models.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoUsuarioRepository  extends JpaRepository<TipoUsuario, Integer> {
}
