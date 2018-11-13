package com.example.rosalino.git.vistas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import com.example.rosalino.git.adaptadores.AdaptadorSeguidores;
import com.example.rosalino.git.R;
import com.example.rosalino.git.interfaces.VistaUsuario;
import com.example.rosalino.git.modelos.Usuario;
import com.example.rosalino.git.presentadores.PresentadorUsuario;

public class MainActivity extends AppCompatActivity implements VistaUsuario{

    EditText txtLogin;
    TextView txtNombre;
    TextView txtMensaje;
    TextView txtCorreo;
    RelativeLayout layoutSeguidores;
    ImageView avatarImageView;
    ProgressBar progressBar;
    ListView lvSeguidores;
    PresentadorUsuario presentadorUsuario;
    AdaptadorSeguidores adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtLogin = (EditText)findViewById(R.id.txtLogin);
        txtMensaje = (TextView) findViewById(R.id.txtMensaje);
        txtNombre = (TextView)findViewById(R.id.txtNombre);
        txtCorreo = (TextView)findViewById(R.id.txtCorreo);
        layoutSeguidores = (RelativeLayout)findViewById(R.id.layoutSeguidores);
        avatarImageView = (ImageView)findViewById(R.id.imgAvatar);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        lvSeguidores = (ListView)findViewById(R.id.lvSeguidores);
        presentadorUsuario = new PresentadorUsuario(this);
    }

    public void buscar(View view){
        presentadorUsuario.obtenerLogin(txtLogin.getText().toString());
    }

    @Override
    public void showLogin(Usuario usuario) {
        Log.i("USUARIO", usuario.toString());
        txtNombre.setText(usuario.getName());
        txtCorreo.setText(usuario.getEmail());
        Transformation transformation = new RoundedTransformationBuilder().oval(true).build();

        Picasso.with(getApplicationContext())
                .load(usuario.getAvatar_url())
                .transform(transformation)
                .resize(180,180)
                .into(avatarImageView);
        layoutSeguidores.setVisibility(View.VISIBLE);
    }

    @Override
    public void showFollowers(List<Usuario> usuarios) {
        Log.i("Followers", usuarios.toString());
        adapter = new AdaptadorSeguidores(MainActivity.this, usuarios);
        lvSeguidores.setAdapter(adapter);
    }

    @Override
    public void showError(String mensaje) {
        layoutSeguidores.setVisibility(View.GONE);
        txtMensaje.setVisibility(View.VISIBLE);
        txtMensaje.setText(mensaje);
    }

    @Override
    public void showProgress() {
        txtMensaje.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void goneProgress() {
        txtMensaje.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyState(String mensaje) {
        lvSeguidores.setAdapter(null);
        txtMensaje.setVisibility(View.VISIBLE);
        txtMensaje.setText(mensaje);
    }

    @Override
    public void showSusses() {
        //no se implementa
    }
}
