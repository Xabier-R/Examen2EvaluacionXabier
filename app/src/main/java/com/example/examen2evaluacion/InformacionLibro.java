package com.example.examen2evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class InformacionLibro extends AppCompatActivity {


    private TextView titulo,Autor,ISBN,Editorial,paginas,leido;
    private Button buttonBorrar,buttonCancelar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_libro);



        titulo = findViewById(R.id.titulo);
        Autor = findViewById(R.id.Autor);
        ISBN = findViewById(R.id.ISBN);
        Editorial = findViewById(R.id.Editorial);
        paginas = findViewById(R.id.paginas);
        leido = findViewById(R.id.leido);
        buttonBorrar = findViewById(R.id.buttonBorrar);
        buttonCancelar = findViewById(R.id.buttonCancelar);








    }
}
