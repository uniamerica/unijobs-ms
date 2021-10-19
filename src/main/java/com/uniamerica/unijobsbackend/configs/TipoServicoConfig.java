package com.uniamerica.unijobsbackend.configs;

import com.uniamerica.unijobsbackend.repositories.TipoServicoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TipoServicoConfig {
    @Bean
    CommandLineRunner commandLineRunner(TipoServicoRepository repository){
        return args -> {
//            TipoServico informatica = new TipoServico("Informatica", "Serviõs relacionados a informática");
//            TipoServico gastronomia = new TipoServico("Gastronomia", "Serviços relacionados a comida");
//
//            repository.saveAll(List.of(informatica, gastronomia));
        };
    }
}
