package com.API.GestionnaireSalleClasse.controller;

import com.API.GestionnaireSalleClasse.entity.Prof;
import com.API.GestionnaireSalleClasse.service.ProfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profs")
public class ProfController {

    @Autowired
    private ProfService profService;

    // Création d'un nouveau prof
    @PostMapping
    public ResponseEntity<Prof> createProf(@RequestBody Prof prof) {
        Prof savedProf = profService.createProf(prof);
        return ResponseEntity.status(201).body(savedProf);
    }

    @GetMapping
    public List<Prof> getAllProfs() {
        return profService.getAllProfs();
    }

    // Récupérer un prof par son code
    @GetMapping("/{codeProf}")
    public ResponseEntity<Prof> getProfByCode(@PathVariable String codeProf) {
        return profService.getProfByCode(codeProf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Rechercher un prof par nom
    @GetMapping("/search")
    public ResponseEntity<List<Prof>> searchProfByNom(@RequestParam String nom) {
        List<Prof> profs = profService.searchProfByNom(nom);
        return ResponseEntity.ok(profs);
    }

    // Mettre à jour un prof
    @PutMapping("/{codeProf}")
    public ResponseEntity<Prof> updateProf(@PathVariable String codeProf, @RequestBody Prof profDetails) {
        try {
            Prof updatedProf = profService.updateProf(codeProf, profDetails);
            return ResponseEntity.ok(updatedProf);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer un prof
    @DeleteMapping("/{codeProf}")
    public ResponseEntity<Void> deleteProf(@PathVariable String codeProf) {
        profService.deleteProf(codeProf);
        return ResponseEntity.ok().build();
    }
}
