package com.uniamerica.unijobsbackend.tipoServico;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TipoServicoConfig {
    @Bean
    CommandLineRunner commandLineRunner(TipoServicoRepository repository){
        return args -> {
            TipoServico formatar = new TipoServico("formatar", "Formatar computador");
            TipoServico limpeza = new TipoServico("limpeza", "Limpeza de algo");

            repository.saveAll(List.of(formatar, limpeza));
        };
    }
}
