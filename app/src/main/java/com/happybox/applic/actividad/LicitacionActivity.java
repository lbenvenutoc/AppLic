package com.happybox.applic.actividad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.happybox.applic.R;
import com.happybox.applic.modelo.Cliente;
import com.happybox.applic.modelo.Licitacion;

public class LicitacionActivity extends AppCompatActivity {

    TextView lblVistaRazSoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licitacion);
        lblVistaRazSoc=(TextView) findViewById(R.id.lblVistaRazSoc);

        Intent intent = getIntent();
        Licitacion licitacion = (Licitacion) intent.getExtras().getSerializable("licitacion");

        lblVistaRazSoc.setText(""+licitacion.getCodLic());



    }
}
