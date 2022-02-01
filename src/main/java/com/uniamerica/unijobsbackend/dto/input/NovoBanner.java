package com.uniamerica.unijobsbackend.dto.input;

import com.uniamerica.unijobsbackend.models.Banner;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;


@Data
public class NovoBanner {

    @NotBlank(message = "O Título é obrigatório.")
    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String img;

    @NotBlank(message = "O Título é obrigatório.")
    @Column(nullable = false)
    private String discricao;

    public Banner converteModelo() {
        Banner banner = new Banner();
        banner.setId(banner.getId());
        banner.setDiscricao(banner.getDiscricao());
        banner.setImg(img.getBytes().toString());
        return banner;
    }
}
