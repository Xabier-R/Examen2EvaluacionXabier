package com.example.examen2evaluacion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LibrosSQlite extends SQLiteOpenHelper {

    String sqlCreate1 ="CREATE TABLE Libros (Titulo TEXT PRIMARY KEY ," +" Autor TEXT," +" ISBN INTEGER ," +" Editorial TEXT, " +" Num_pag INTEGER ," +" Leido BOOLEAN)";


    public LibrosSQlite(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Libros");
        db.execSQL(sqlCreate1);
    }
}
