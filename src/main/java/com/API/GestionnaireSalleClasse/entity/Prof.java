/*
* Donc Data maping gère la transformation et correspondance d'un objet et sa classe(entité)
* en tables et colonnes ;
* et Persistance gère l'enregistrement, le stockage et la récupération ?
* */
package com.API.GestionnaireSalleClasse.entity;//package name

import jakarta.persistence.*;// Importation des annotations JPA nécessaires pour la gestion des entités
import lombok.*;  // Importation des annotations de Lombok pour réduire le boilerplate code

@Entity  // Cette annotation indique que cette classe est une entité JPA, pour le mapping
@Data  // Annotation de Lombok qui génère automatiquement les méthodes getter, setter, toString, equals, hashCode, etc.
//Donc il n'est plus nécessaire de faire les getters et setters de propiétés de cette entité
@NoArgsConstructor  // Génère un constructeur sans arguments.
@AllArgsConstructor  // Génère un constructeur avec tous les arguments.
public class Prof {

    @Id  // Annotation JPA qui définit la clé primaire de l'entité (ici, `codeProf`).
    private String codeProf;

    private String nom;
    private String prenom;
    private String grade;
}
