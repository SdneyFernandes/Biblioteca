package com.biblioteca.controller;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.ViewScoped;

import com.biblioteca.DAO.UsuarioDAO;
import com.biblioteca.model.Usuario;

import java.util.List;

@ManagedBean
@ViewScoped
public class UsuarioController {
    private Usuario usuario = new Usuario();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private List<Usuario> usuarios;

    public void cadastrar() {
        usuarioDAO.cadastrar(usuario);
        usuario = new Usuario();
        listar(); 
    }

    public void atualizar() {
        usuarioDAO.atualizar(usuario);
        usuario = new Usuario();
        listar(); 
    }

    public void excluir(int id) {
        usuarioDAO.excluir(id);
        listar(); 
    }

    public Usuario consultar(int id) {
        return usuarioDAO.consultar(id);
    }

    public List<Usuario> listar() {
        usuarios = usuarioDAO.listar();
        return usuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}
