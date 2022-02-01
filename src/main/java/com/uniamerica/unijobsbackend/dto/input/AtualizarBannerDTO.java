package com.uniamerica.unijobsbackend.dto.input;

import com.uniamerica.unijobsbackend.models.Banner;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
public class AtualizarBannerDTO {

    @NotNull(message = "O nome é obrigatório")
    private String nome;

    @Column(nullable = false)
    private String img;

    @NotNull(message = "O nome é obrigatório")
    private String discricao;

}
