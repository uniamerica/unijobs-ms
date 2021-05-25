package com.uniamerica.unijobsbackend.repository;

import com.uniamerica.unijobsbackend.model.TipoUsuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoUsuarioRepository  extends JpaRepository<TipoUsuario, Integer> {
}
