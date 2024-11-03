package com.biblioteca.controller;

import java.util.List;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.ViewScoped;

import com.biblioteca.DAO.EmprestimoDAO;
import com.biblioteca.model.Emprestimo;

@ManagedBean
@ViewScoped
public class EmprestimoController {
    private Emprestimo emprestimo = new Emprestimo();
    private EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
    private List<Emprestimo> emprestimos;

    public void cadastrar() {
        emprestimoDAO.cadastrar(emprestimo);
        emprestimo = new Emprestimo();
        listar(); 
    }

    public void atualizar() {
        emprestimoDAO.atualizar(emprestimo);
        emprestimo = new Emprestimo();
        listar(); 
    }

    public void excluir(int id) {
        emprestimoDAO.excluir(id);
        listar(); 
    }

    public Emprestimo consultar(int id) {
        return emprestimoDAO.consultar(id);
    }

    public List<Emprestimo> listar() {
        emprestimos = emprestimoDAO.listar();
        return emprestimos;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }
}
