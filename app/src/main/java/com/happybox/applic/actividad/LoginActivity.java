package com.happybox.applic.actividad;


import android.content.Intent;
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
import com.happybox.applic.R;
import com.happybox.applic.modelo.Cliente;



public class LoginActivity extends AppCompatActivity {



    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }







    private void attemptLogin() {


        boolean cancel = false;
        View focusView = null;
        String ruc = mEmailView.getText().toString();
        String claUsu = mPasswordView.getText().toString();

        mEmailView.setError(null);
        mPasswordView.setError(null);

        if(TextUtils.isEmpty(ruc)){
            mEmailView.setError("Ruc es requerido");
            focusView = mEmailView;
            cancel = true;
        }else if(TextUtils.isEmpty(claUsu)){
            mPasswordView.setError("Clave es requerida");
            focusView = mEmailView;
            cancel = true;
        }else if(ruc.length()<11){
            mEmailView.setError("Ruc debe tener 11 dígitos");
            focusView = mEmailView;
            cancel = true;
        }

       if(cancel){
           focusView.requestFocus();
       }else{
            procesarAcceso(ruc,claUsu);
        }






    }

    public void procesarAcceso(String ruc, String claUsu){

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
                            Intent objIntent = new Intent(getBaseContext(),ListaLicitacionActivity.class);
                            objIntent.putExtra("cliente", cliente);
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

