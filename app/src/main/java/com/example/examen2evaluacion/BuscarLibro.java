package com.example.examen2evaluacion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class BuscarLibro extends AppCompatActivity {

    private SQLiteDatabase db;
    private LibrosSQlite lb;
    private Button btnBuscarL;
    private EditText editTextBuscar;
    private TextView BuscarL,texto,textView2;
    private RadioButton radioButtonTitulo,radioButtonAutor,radioButtonEditorial;
    private ArrayList<Libro> arrLibros;
    private ListView listViewLibros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_libro);

        lb = new LibrosSQlite(this, "DBLibros", null, 1);

        db = lb.getWritableDatabase();

        btnBuscarL = findViewById(R.id.btnBuscarL);
        BuscarL = findViewById(R.id.BuscarL);
        editTextBuscar = findViewById(R.id.editTextBuscar);
        texto = findViewById(R.id.texto);
        textView2 = findViewById(R.id.textView2);
        radioButtonTitulo = findViewById(R.id.radioButtonTitulo);
        radioButtonAutor = findViewById(R.id.radioButtonAutor);
        radioButtonEditorial = findViewById(R.id.radioButtonEditorial);
        listViewLibros = findViewById(R.id.listViewLibros);




        radioButtonTitulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                textView2.setText("TITULO");
                editTextBuscar.setVisibility(View.VISIBLE);


            }

        });
        radioButtonAutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                textView2.setText("AUTOR");
                editTextBuscar.setVisibility(View.VISIBLE);

            }

        });
        radioButtonEditorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                textView2.setText("EDITORIAL");
                editTextBuscar.setVisibility(View.VISIBLE);


            }

        });
        btnBuscarL.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){


                if(radioButtonTitulo.isChecked())
                {
                    String Titulo="";
                    String Autor = "";
                    Cursor c =db.rawQuery("SELECT Titulo, Autor FROM Libros WHERE Titulo=?", new String[]{editTextBuscar.getText().toString()});
                    if (c.moveToFirst()){
                        //Recorremos el cursor hasta que no haya más registros.
                        do {
                            Titulo =c.getString(0);
                            Autor = c.getString(1);


                        }
                        while (c.moveToNext());

                    }

                    if((Autor.equals(""))&&(Titulo.equals("")))
                    {
                        noExiste();

                    }
                    else
                    {

                        //FALTA LA PARTE DE MOSTRAR INFORMACION DETALLADA

                    }

                }
                else
                {

                    if(radioButtonAutor.isChecked())
                    {
                        String Titulo="";
                        String Autor = "";
                        arrLibros=new ArrayList<Libro>();
                        Cursor c =db.rawQuery("SELECT Titulo, Autor FROM Libros WHERE Autor=?", new String[]{editTextBuscar.getText().toString()});
                        if (c.moveToFirst()){
                            //Recorremos el cursor hasta que no haya más registros.
                            do {
                                Titulo =c.getString(0);
                                Autor = c.getString(1);

                                Libro l1 =new Libro(Titulo,Autor);
                                arrLibros.add(l1);

                            }
                            while (c.moveToNext());

                        }


                        if((Autor.equals(""))&&(Titulo.equals("")))
                        {
                            noExiste();

                        }
                        else
                        {

                            listado();

                        }

                    }
                    else
                    {

                        if(radioButtonEditorial.isChecked())
                        {
                            String Titulo="";
                            String Autor = "";
                            arrLibros=new ArrayList<Libro>();

                            Cursor c =db.rawQuery("SELECT Titulo, Autor FROM Libros WHERE Editorial=?", new String[]{editTextBuscar.getText().toString()});
                            if (c.moveToFirst()){
                                //Recorremos el cursor hasta que no haya más registros.
                                do {
                                    Titulo =c.getString(0);
                                    Autor = c.getString(1);

                                    Libro l1 =new Libro(Titulo,Autor);
                                    arrLibros.add(l1);

                                }
                                while (c.moveToNext());

                            }


                            if((Autor.equals(""))&&(Titulo.equals("")))
                            {
                                noExiste();

                            }
                            else
                            {

                                listado();

                            }
                        }


                    }

                }

            }
        });



    }


    //Metodo que saca un alert dialog cuando no exite el libro buscado
    public void noExiste()
    {

        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(BuscarLibro.this);
        dialogo1.setTitle("Aviso");
        dialogo1.setMessage("No existe ningun libro con esos datos");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        dialogo1.show();

    }

    //Metodo que saca el listado de libros
    public void listado()
    {
        BuscarL.setText("Listado Libros");
        listViewLibros.setVisibility(View.VISIBLE);
        editTextBuscar.setVisibility(View.INVISIBLE);
        btnBuscarL.setVisibility(View.INVISIBLE);
        textView2.setVisibility(View.INVISIBLE);
        texto.setVisibility(View.INVISIBLE);
        radioButtonTitulo.setVisibility(View.INVISIBLE);
        radioButtonAutor.setVisibility(View.INVISIBLE);
        radioButtonEditorial.setVisibility(View.INVISIBLE);





        Libro[] arr=new Libro[arrLibros.size()];
        for(int i=0;i<arrLibros.size();i++)
        {
            arr[i]=arrLibros.get(i);
        }
        AdaptadorLibros adaptador = new AdaptadorLibros(BuscarLibro.this, arr);
        listViewLibros.setAdapter(adaptador);



    }
}
