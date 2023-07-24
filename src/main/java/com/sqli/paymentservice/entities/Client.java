package com.sqli.paymentservice.entities;



import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Client {
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "accountNoGenerator")
        @SequenceGenerator(name = "accountNoGenerator", sequenceName = "your_sequence_name", initialValue = 338509635, allocationSize = 1)

        @Column (name = "id", nullable = false, unique = true)
        private Long id;

        private String nom;

        private String prenom;

        private String adresse;

        private String numeroCompte;

        private BigDecimal solde;
}
