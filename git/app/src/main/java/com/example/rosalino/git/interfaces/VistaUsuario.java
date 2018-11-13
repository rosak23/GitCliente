package com.example.rosalino.git.interfaces;

import java.util.List;

import com.example.rosalino.git.modelos.Usuario;

public interface VistaUsuario extends VistaGenerica{
    void showLogin(Usuario usuario);
    void showFollowers(List<Usuario> usuarios);
}
