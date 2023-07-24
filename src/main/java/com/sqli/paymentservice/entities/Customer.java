package com.sqli.paymentservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "accountNoGenerator")
    @SequenceGenerator(name = "accountNoGenerator", sequenceName = "your_sequence_name", initialValue = 338509635, allocationSize = 1)


    @Column
    private Long accountNo;

    @Column
    private String fullName;

    @Column
    private String gender;

    @Column
    private LocalDate birthDate;

    @Column
    private String mobile;

    @Column
    private String email;
}
