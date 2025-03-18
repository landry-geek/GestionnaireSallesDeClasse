package com.API.GestionnaireSalleClasse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Occuper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "codeprof", referencedColumnName = "codeProf")
    private Prof prof;

    @ManyToOne
    @JoinColumn(name = "codesal", referencedColumnName = "codeSal") // Assure-toi que le nom de la colonne dans la table Salle est bien "codeSal"
    private Salle salle;

    @Temporal(TemporalType.DATE)
    private Date date;
}
