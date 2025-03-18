package com.API.GestionnaireSalleClasse.repository;

import com.API.GestionnaireSalleClasse.entity.Salle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalleRepository extends JpaRepository<Salle, String> {
    // Recherche des salles par désignation
    List<Salle> findByDesignationContainingIgnoreCase(String designation);
}

