package com.example.rosalino.git.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import com.example.rosalino.git.R;
import com.example.rosalino.git.modelos.Usuario;

public class AdaptadorSeguidores extends BaseAdapter {

    private Context context;
    private List<Usuario> usuarios;

    public AdaptadorSeguidores(Context context, List<Usuario> usuarios) {
        this.context = context;
        this.usuarios = usuarios;
    }

    @Override
    public int getCount() {
        return usuarios.size();
    }

    @Override
    public Object getItem(int position) {
        return usuarios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return usuarios.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_followrs,null);
        }
        Usuario u = usuarios.get(position);
        TextView loginTextView = (TextView)v.findViewById(R.id.textViewLogin);
        ImageView avatarImageView = (ImageView)v.findViewById(R.id.imgAvatar);
        loginTextView.setText(u.getLogin());
        //Transformacion
        Picasso.with(context).load(u.getAvatar_url()).into(avatarImageView);
        return v;
    }
}
