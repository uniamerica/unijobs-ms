package com.uniamerica.unijobsbackend.repositories;


import com.uniamerica.unijobsbackend.models.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BannerRepository extends JpaRepository<Banner, Integer> {
}
