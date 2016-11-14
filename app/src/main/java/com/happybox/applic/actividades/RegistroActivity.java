package com.happybox.applic.actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import com.happybox.applic.R;
import com.happybox.applic.actividades.LoginActivity;

public class RegistroActivity extends AppCompatActivity {

    Spinner cboTipoTarjeta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        cboTipoTarjeta=(Spinner)findViewById(R.id.cboTipoTarjeta);
        //cboTipoTarjeta.set

    }

    public void registrarCliente(View view) {

        Intent objIntent = new Intent(this, LoginActivity.class);
        startActivity(objIntent);

    }
}
