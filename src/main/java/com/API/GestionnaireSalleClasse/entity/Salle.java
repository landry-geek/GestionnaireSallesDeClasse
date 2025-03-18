package com.API.GestionnaireSalleClasse.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Salle {
    @Id
    private String codesal;

    private String designation;
}
