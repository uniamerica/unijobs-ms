package com.uniamerica.unijobsbackend.configs;

import com.uniamerica.unijobsbackend.dto.ProdutoDTO;
import com.uniamerica.unijobsbackend.models.Produto;
import com.uniamerica.unijobsbackend.models.TipoProduto;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        var modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Produto.class, ProdutoDTO.class)
                .<TipoProduto>addMapping(src -> src.getTipoProduto(),
                        (dest, value) -> dest.setTipoProduto(value));
       return modelMapper;
    }

}
