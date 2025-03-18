/*
* Service : contient la logique métier
* */
package com.API.GestionnaireSalleClasse.service;

import com.API.GestionnaireSalleClasse.entity.Prof;
import com.API.GestionnaireSalleClasse.repository.ProfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfService {

    @Autowired
    private ProfRepository profRepository;

    public Prof createProf(Prof prof) {
        return profRepository.save(prof);
    }

    public List<Prof> getAllProfs() {
        return profRepository.findAll();
    }

    public Optional<Prof> getProfByCode(String codeProf) {
        return profRepository.findByCodeProf(codeProf);
    }

    public List<Prof> searchProfByNom(String nom) {
        return profRepository.findByNomContainingIgnoreCase(nom);
    }

    public Prof updateProf(String codeProf, Prof profDetails) {
        return profRepository.findByCodeProf(codeProf).map(prof -> {
            prof.setNom(profDetails.getNom());
            prof.setPrenom(profDetails.getPrenom());
            prof.setGrade(profDetails.getGrade());
            return profRepository.save(prof);
        }).orElseThrow(() -> new RuntimeException("Professeur non trouvé"));
    }

    public void deleteProf(String codeProf) {
        profRepository.findByCodeProf(codeProf).ifPresentOrElse(
                profRepository::delete,
                () -> { throw new RuntimeException("Professeur non trouvé"); }
        );
    }
}