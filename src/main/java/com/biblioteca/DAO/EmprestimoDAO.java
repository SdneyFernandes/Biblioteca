package com.biblioteca.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biblioteca.model.Emprestimo;
import com.biblioteca.model.Livro;
import com.biblioteca.model.Usuario;
import com.biblioteca.util.Conexao;

public class EmprestimoDAO {

    public void cadastrar(Emprestimo emprestimo) {
        String sql = "INSERT INTO emprestimos (usuario_id, livro_id, data_emprestimo, data_devolucao) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, emprestimo.getUsuario().getId());
            stmt.setInt(2, emprestimo.getLivro().getId());
            stmt.setDate(3, new java.sql.Date(emprestimo.getDataEmprestimo().getTime()));
            stmt.setDate(4, new java.sql.Date(emprestimo.getDataDevolucao().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Emprestimo emprestimo) {
        String sql = "UPDATE emprestimos SET usuario_id = ?, livro_id = ?, data_emprestimo = ?, data_devolucao = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, emprestimo.getUsuario().getId());
            stmt.setInt(2, emprestimo.getLivro().getId());
            stmt.setDate(3, new java.sql.Date(emprestimo.getDataEmprestimo().getTime()));
            stmt.setDate(4, new java.sql.Date(emprestimo.getDataDevolucao().getTime()));
            stmt.setInt(5, emprestimo.getId()); // Corrigido
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM emprestimos WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Emprestimo consultar(int id) {
        Emprestimo emprestimo = null;
        String sql = "SELECT * FROM emprestimos WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Usuario usuario = new UsuarioDAO().consultar(rs.getInt("usuario_id"));
                Livro livro = new LivroDAO().consultar(rs.getInt("livro_id"));
                emprestimo = new Emprestimo(
                        rs.getInt("id"), // Corrigido
                        usuario,
                        livro,
                        rs.getDate("data_emprestimo"),
                        rs.getDate("data_devolucao")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emprestimo;
    }

    public List<Emprestimo> listar() {
        List<Emprestimo> emprestimos = new ArrayList<>();
        String sql = "SELECT * FROM emprestimos";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Usuario usuario = new UsuarioDAO().consultar(rs.getInt("usuario_id"));
                Livro livro = new LivroDAO().consultar(rs.getInt("livro_id"));
                Emprestimo emprestimo = new Emprestimo(
                        rs.getInt("id"), // Corrigido
                        usuario,
                        livro,
                        rs.getDate("data_emprestimo"),
                        rs.getDate("data_devolucao")
                );
                emprestimos.add(emprestimo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emprestimos;
    }
}
