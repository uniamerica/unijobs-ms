package com.uniamerica.unijobsbackend.controllers;

import com.uniamerica.unijobsbackend.models.Banner;
import com.uniamerica.unijobsbackend.services.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bannners")
public class BannerController {

    private final BannerService bannerService;

    @PostMapping
    public ResponseEntity<Banner> criarBanner(@RequestBody Banner banner){
        return ResponseEntity.status(HttpStatus.CREATED).body(bannerService.criarBanner(banner));
    }

    @GetMapping
    public ResponseEntity<List<Banner>> listar() {
        return ResponseEntity.ok(bannerService.listarBanner());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Banner> consutarBanner(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok((bannerService.buscarBannerPorId(id)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarBanner(@PathVariable(name = "id") Integer id) {
        bannerService.deleteBanner(id);
        return ResponseEntity.noContent().build();
    }
}
