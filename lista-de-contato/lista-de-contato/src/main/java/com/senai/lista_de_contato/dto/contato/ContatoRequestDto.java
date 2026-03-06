package com.senai.lista_de_contato.dto.contato;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ContatoRequestDto (
        @NotBlank(message = "O nome do contato é obrigatório")
        @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
        String nome,

        @NotBlank(message = "O número de telefone é obrigatório")
        @Pattern(regexp = "^\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}$",
                message = "O número deve estar no formato (XX) 9XXXX-XXXX ou XXXXXXXXXXX")
        String numero
){}