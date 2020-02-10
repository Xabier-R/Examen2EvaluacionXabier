package com.example.examen2evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ElTiempo extends AppCompatActivity {


    public String url1 = "http://xml.tutiempo.net/xml/8043.xml";
    public String url2 = "https://api.tutiempo.net/xml/?lan=es&apid=qsTX4X4qq44as6Q&lid=8050";
    public String url3 = "https://api.tutiempo.net/xml/?lan=es&apid=qsTX4X4qq44as6Q&lid=4917";
    private Button bBilbao, bVitoria, bDonosti, bSalir;
    private TextView dia, TempMax, TempMin, Cielo,ciudad;
    private List<Tiempo> tiempo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_el_tiempo);

        bBilbao = findViewById(R.id.bBilbao);
        bVitoria = findViewById(R.id.bVitoria);
        bDonosti = findViewById(R.id.bDonosti);
        bSalir = findViewById(R.id.bSalir);
        dia = findViewById(R.id.dia);
        TempMax = findViewById(R.id.TempMax);
        TempMin = findViewById(R.id.TempMin);
        Cielo = findViewById(R.id.Cielo);
        ciudad = findViewById(R.id.ciudad);



        bVitoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ciudad.setText("Vitoria");
                ElTiempo.CargarXmlTask tarea = new ElTiempo.CargarXmlTask();
                tarea.execute(url1);


            }

        });

        bBilbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ciudad.setText("Bilbao");
                ElTiempo.CargarXmlTask2 tarea = new ElTiempo.CargarXmlTask2();
                tarea.execute(url2);

            }

        });

        bDonosti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ciudad.setText("Donosti");
                ElTiempo.CargarXmlTask2 tarea = new ElTiempo.CargarXmlTask2();
                tarea.execute(url3);


            }

        });

        bSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();
            }

        });

    }




    private class CargarXmlTask extends AsyncTask<String, Integer, Boolean> {

        protected Boolean doInBackground(String... params) {
            RssParserDOMtemperatura domParser = new RssParserDOMtemperatura(params[0]);
            tiempo = domParser.parse();
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            cargarDatos();
        }
    }

    private class CargarXmlTask2 extends AsyncTask<String, Integer, Boolean> {

        protected Boolean doInBackground(String... params) {
            RssParserDOMtemperatura2 domParser = new RssParserDOMtemperatura2(params[0]);
            tiempo = domParser.parse();
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            cargarDatos();
        }
    }

    public void cargarDatos() {


        Tiempo tiempo;

        tiempo = this.tiempo.get(0);

        dia.setText(getResources().getString(R.string.dia) + " " + tiempo.getFecha());
        Cielo.setText(getResources().getString(R.string.estado_del_cielo) + " " + tiempo.getEstadocielo());
        TempMin.setText(getResources().getString(R.string.temperatura_minima) + " " + tiempo.getTempmin());
        TempMax.setText(getResources().getString(R.string.temperatura_maxima) + " " + tiempo.getTempmax());

    }
}
