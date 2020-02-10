package com.example.examen2evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button Sqlite,Eltiempo,bSalir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Sqlite = findViewById(R.id.bEjer1);
        Eltiempo = findViewById(R.id.bEjer2);
        bSalir = findViewById(R.id.bSalir);

        Sqlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this,SQLite.class);

                startActivity(intent);
            }

        });

        Eltiempo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this,ElTiempo.class);

                startActivity(intent);

            }


        });


        bSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();
            }

        });

    }
}
