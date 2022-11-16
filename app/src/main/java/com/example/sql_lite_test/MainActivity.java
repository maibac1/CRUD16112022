package com.example.sql_lite_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.sql_lite_test.Database.DataBaseQueryUsuario;
import com.example.sql_lite_test.modelos.Usuario;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText usuario;
    private EditText password;
    private CheckBox activo;
    private EditText edad;
    private Button agregarBtn;
    List<Usuario> listaDatos;//datos con los que se construirá el reciclador
    RecyclerView reciclador;

    ArrayList<String> listaDatos2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario = this.findViewById(R.id.editTextTextEmailAddress);
        password= this.findViewById(R.id.editTextTextPassword);
        activo= this.findViewById(R.id.checkBoxActivoL);
        edad= this.findViewById(R.id.editTextNumberEdad);
        agregarBtn=this.findViewById(R.id.button);


        this.obtenerListaUsuarios();



        agregarBtn.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){

                Usuario usuarioNuevo= new Usuario();
                usuarioNuevo.setNombre(usuario.getText().toString());
                usuarioNuevo.setPassword(password.getText().toString());
                usuarioNuevo.setEdad(Integer.parseInt(edad.getText().toString()));
                if(activo.isChecked())
                    usuarioNuevo.setActivo(1);
                else
                    usuarioNuevo.setActivo(0);
                DataBaseQueryUsuario dbQeryUsuario = new DataBaseQueryUsuario(getBaseContext());

                dbQeryUsuario.insertarUsuario(usuarioNuevo);
               // this.obtenerListaUsuarios();


            }

        });

    }

    public void obtenerListaUsuarios(){
        DataBaseQueryUsuario dbQeryUsuario1 = new DataBaseQueryUsuario(getBaseContext());

        //listaDatos= new ArrayList<Usuario>();
        listaDatos=dbQeryUsuario1.obtenerUsuarios();
        Adaptador adapter = new Adaptador(listaDatos);


        //obtener el reciclador
        reciclador= findViewById(R.id.recicladorSimple);
        //reciclador.setLayoutManager(new GridLayoutManager(this,1));
        reciclador.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        reciclador.setAdapter(adapter);
    }
}