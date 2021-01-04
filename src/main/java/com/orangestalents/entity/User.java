package com.orangestalents.entity;

import com.orangestalents.dto.request.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
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

    @NotEmpty(message = "campo não pode se nulo")
    @Column(length = 100, nullable = false)
    private String nome;

    @NotEmpty(message = "campo não pode se nulo")
    @CPF
    @Column(length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(length = 10,nullable = false)
    private LocalDate dataNascimento;

    @NotEmpty(message = "campo não pode se nulo")
    @Email
    @Column(nullable = false, unique = true)
    private String email;
}
