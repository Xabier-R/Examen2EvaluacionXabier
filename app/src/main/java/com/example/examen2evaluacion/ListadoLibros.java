package com.example.examen2evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;

import java.util.ArrayList;

public class ListadoLibros extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private RadioButton radioButtonLeido,radioButtonNoLeido,radioButtonTodo;
    private ListView listViewLibros;
    private Button btnVolver;
    private SQLiteDatabase db;
    private LibrosSQlite lb;
    private ArrayList<Libro> arrLibros;
    private Libro[] arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_libros);


        lb = new LibrosSQlite(this, "DBLibros", null, 1);

        db = lb.getWritableDatabase();

        radioButtonLeido = findViewById(R.id.radioButtonLeido);
        radioButtonNoLeido = findViewById(R.id.radioButtonNoLeido);
        radioButtonTodo = findViewById(R.id.radioButtonTodo);
        listViewLibros = findViewById(R.id.listViewLibros);
        btnVolver = findViewById(R.id.btnVolver);



        radioButtonTodo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){

                arrLibros=new ArrayList<Libro>();
                Cursor c =db.rawQuery("SELECT Titulo, Autor FROM Libros", null);
                if (c.moveToFirst()){
                    //Recorremos el cursor hasta que no haya más registros.
                    do {
                        String Titulo =c.getString(0);
                        String Autor = c.getString(1);

                        Libro l1 =new Libro(Titulo,Autor);

                        arrLibros.add(l1);


                    }
                    while (c.moveToNext());

                }

                arr=new Libro[arrLibros.size()];

                for(int i=0;i<arrLibros.size();i++)
                {

                    arr[i]=arrLibros.get(i);


                }
                AdaptadorLibros adaptador = new AdaptadorLibros(ListadoLibros.this, arr);
                listViewLibros.setAdapter(adaptador);
                listViewLibros.setOnItemClickListener(ListadoLibros.this);

            }
        });

        radioButtonLeido.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){

                arrLibros=new ArrayList<Libro>();
                Cursor c =db.rawQuery("SELECT Titulo, Autor FROM Libros WHERE Leido=1", null);
                if (c.moveToFirst()){
                    //Recorremos el cursor hasta que no haya más registros.
                    do {
                        String Titulo =c.getString(0);
                        String Autor = c.getString(1);

                        Libro l1 =new Libro(Titulo,Autor);

                        arrLibros.add(l1);


                    }
                    while (c.moveToNext());

                }


                arr=new Libro[arrLibros.size()];

                for(int i=0;i<arrLibros.size();i++)
                {

                    arr[i]=arrLibros.get(i);


                }
                AdaptadorLibros adaptador = new AdaptadorLibros(ListadoLibros.this, arr);
                listViewLibros.setAdapter(adaptador);
                listViewLibros.setOnItemClickListener(ListadoLibros.this);
            }
        });

        radioButtonNoLeido.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){

                arrLibros=new ArrayList<Libro>();
                Cursor c =db.rawQuery("SELECT Titulo, Autor FROM Libros WHERE Leido=0", null);
                if (c.moveToFirst()){
                    //Recorremos el cursor hasta que no haya más registros.
                    do {
                        String Titulo =c.getString(0);
                        String Autor = c.getString(1);

                        Libro l1 =new Libro(Titulo,Autor);

                        arrLibros.add(l1);


                    }
                    while (c.moveToNext());

                }


                arr=new Libro[arrLibros.size()];

                for(int i=0;i<arrLibros.size();i++)
                {

                    arr[i]=arrLibros.get(i);


                }
                AdaptadorLibros adaptador = new AdaptadorLibros(ListadoLibros.this, arr);
                listViewLibros.setAdapter(adaptador);
                listViewLibros.setOnItemClickListener(ListadoLibros.this);
            }
        });



        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();

            }

        });

    }

    public void onItemClick(AdapterView<?> parent, View view, int index, long arg3) {

        //FALTA LA PARTE DE MOSTRAR INFORMACION DETALLADA

       /* AdaptadorLibros adaptador = new AdaptadorLibros(ListadoLibros.this, arr);
        Libro libro = adaptador.getItem(index);
        Intent intent = new Intent(this, InformacionLibro.class);
        startActivity(intent);*/


    }
}
