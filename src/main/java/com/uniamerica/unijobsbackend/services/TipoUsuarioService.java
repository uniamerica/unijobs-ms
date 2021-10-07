package com.uniamerica.unijobsbackend.services;

import com.uniamerica.unijobsbackend.models.TipoUsuario;
import com.uniamerica.unijobsbackend.repositories.TipoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoUsuarioService {
    private  TipoUsuarioRepository tipoUsuarioRepository;

    @Autowired
    public TipoUsuarioService(TipoUsuarioRepository tipoUsuarioRepository) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    public TipoUsuario store(TipoUsuario tipoUsuario) {
        return tipoUsuarioRepository.save(tipoUsuario);
    }
    public List<TipoUsuario> index() {
        return tipoUsuarioRepository.findAll();
    }
    public Optional<TipoUsuario> show(Integer id) {
        return tipoUsuarioRepository.findById(id);
    }
    public TipoUsuario update(TipoUsuario tipoUsuario) {
        return tipoUsuarioRepository.save(tipoUsuario);
    }
    public String destroy(Integer id) {
        Optional<TipoUsuario> optionalTipoUsuario = this.show(id);

        if (optionalTipoUsuario.isPresent()) {
            tipoUsuarioRepository.deleteById(id);

        }
        return "Tipo Usuario deletado com sucesso!";
    }


}
