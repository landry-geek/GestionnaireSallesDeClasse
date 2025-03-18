package com.API.GestionnaireSalleClasse.repository;

import com.API.GestionnaireSalleClasse.entity.Occuper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OccuperRepository extends JpaRepository<Occuper, Long> {
    // Recherche d'une occupation par codeProf, codesal et date
    Optional<Occuper> findByProf_CodeProfAndSalle_CodesalAndDate(String codeProf, String codesal, java.sql.Date date);
}
