package com.example.sql_lite_test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sql_lite_test.modelos.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolderDatos> {

    List<Usuario> listaDatos;

    public Adaptador(List<Usuario> listaDatos) {
        this.listaDatos = listaDatos;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //enlazar el adaptador con el XML de items.
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {

        holder.asignarDatos(listaDatos.get(position));
    }

    @Override
    public int getItemCount() {
        return listaDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView nombre;
        TextView password;
        CheckBox activo;
        TextView edad;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            nombre=itemView.findViewById(R.id.textViewNombre);
            password=itemView.findViewById(R.id.textViewPassword);
            activo=itemView.findViewById(R.id.checkBoxActivoL);
            edad=itemView.findViewById(R.id.editTextNumberEdad);
        }

        public void asignarDatos(Usuario u) {
            nombre.setText(u.getNombre());
            password.setText(u.getPassword());
            if(u.getActivo()==0)
                activo.setChecked(false);
            else
                activo.setChecked(true);
            //activo.setText();
            edad.setText(u.getEdad());
        }
    }
}