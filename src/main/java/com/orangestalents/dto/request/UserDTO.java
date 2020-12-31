package com.orangestalents.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
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
    @Column(unique = true)
    private String cpf;

    @NotEmpty
    @Size(min = 1, max = 100)
    private String dataNascimento;

    @NotEmpty
    @Email
    @Size(min = 1, max = 100)
    @Column(nullable = false, unique = true)
    private String email;
}
