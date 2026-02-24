package com.senai.lista_de_contato.service;

import com.senai.lista_de_contato.dto.contato.ContatoRequestDto;
import com.senai.lista_de_contato.dto.contato.ContatoResponseDto;
import com.senai.lista_de_contato.mapper.ContatoMapper;
import com.senai.lista_de_contato.model.Contato;
import com.senai.lista_de_contato.repository.ContatoRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ContatoService {

    private final ContatoRepository contatoRepository;

    private final ContatoMapper mapper;

    public ContatoService(ContatoRepository contatoRepository, ContatoMapper mapper){
        this.contatoRepository=contatoRepository;
        this.mapper=mapper;
    }

    public ContatoResponseDto criarContato(ContatoRequestDto contatoRequest) throws SQLException {
        Contato contato = mapper.toEntity(contatoRequest);
        Contato contatoSalvo = contatoRepository.save(contato);
        return mapper.toResponse(contato); // forma do lucas, melhor

        /*ContatoResponseDto responseDto = mapper.toResponse(contatoSalvo);
        return responseDto;*/
    }

    public List<Contato> listarContatos() throws SQLException {
        return contatoRepository.listarContatos();
    }

    public Contato buscarContato(int id) throws SQLException {
        return contatoRepository.buscarContato(id);
    }

    public Contato atualizarContato(Contato contato, int id) throws SQLException {
        contato.setId(id);
        contatoRepository.atualizarContato(contato);
        return contato;
    }

    public void deletarContato(int id) throws SQLException {
        contatoRepository.deletarContato(id);
    }
}