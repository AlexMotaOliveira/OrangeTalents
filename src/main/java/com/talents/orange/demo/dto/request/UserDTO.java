package com.talents.orange.demo.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;



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
