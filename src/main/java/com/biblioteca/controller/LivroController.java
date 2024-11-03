package com.biblioteca.controller;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.ViewScoped;

import com.biblioteca.DAO.LivroDAO;
import com.biblioteca.model.Livro;

import java.util.List;

@ManagedBean
@ViewScoped
public class LivroController {
    private Livro livro = new Livro();
    private LivroDAO livroDAO = new LivroDAO();
    private List<Livro> livros;

    public void cadastrar() {
        livroDAO.cadastrar(livro);
        livro = new Livro();
        listar(); 
    }

    public void atualizar() {
        livroDAO.atualizar(livro);
        livro = new Livro();
        listar(); 
    }

    public void excluir(int id) {
        livroDAO.excluir(id);
        listar(); 
    }

    public Livro consultar(int id) {
        return livroDAO.consultar(id);
    }

    public List<Livro> listar() {
        livros = livroDAO.listar();
        return livros;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public List<Livro> getLivros() {
        return livros;
    }
}
