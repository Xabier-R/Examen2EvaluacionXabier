package com.example.examen2evaluacion;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

class AdaptadorLibros extends ArrayAdapter<Libro> {

    private Libro[] datosLibro;

    public AdaptadorLibros(@NonNull Context context, Libro[] datos) {
        super(context, R.layout.activity_adaptador_libros, datos);
        this.datosLibro = datos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.activity_adaptador_libros, null);

        String titulo = datosLibro[position].getTitulo();
        String autor = datosLibro[position].getAutor();

        TextView tvTitulo = item.findViewById(R.id.titulo);
        tvTitulo.setText(titulo);

        TextView tvAutor = item.findViewById(R.id.autor);
        tvAutor.setText(autor);


        return (item);
    }
}