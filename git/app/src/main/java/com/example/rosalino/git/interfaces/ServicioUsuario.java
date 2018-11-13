package com.example.rosalino.git.interfaces;

import java.util.List;

import com.example.rosalino.git.modelos.Usuario;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface ServicioUsuario {

    @GET("/users/{login}")
    void getUsuario(@Path("login") String login, Callback<Usuario> callback);

    @GET("/users/{login}/followers")
    void getFollowers(@Path("login") String login, Callback<List<Usuario>> callback);

}
