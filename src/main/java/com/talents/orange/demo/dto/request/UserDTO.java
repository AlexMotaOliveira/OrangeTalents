package com.talents.orange.demo.dto.request;


import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {


    private Long id;

    @NotEmpty
    @Size(min = 1, max = 100)
    @Pattern(regexp="^[a-zA-Z0-9]{3}",message="Nome deve conter somente caracteres")
    private String nome;

    @NotEmpty
    @Size(min = 1, max = 100)
    private String dataNascimento;

    @NotEmpty
    @CPF
    private String cpf;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty(message = "Defina um senha")
    @Size(min = 1, max = 100)
    private String senha;

}
