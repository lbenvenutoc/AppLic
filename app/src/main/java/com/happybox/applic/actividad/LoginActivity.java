package com.happybox.applic.actividad;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.happybox.applic.R;
import com.happybox.applic.endpoint.ClienteEndPoint;
import com.happybox.applic.modelo.Cliente;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.url_base))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ClienteEndPoint restCliente=retrofit.create(ClienteEndPoint.class);

        Call<Cliente> call = restCliente.getCliente(ruc,claUsu);

        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                Log.i("response status", ""+response.code());
                switch (response.code()) {
                    case 200:
                        Cliente cliente = response.body();
                        if(cliente.getCodCli()==-1){
                            Toast.makeText(getBaseContext(), "Usuario y/o clave incorrectos",
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            Intent objIntent = new Intent(getBaseContext(),ListaLicitacionActivity.class);
                            objIntent.putExtra("cliente", cliente);
                            startActivity(objIntent);
                        }


                        break;
                    case 401:
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
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

