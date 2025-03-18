package com.API.GestionnaireSalleClasse.controller;

import com.API.GestionnaireSalleClasse.entity.Occuper;
import com.API.GestionnaireSalleClasse.service.OccuperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/occupations")
public class OccuperController {

    private final OccuperService occuperService;

    @Autowired
    public OccuperController(OccuperService occuperService) {
        this.occuperService = occuperService;
    }

    @PostMapping
    public ResponseEntity<Occuper> createOccupation(@RequestBody Occuper occupation) {
        // Appeler le service pour créer l'occupation
        Occuper createdOccupation = occuperService.createOccupation(occupation);

        // Retourner l'occupation créée dans la réponse
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOccupation);
    }

    // Récupérer une occupation par son id
    @GetMapping("/{id}")
    public ResponseEntity<Occuper> getOccupationById(@PathVariable Long id) {
        return occuperService.getOccupationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Récupérer toutes les occupations
    @GetMapping
    public ResponseEntity<List<Occuper>> getAllOccupations() {
        List<Occuper> occupations = occuperService.getAllOccupations();
        return ResponseEntity.ok(occupations);
    }

    // Mettre à jour une occupation existante
    @PutMapping("/{id}")
    public ResponseEntity<Occuper> updateOccupation(
            @PathVariable Long id,
            @RequestBody Occuper updatedOccupation) {

        // Vérifier si l'occupation existe et procédez à la mise à jour
        try {
            Occuper existingOccupation = occuperService.updateOccupation(id, updatedOccupation);
            return ResponseEntity.ok(existingOccupation);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();  // Si l'occupation n'est pas trouvée
        }
    }

    // Supprimer une occupation par son id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOccupation(@PathVariable Long id) {
        occuperService.deleteOccupation(id);
        return ResponseEntity.ok().build();
    }
}
