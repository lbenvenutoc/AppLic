package com.happybox.applic.actividad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.happybox.applic.R;

public class BienvenidaActivity extends AppCompatActivity {

    //Prueba vigo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);
    }

    public void mostrarRegistro(View view) {
        Intent objIntent = new Intent(this, LoginActivity.class);
        startActivity(objIntent);
    }
}
