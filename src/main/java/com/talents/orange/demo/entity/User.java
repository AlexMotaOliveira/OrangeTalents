package com.talents.orange.demo.entity;


import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;


import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "app_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements Serializable {


    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotEmpty(message ="O nome não pode ser nulo")
    @Column(length = 100, nullable = false)
    private String nome;

    //TODO corrigir tipo de data
    @NotEmpty(message ="A Data nao pode estar vazio")
    private String dataNascimento;

    @NotEmpty(message ="O CPF nao pode estar vazio")
    @Column(length = 11, nullable = false, unique = true)
    @CPF(message = "CPF invalido")
    private String cpf;

    @NotEmpty(message = "O email nao pode ser vazio")
    @Email(message = "Email invalido")
    @Column(unique = true)
    private String email;

    @NotEmpty(message = "Defina um senha")
    @Size(max = 80, message = "A senha é muito longa")
    private String senha;

}
