package com.happybox.applic.actividad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.happybox.applic.LicApp;
import com.happybox.applic.R;
import com.happybox.applic.modelo.Licitacion;

public class LicitacionActivity extends AppCompatActivity {

    TextView identificadorTextView;
    TextView nomenclaturaTextView;
    TextView normativaTextView;
    TextView seaceTextView;
    TextView entidadConvocanteTextView;
    TextView direccionTextView;
    TextView categoriaTextView;
    TextView resumenTextView;
    TextView montoTextView;
    TextView fechaPublicacionTextView;
    TextView fechaCierreTextView;
    Licitacion licitacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licitacion);
        identificadorTextView=(TextView) findViewById(R.id.identificadorTextView);
        nomenclaturaTextView=(TextView) findViewById(R.id.nomenclaturaTextView);
        normativaTextView=(TextView) findViewById(R.id.normativaTextView);
        seaceTextView=(TextView) findViewById(R.id.seaceTextView);
        entidadConvocanteTextView=(TextView) findViewById(R.id.entidadConvocanteTextView);
        direccionTextView=(TextView) findViewById(R.id.direccionTextView);
        categoriaTextView=(TextView) findViewById(R.id.categoriaTextView);
        resumenTextView=(TextView) findViewById(R.id.resumenTextView);
        montoTextView=(TextView) findViewById(R.id.montoTextView);
        fechaPublicacionTextView=(TextView) findViewById(R.id.fechaPublicacionTextView);
        fechaCierreTextView=(TextView) findViewById(R.id.fechaCierreTextView);

        licitacion= LicApp.obtenerInstancia().obtenerServicio().getLicitacion();

        /*
        Intent intent = getIntent();
        Licitacion licitacion = (Licitacion) intent.getExtras().getSerializable("licitacion");
        */

        identificadorTextView.setText("Identificador de Convocatoria: "+licitacion.getCodLic());
        nomenclaturaTextView.setText("Nomenclatura: "+licitacion.getNomLic());
        normativaTextView.setText("Normativa Aplicable: "+licitacion.getNorAplLic());
        seaceTextView.setText("Version SEACE: "+licitacion.getVerSeaLic());
        entidadConvocanteTextView.setText("Entidad convocante: "+licitacion.getDesEnt());
        direccionTextView.setText("Direccion legal: "+licitacion.getDirEnt());
        categoriaTextView.setText("Categoría: "+licitacion.getDesCat());
        resumenTextView.setText("Resumen: "+licitacion.getDesLic());
        montoTextView.setText("Monto referencial: "+licitacion.getValRef()+ " "+licitacion.getMonLic());
        fechaPublicacionTextView.setText("Fecha y hora de publicación: "+licitacion.getFecPubLic());
        fechaCierreTextView.setText("Fecha de cierre: "+licitacion.getFecTerLic());



    }


}
