package com.edayavas.enoca_rest_servis.controller;

import com.edayavas.enoca_rest_servis.entity.Adres;
import com.edayavas.enoca_rest_servis.exception.ResourceNotFoundException;
import com.edayavas.enoca_rest_servis.repository.AdresRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/adres")
public class AdresController {

    private final AdresRepository adresRepository;

    public AdresController(AdresRepository adresRepository) {
        this.adresRepository = adresRepository;
    }

    @GetMapping
    public ResponseEntity<List<Adres>> getAllRecords() {
        List<Adres> adresList = adresRepository.findAll();
        return ResponseEntity.ok(adresList);
    }

    @GetMapping("{adresId}")
    public ResponseEntity<Adres> getAdresById(@PathVariable Long adresId) {
        Optional<Adres> optionalAdres = adresRepository.findAdresById(adresId);
        if (!optionalAdres.isPresent()){
            throw new ResourceNotFoundException("Adres is not found");
        }else {
            return ResponseEntity.ok(optionalAdres.get());
        }
    }

    @PostMapping
    public ResponseEntity<Adres> saveAdres(@RequestBody Adres adres) {
        Adres savedAdres = adresRepository.save(adres);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedAdres.getId()).toUri();
        return ResponseEntity.created(location).body(savedAdres);
    }

    @PutMapping("/update/{adresId}")
    public  ResponseEntity<Adres> updateAdres(@PathVariable Long adresId, @RequestBody Adres adres) {
        Optional<Adres> optionalCategory = adresRepository.findAdresById(adresId);
        if (!optionalCategory.isPresent()){
            throw new ResourceNotFoundException("Adres is not found");
        }else {
            adres.setId(optionalCategory.get().getId());
            adresRepository.save(adres);
            return ResponseEntity.ok().body(adres);
        }
    }

    @DeleteMapping("/delete/{adresId}")
    public ResponseEntity<Adres> deleteAdres(@PathVariable Long adresId) {
        Optional<Adres> optionalCategory = adresRepository.findAdresById(adresId);
        if (!optionalCategory.isPresent()) {
            throw new ResourceNotFoundException("Adres is not found");
        } else {
            adresRepository.delete(optionalCategory.get());
            return ResponseEntity.noContent().build();
        }
    }
}
