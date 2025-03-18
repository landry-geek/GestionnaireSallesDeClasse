package com.API.GestionnaireSalleClasse.service;

import com.API.GestionnaireSalleClasse.entity.Occuper;
import com.API.GestionnaireSalleClasse.entity.Prof;
import com.API.GestionnaireSalleClasse.entity.Salle;
import com.API.GestionnaireSalleClasse.repository.OccuperRepository;
import com.API.GestionnaireSalleClasse.repository.ProfRepository;
import com.API.GestionnaireSalleClasse.repository.SalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OccuperService {

    private final ProfRepository profRepository;
    private final SalleRepository salleRepository;
    private final OccuperRepository occuperRepository;

    @Autowired
    public OccuperService(ProfRepository profRepository, SalleRepository salleRepository, OccuperRepository occuperRepository) {
        this.profRepository = profRepository;
        this.salleRepository = salleRepository;
        this.occuperRepository = occuperRepository;
    }

    public Occuper createOccupation(Occuper occupation) {
        // Récupérer l'objet Prof et Salle en fonction des clés étrangères
        Prof prof = profRepository.findById(occupation.getProf().getCodeProf())
                .orElseThrow(() -> new RuntimeException("Prof not found"));
        Salle salle = salleRepository.findById(occupation.getSalle().getCodesal())
                .orElseThrow(() -> new RuntimeException("Salle not found"));

        // Associer les objets récupérés avec l'objet Occuper
        occupation.setProf(prof);
        occupation.setSalle(salle);

        // Sauvegarder l'objet Occuper dans la base de données
        return occuperRepository.save(occupation);
    }

    public Optional<Occuper> getOccupationById(Long id) {
        return occuperRepository.findById(id);
    }

    public List<Occuper> getAllOccupations() {
        return occuperRepository.findAll();
    }

    // Mettre à jour l'occupation
    public Occuper updateOccupation(Long id, Occuper occupationDetails) {
        return occuperRepository.findById(id).map(occupation -> {
            occupation.setProf(occupationDetails.getProf());
            occupation.setSalle(occupationDetails.getSalle());
            occupation.setDate(occupationDetails.getDate());

            // Récupérer le codeProf du Prof associé à l'occupation
            String codeProf = occupation.getProf().getCodeProf(); // Pas besoin de définir getCodeProf() explicitement

            return occuperRepository.save(occupation);
        }).orElseThrow(() -> new RuntimeException("Occupation non trouvée"));
    }

    public void deleteOccupation(Long id) {
        occuperRepository.findById(id).ifPresentOrElse(
                occuperRepository::delete,
                () -> { throw new RuntimeException("Occupation non trouvée"); }
        );
    }
}
