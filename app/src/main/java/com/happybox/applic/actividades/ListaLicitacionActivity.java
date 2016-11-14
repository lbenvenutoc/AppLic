package com.happybox.applic.actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.happybox.applic.R;
import com.happybox.applic.endpoint.ClienteEndPoint;
import com.happybox.applic.modelo.Cliente;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ListaLicitacionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_licitacion);


       /* Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
                */

        Intent intent = getIntent();
        String ruc = intent.getStringExtra("ruc");
        String claUsu = intent.getStringExtra("claUsu");
        //String url = getResources().getString(R.string.url_acceso_usuario);
        //String urlParametros = url + ruc + "/" + claUsu;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8090/spring-rest-servidor/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ClienteEndPoint rest=retrofit.create(ClienteEndPoint.class);

        Call<Cliente> call = rest.getCliente(ruc,claUsu);

        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                Log.i("response status", ""+response.code());
                switch (response.code()) {
                    case 200:

                        Cliente data = response.body();
                        //Log.i("Informacion", data.getRucCli());
                        Toast.makeText(getBaseContext(), data.getRucCli(),
                                Toast.LENGTH_LONG).show();
                        break;
                    case 401:

                        break;
                    default:

                        break;
                }




            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                Log.e("error", t.toString());
            }
        });
    }



    }




