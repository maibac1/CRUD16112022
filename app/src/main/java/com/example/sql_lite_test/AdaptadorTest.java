package com.example.sql_lite_test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorTest extends RecyclerView.Adapter<AdaptadorTest.ViewHolderDatos> {

    ArrayList<String> listaDatos;

    public AdaptadorTest(ArrayList<String> listaDatos) {
        this.listaDatos = listaDatos;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //enlazar el adaptador con el XML de items.
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list2,null,false);
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

        TextView dato;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            dato=itemView.findViewById(R.id.textViewDatos);
        }

        public void asignarDatos(String s) {
            dato.setText(s);
        }
    }
}