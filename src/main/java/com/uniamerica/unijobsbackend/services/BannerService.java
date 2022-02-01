package com.uniamerica.unijobsbackend.services;


import com.uniamerica.unijobsbackend.models.Banner;
import com.uniamerica.unijobsbackend.repositories.BannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BannerService {

    private final BannerRepository bannerRepository;

    public List<Banner> listarBanner(){
        return bannerRepository.findAll();
    }

    public Banner criarBanner(Banner banner){
        return bannerRepository.save(banner);
    }

    public Banner buscarBannerPorId(Integer id) {
        return bannerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Banner não encontrado")
        );
    }
    public Banner atualizarBanner(Integer id, Banner update) {
        var banner = this.buscarBannerPorId(id);
        update.setId((banner.getId()));
        return bannerRepository.save(update);
    }

    public void deleteBanner(Integer id) {
        buscarBannerPorId(id);
        bannerRepository.deleteById(id);
    }

}
