package com.uniamerica.unijobsbackend.configs;

import com.uniamerica.unijobsbackend.dto.ListaProdutoDTO;
import com.uniamerica.unijobsbackend.models.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        var modelMapper = new ModelMapper();
//        modelMapper.createTypeMap(Produto.class, ListaProdutoDTO.class)
//                .<String>addMapping(src -> src.getTipoProduto().getNome(),
//                        (dest, value) -> dest.setTipoProduto(value));
       return modelMapper;
    }

}
