package com.example.examen2evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SQLite extends AppCompatActivity {

    private Button NuevoLibro,ListadoLibros,BuscarLibro,btnVolver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);



        NuevoLibro = findViewById(R.id.NuevoL);
        ListadoLibros = findViewById(R.id.ListadoL);
        BuscarLibro = findViewById(R.id.BuscarL);
        btnVolver = findViewById(R.id.bSalir);

        NuevoLibro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(SQLite.this,NuevoLibro.class);

                startActivity(intent);
            }

        });

        ListadoLibros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(SQLite.this,ListadoLibros.class);

                startActivity(intent);
            }

        });


        BuscarLibro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(SQLite.this,BuscarLibro.class);

                startActivity(intent);
            }

        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();
            }

        });



    }
}
