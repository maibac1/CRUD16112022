package com.example.sql_lite_test.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper databaseHelper;

    private static final int DATABASE_VERSION = 5;

    public static final String DATABASE_NAME= Config.DATABASE_NAME;


    private DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public static synchronized DatabaseHelper getInstance(Context context){
        if(databaseHelper==null){
            databaseHelper= new DatabaseHelper(context);
        }
        return databaseHelper;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREAR_TABLA_USUARIO = "CREATE TABLE " + Config.TABLA_USUARIO + "("
                + Config.COLUMNA_NOMBRE_USUARIO + " TEXT PRIMARY KEY,"
                + Config.COLUMNA_PASSWORD + " TEXT NOT NULL,"
                + Config.COLUMNA_GENERO + " TEXT ,"
                + Config.COLUMNA_EDAD + " INTEGER ,"
                + Config.COLUMNA_ACTIVO + " INTEGER ,"
                + Config.COLUMNA_FECHA_REGISTRO + " TEXT "
                +")";
        db.execSQL(CREAR_TABLA_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + Config.TABLA_USUARIO);
        onCreate(db);
    }
}
