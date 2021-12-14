package com.uniamerica.unijobsbackend.services;


import com.uniamerica.unijobsbackend.models.Perfil;
import com.uniamerica.unijobsbackend.repositories.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilService {

    private final PerfilRepository perfilRepository;

    @Autowired
    public PerfilService(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }
    public Perfil store(Perfil perfil) {
        return perfilRepository.save(perfil);
    }
    public List<Perfil> index() {
        return perfilRepository.findAll();
    }

    public Optional<Perfil> show(Integer id) {
        return perfilRepository.findById(id);
    }
    public Perfil update(Perfil perfil) {
        return perfilRepository.save(perfil);
    }
    public void destroy(Integer id) {
        Optional<Perfil> optionalPerfil = this.show(id);

        if (optionalPerfil.isPresent()) {
            perfilRepository.deleteById(id);
        }
    }
}
