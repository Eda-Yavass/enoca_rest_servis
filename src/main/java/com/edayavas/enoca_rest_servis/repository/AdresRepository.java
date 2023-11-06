package com.edayavas.enoca_rest_servis.repository;

import com.edayavas.enoca_rest_servis.entity.Adres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdresRepository extends JpaRepository<Adres, Long> {
    Optional<Adres> findAdresById(Long id);
}
