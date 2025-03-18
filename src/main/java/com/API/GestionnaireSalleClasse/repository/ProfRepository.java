/*
* Nos Repository etendent de l'interface JpaRepository qui contient
* des méthodes CRUD déjà prêtes, toutefois, on peut ajouter de méthodes
* de recherche personnalisées
* */
package com.API.GestionnaireSalleClasse.repository;

import com.API.GestionnaireSalleClasse.entity.Prof;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional; /*Optional est un conteneur qui encapsule une valeur pouvant être présente
ou absente, évitant ainsi l'utilisation de null et les NullPointerException*/

public interface ProfRepository extends JpaRepository<Prof, String>{
    // Recherche d'un prof par son code (la clé primaire est déjà gérée par JpaRepository)
    Optional<Prof> findByCodeProf(String codeProf);

    // Recherche par nom ("Containing" pour une recherche partielle insensible à la casse)
    List<Prof> findByNomContainingIgnoreCase(String nom);
}
