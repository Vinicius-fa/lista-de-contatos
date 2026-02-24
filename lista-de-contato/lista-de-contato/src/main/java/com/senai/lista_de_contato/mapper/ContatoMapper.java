package com.senai.lista_de_contato.mapper;

import com.senai.lista_de_contato.dto.contato.ContatoRequestDto;
import com.senai.lista_de_contato.dto.contato.ContatoResponseDto;
import com.senai.lista_de_contato.model.Contato;
import org.springframework.stereotype.Component;

@Component
public class ContatoMapper {

    public Contato toEntity(
            ContatoRequestDto requestDto
    ){
        return  new Contato(
                requestDto.nome(),
                requestDto.numero()
        );
    }

    public ContatoResponseDto toResponse(
            Contato contato
    ){
        return new ContatoResponseDto(
                contato.getId(),
                contato.getNome(),
                contato.getNumero()
        );
    }
}