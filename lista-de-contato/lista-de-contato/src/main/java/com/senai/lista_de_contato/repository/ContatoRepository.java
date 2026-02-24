package com.senai.lista_de_contato.repository;

import com.senai.lista_de_contato.model.Contato;
import com.senai.lista_de_contato.utils.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ContatoRepository {

    public Contato save(Contato contato) throws SQLException {
        String sql = "INSERT INTO contato(nome, numero) VALUES(?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getNumero());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                contato.setId(rs.getInt(1));
                return contato;
            }

        }
        throw new RuntimeException("Erro ao inserir o contato!");
    }

    public List<Contato> listarContatos() throws SQLException {
        String sql = "SELECT id, nome, numero FROM contato";
        List<Contato> lista_contatos = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista_contatos.add(buscaPorContato(rs));

            }
        }
        return lista_contatos;
    }

    private Contato buscaPorContato(ResultSet rs) throws SQLException {
        Contato contato = new Contato();
        contato.setId(rs.getInt("id"));
        contato.setNome(rs.getString("nome"));
        contato.setNumero(rs.getString("numero"));
        return contato;
    }

    public Contato buscarContato(int id) throws SQLException {
        String sql = "SELECT id, nome, numero FROM contato WHERE id = ?";
        Contato contato = new Contato();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                contato = buscaPorContato(rs);

            }
        }
        return contato;
    }

    public void atualizarContato(Contato contato) throws SQLException {
        String sql = "UPDATE contato SET nome = ?, numero = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getNumero());
            stmt.setInt(3, contato.getId());
            stmt.executeUpdate();
        }
    }

    public void deletarContato(int id) throws SQLException {
        String sql = "DELETE FROM contato WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}