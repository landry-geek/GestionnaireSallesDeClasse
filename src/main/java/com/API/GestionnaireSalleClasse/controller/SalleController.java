package com.API.GestionnaireSalleClasse.controller;

import com.API.GestionnaireSalleClasse.entity.Salle;
import com.API.GestionnaireSalleClasse.service.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salles")
public class SalleController {

    @Autowired
    private SalleService salleService;

    // Création d'une nouvelle salle
    @PostMapping
    public ResponseEntity<Salle> createSalle(@RequestBody Salle salle) {
        Salle savedSalle = salleService.createSalle(salle);
        return ResponseEntity.status(201).body(savedSalle);
    }

    @GetMapping
    public ResponseEntity<List<Salle>> getAllSalles() {
        List<Salle> salles = salleService.getAllSalles();
        return ResponseEntity.ok(salles);
    }

    // Récupérer une salle par son code
    @GetMapping("/{codesal}")
    public ResponseEntity<Salle> getSalleByCode(@PathVariable String codesal) {
        return salleService.getSalleByCode(codesal)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Recherche d'une salle par désignation
    @GetMapping("/search")
    public ResponseEntity<List<Salle>> searchSalleByDesignation(@RequestParam String designation) {
        List<Salle> salles = salleService.searchSalleByDesignation(designation);
        if (salles.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(salles);
    }

    // Mettre à jour une salle
    @PutMapping("/{codesal}")
    public ResponseEntity<Salle> updateSalle(@PathVariable String codesal, @RequestBody Salle salleDetails) {
        try {
            Salle updatedSalle = salleService.updateSalle(codesal, salleDetails);
            return ResponseEntity.ok(updatedSalle);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer une salle
    @DeleteMapping("/{codesal}")
    public ResponseEntity<Void> deleteSalle(@PathVariable String codesal) {
        salleService.deleteSalle(codesal);
        return ResponseEntity.ok().build();
    }
}
