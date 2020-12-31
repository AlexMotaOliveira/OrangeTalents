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
    private String nome;

    @NotEmpty
    @CPF
    private String cpf;

    @NotEmpty
    @Size(min = 1, max = 100)
    private String dataNascimento;

    @NotEmpty
    @Email
    private String email;


}
