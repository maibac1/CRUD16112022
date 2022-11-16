package com.example.sql_lite_test.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;

import com.example.sql_lite_test.modelos.Usuario;

import java.util.ArrayList;
import java.util.List;

public class DataBaseQueryUsuario {

    private Context context;

    public DataBaseQueryUsuario(Context context) {
        this.context = context;
    }

    public String insertarUsuario(Usuario u){
        String nombreUsuario=u.getNombre();
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contenido= new ContentValues();
        contenido.put(Config.COLUMNA_NOMBRE_USUARIO,u.getNombre());
        contenido.put(Config.COLUMNA_PASSWORD,u.getPassword());
        contenido.put(Config.COLUMNA_EDAD,u.getEdad());
        contenido.put(Config.COLUMNA_ACTIVO,u.getActivo());
        try{
            sqLiteDatabase.insertOrThrow(Config.TABLA_USUARIO,null,contenido);
        }
        catch(SQLiteException e){
            Toast.makeText(context, "Falló la inserción: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }finally {
            sqLiteDatabase.close();
        }
        return nombreUsuario;
    }

    public List<Usuario> obtenerUsuarios(){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor=null;
        try{
            cursor=  sqLiteDatabase.query(Config.TABLA_USUARIO,null,null,null,null,null,null,null);
        if(cursor!=null)
            if(cursor.moveToFirst()){
                List<Usuario> listaObtenida= new ArrayList<>();
                do{
                    Usuario usuarioObtenido=new Usuario();
                    //obtengo valores de la BD
                    String nombre = cursor.getString (cursor.getColumnIndex(Config.COLUMNA_NOMBRE_USUARIO));
                    String password = cursor.getString(cursor.getColumnIndex(Config.COLUMNA_PASSWORD));
                    int edad = cursor.getInt(cursor.getColumnIndex(Config.COLUMNA_EDAD));
                    int activo = cursor.getInt(cursor.getColumnIndex(Config.COLUMNA_ACTIVO));
                    //se los asigno al usuario
                    usuarioObtenido.setNombre(nombre);
                    usuarioObtenido.setPassword(password);
                    usuarioObtenido.setEdad(edad);
                    usuarioObtenido.setActivo(activo);
                    //agrego el usuario a la lista
                    listaObtenida.add(usuarioObtenido);

                }while(cursor.moveToNext());
                return listaObtenida;
            }
        }
        catch(SQLiteException e){
            Toast.makeText(context, "Error al listar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }finally {
            sqLiteDatabase.close();
        }
        return new ArrayList<Usuario>();
    }

    public Usuario obtenerUsuario(String nombre){
        Usuario usuarioObtenido=new Usuario();
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        int id=25;
        Cursor cursor=null;
        try{
            cursor=  sqLiteDatabase.query(Config.TABLA_USUARIO,
                    null,Config.COLUMNA_NOMBRE_USUARIO + " = ? ",
                    new String[]{nombre},null,null,null,null);

                if(cursor.moveToFirst()){


                        String nombreU = cursor.getString (cursor.getColumnIndex(Config.COLUMNA_NOMBRE_USUARIO));
                        String password = cursor.getString(cursor.getColumnIndex(Config.COLUMNA_PASSWORD));
                        usuarioObtenido.setNombre(nombre);
                        usuarioObtenido.setPassword(password);
                }
                return usuarioObtenido;

        }
        catch(SQLiteException e){
            Toast.makeText(context, "Error al listar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }finally {
            sqLiteDatabase.close();
        }
        return usuarioObtenido;

    }
}
