package com.example.rosalino.git.presentadores;

import android.util.Log;

import java.util.List;

import com.example.rosalino.git.interfaces.ServicioUsuario;
import com.example.rosalino.git.interfaces.VistaUsuario;
import com.example.rosalino.git.modelos.Usuario;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PresentadorUsuario {
    VistaUsuario vistaUsuario;
    ServicioUsuario servicioUsuario;

    public PresentadorUsuario(VistaUsuario vistaUsuario) {
        this.vistaUsuario = vistaUsuario;
    }

    public void obtenerLogin(final String login){
        vistaUsuario.showProgress();
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("https://api.github.com").build();
        servicioUsuario = restAdapter.create(ServicioUsuario.class);
        servicioUsuario.getUsuario(login, new Callback<Usuario>() {
            @Override
            public void success(final Usuario usuario, Response response) {
                vistaUsuario.goneProgress();
                vistaUsuario.showLogin(usuario);
                servicioUsuario.getFollowers(login, new Callback<List<Usuario>>() {
                     @Override
                     public void success(List<Usuario> usuarios, Response response) {
                         if (usuarios.isEmpty()) {
                             vistaUsuario.showEmptyState("No cuenta con seguidores");
                         } else {
                             vistaUsuario.showFollowers(usuarios);
                         }
                     }
                     @Override
                     public void failure(RetrofitError error) {
                         Log.e("Error followers", error.getLocalizedMessage());
                     }
                });
            }
            @Override
            public void failure(RetrofitError error) {
                Log.e("ERROR DE PETICION", error.getLocalizedMessage());
                vistaUsuario.goneProgress();
            }
        });
    }

}
