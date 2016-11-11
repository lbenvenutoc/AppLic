package com.happybox.applic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

public class RegistroActivity extends AppCompatActivity {

    Spinner cboTipoTarjeta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        cboTipoTarjeta=(Spinner)findViewById(R.id.cboTipoTarjeta);
        //cboTipoTarjeta.set

    }
}
