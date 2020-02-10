package com.example.examen2evaluacion;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class RssParserDOMtemperatura2 {

    private URL rssURL;

    public RssParserDOMtemperatura2(String url){
        try{
            this.rssURL = new URL (url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Tiempo> parse() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        List<Tiempo> prediccion = new ArrayList<Tiempo>();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(this.getInputStream());
            Element root = dom.getDocumentElement();

            //Localizamos todos los elemntos <dia>
            NodeList items = root.getElementsByTagName("day1");

            for (int i=0; i<items.getLength(); i++) {
                Tiempo tiempo = new Tiempo();

                //ETIQUETA DIA
                Node dia = items.item(i);

                //OBTENEMOS LOS DATOS DE DIA
                NodeList datosTemporal = dia.getChildNodes();

                //RECORREMOS CADA ELEMENTO
                for (int j = 0; j < datosTemporal.getLength(); j++) {
                    //OBTENER NOMBRE ETIQUETA
                    Node dato = datosTemporal.item(j);
                    String etiqueta = dato.getNodeName();


                    if (etiqueta.equals("date")) {
                        String texto = obtenerTexto(dato);
                        if (texto.equals("")) tiempo.setFecha("");
                        else tiempo.setFecha(texto);
                    } else if (etiqueta.equals("temperature_max")) {
                        String texto = obtenerTexto(dato);
                        Log.v("TEMP_MAX", texto);
                        if (texto.equals("")) tiempo.setTempmax(0);
                        else tiempo.setTempmax(Integer.parseInt(texto));
                    } else if (etiqueta.equals("temperature_min")) {
                        String texto = obtenerTexto(dato);

                        if (texto.equals("")) tiempo.setTempmin(0);
                        else tiempo.setTempmin(Integer.parseInt(texto));

                    } else if (etiqueta.equals("text")) {
                        String texto = obtenerTexto(dato);
                        if (texto.equals("")) tiempo.setEstadocielo("");
                        else tiempo.setEstadocielo(texto);
                    }


                }prediccion.add(tiempo);
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }

        return prediccion;

    }

    public String obtenerTexto (Node dato) {
        StringBuilder texto = new StringBuilder();
        NodeList fragmentos = dato.getChildNodes();

        for (int k=0; k<fragmentos.getLength(); k++) {
            texto.append(fragmentos.item(k).getNodeValue());
        }
        return texto.toString();
    }

    private InputStream getInputStream() {
        try {
            return rssURL.openConnection().getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
