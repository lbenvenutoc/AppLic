package com.happybox.applic.actividad;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.gson.reflect.TypeToken;
import com.happybox.applic.LicApp;
import com.happybox.applic.R;
import com.happybox.applic.modelo.Cliente;


public class LoginActivity extends AppCompatActivity {

    private AutoCompleteTextView emailAutoCompleteTextView;
    private EditText claveEditText;
    private View loginScrollView;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.emailAutoCompleteTextView);
        claveEditText = (EditText) findViewById(R.id.claveEditText);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        loginScrollView = findViewById(R.id.loginScrollView);

        //if(sharedPreferences==null){
            sharedPreferences = getBaseContext().getSharedPreferences(
                    LicApp.obtenerInstancia().obtenerServicio().PREFS, Context.MODE_PRIVATE);
            String usuario=sharedPreferences.getString(LicApp.obtenerInstancia().obtenerServicio().PREF_USUARIO, "");
            String clave=sharedPreferences.getString(LicApp.obtenerInstancia().obtenerServicio().PREF_CLAVE, "");
            emailAutoCompleteTextView.setText(usuario);
            claveEditText.setText(clave);
        //}

    }

    private void attemptLogin() {


        boolean cancel = false;
        View focusView = null;
        String ruc = emailAutoCompleteTextView.getText().toString();
        String claUsu = claveEditText.getText().toString();

        emailAutoCompleteTextView.setError(null);
        claveEditText.setError(null);

        if(TextUtils.isEmpty(ruc)){
            emailAutoCompleteTextView.setError("Ruc es requerido");
            focusView = emailAutoCompleteTextView;
            cancel = true;
        }else if(TextUtils.isEmpty(claUsu)){
            claveEditText.setError("Clave es requerida");
            focusView = emailAutoCompleteTextView;
            cancel = true;
        }else if(ruc.length()<11){
            emailAutoCompleteTextView.setError("Ruc debe tener 11 dígitos");
            focusView = emailAutoCompleteTextView;
            cancel = true;
        }

       if(cancel){
           focusView.requestFocus();
       }else{
            procesarAcceso(ruc,claUsu);
        }

    }


    public void procesarAcceso(final String ruc, final String claUsu){

        AndroidNetworking.get(getResources().getString(R.string.url_base)+getResources().getString(R.string.url_obtiene_cliente))
                .addPathParameter("ruc", ruc)
                .addPathParameter("claUsu", claUsu)
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsParsed(new TypeToken<Cliente>(){}, new ParsedRequestListener<Cliente>() {
                    @Override
                    public void onResponse(Cliente cliente) {
                        if(cliente.getCodCli()==-1){
                            Toast.makeText(getBaseContext(), "Usuario y/o clave incorrectos",
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            LicApp.obtenerInstancia().obtenerServicio().setCliente(cliente);
                            sharedPreferences =  getSharedPreferences(LicApp.obtenerInstancia().obtenerServicio().PREFS, MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(LicApp.obtenerInstancia().obtenerServicio().PREF_USUARIO, ruc);
                            editor.putString(LicApp.obtenerInstancia().obtenerServicio().PREF_CLAVE, claUsu);
                            editor.commit();
                            Intent objIntent = new Intent(getBaseContext(),ListaLicitacionActivity.class);
                            startActivity(objIntent);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getBaseContext(), "Error al conectarse al API REST",
                                Toast.LENGTH_SHORT).show();
                    }

                });

    }


    public void mostrarRegistro(View view) {

        Intent objIntent = new Intent(getBaseContext(), RegistroActivity.class);
        startActivity(objIntent);

    }
}

