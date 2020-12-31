package com.talents.orange.demo.entity;


import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;


import java.io.Serializable;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "app_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {


    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotEmpty
    @Column(length = 100, nullable = false)
    private String nome;

    @NotEmpty
    @Column(length = 11, nullable = false, unique = true)
    @CPF(message = "CPF invalido")
    private String cpf;

    private LocalDate dataNascimento;

    @NotEmpty
    @Email
    @Column(unique = true)
    private String email;

}
