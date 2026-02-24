package com.senai.lista_de_contato.controller;

import com.senai.lista_de_contato.dto.contato.ContatoRequestDto;
import com.senai.lista_de_contato.dto.contato.ContatoResponseDto;
import com.senai.lista_de_contato.model.Contato;
import com.senai.lista_de_contato.service.ContatoService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/contato")
public class ContatoController {

    private ContatoService contatoService;

    public ContatoController(ContatoService contatoService){
        this.contatoService=contatoService;
    }

    @GetMapping
    public List<Contato> listarContatos(){
        try {
            return contatoService.listarContatos();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Contato buscarContato(@RequestBody Contato contato, @PathVariable int id){
        try {
            return contatoService.buscarContato(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping
    public ContatoResponseDto criarContato(@RequestBody ContatoRequestDto contatoRequest){
        try {
            return contatoService.criarContato(contatoRequest);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Contato atualizarContato(@RequestBody Contato contato, @PathVariable int id){
        try {
            return contatoService.atualizarContato(contato, id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deletarContato(@PathVariable int id){
        try {
            contatoService.deletarContato(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}