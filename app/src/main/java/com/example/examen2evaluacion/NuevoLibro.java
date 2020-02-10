package com.example.examen2evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class NuevoLibro extends AppCompatActivity {

    private SQLiteDatabase db;
    private LibrosSQlite lb;
    private Button btnInsertar,btnCancelar,btnVolver;
    private EditText editTextTitulo,editTextAutor,editTextISBN,editTextEditorial,editTextNum_pag;
    private CheckBox checkBoxLeido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_libro);


        lb = new LibrosSQlite(this, "DBLibros", null, 1);

        db = lb.getWritableDatabase();


        btnInsertar = findViewById(R.id.btnInsertar);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnVolver = findViewById(R.id.btnVolver);


        editTextTitulo = findViewById(R.id.editTextTitulo);
        editTextAutor = findViewById(R.id.editTextAutor);
        editTextISBN = findViewById(R.id.editTextISBN);
        editTextEditorial = findViewById(R.id.editTextEditorial);
        editTextNum_pag = findViewById(R.id.editTextNum_pag);
        checkBoxLeido = findViewById(R.id.checkBoxLeido);



        //INSERTAR
        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (db != null) {

                    ContentValues nuevoLibro = new ContentValues();
                    nuevoLibro.put("Titulo", editTextTitulo.getText().toString());
                    nuevoLibro.put("Autor", editTextAutor.getText().toString());
                    nuevoLibro.put("ISBN", Integer.parseInt(editTextISBN.getText().toString()));
                    nuevoLibro.put("Editorial", editTextEditorial.getText().toString());
                    nuevoLibro.put("Num_pag", Integer.parseInt(editTextNum_pag.getText().toString()));

                    if(checkBoxLeido.isChecked())
                    {
                        nuevoLibro.put("Leido", Boolean.TRUE);
                    }
                    else
                    {
                        nuevoLibro.put("Leido", Boolean.FALSE);
                    }
                    

                    db.insert("Libros", null, nuevoLibro);

                }



                editTextTitulo.setText("");
                editTextAutor.setText("");
                editTextISBN.setText("");
                editTextEditorial.setText("");
                editTextNum_pag.setText("");

                checkBoxLeido.setChecked(false);

            }

        });


        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editTextTitulo.setText("");
                editTextAutor.setText("");
                editTextISBN.setText("");
                editTextEditorial.setText("");
                editTextNum_pag.setText("");

                checkBoxLeido.setChecked(false);

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
