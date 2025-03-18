package com.API.GestionnaireSalleClasse.service;

import com.API.GestionnaireSalleClasse.entity.Salle;
import com.API.GestionnaireSalleClasse.repository.SalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalleService {

    @Autowired
    private SalleRepository salleRepository;

    public Salle createSalle(Salle salle) {
        return salleRepository.save(salle);
    }

    public Optional<Salle> getSalleByCode(String codesal) {
        return salleRepository.findById(codesal);
    }

    public List<Salle> searchSalleByDesignation(String designation) {
        return salleRepository.findByDesignationContainingIgnoreCase(designation);
    }

    public List<Salle> getAllSalles() {
        return salleRepository.findAll();
    }

    public Salle updateSalle(String codesal, Salle salleDetails) {
        return salleRepository.findById(codesal).map(salle -> {
            salle.setDesignation(salleDetails.getDesignation());
            return salleRepository.save(salle);
        }).orElseThrow(() -> new RuntimeException("Salle non trouvée"));
    }

    public void deleteSalle(String codesal) {
        salleRepository.findById(codesal).ifPresentOrElse(
                salleRepository::delete,
                () -> { throw new RuntimeException("Salle non trouvée"); }
        );
    }
}
