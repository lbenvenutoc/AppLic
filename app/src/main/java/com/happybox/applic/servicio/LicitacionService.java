package com.happybox.applic.servicio;

import android.util.Log;

import com.happybox.applic.R;
import com.happybox.applic.endpoint.ClienteEndPoint;
import com.happybox.applic.modelo.Cliente;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by USUARIO on 14/11/2016.
 */

public class LicitacionService {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8090/spring-rest-servidor/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    Cliente respuesta = new Cliente();

    ClienteEndPoint rest=retrofit.create(ClienteEndPoint.class);


    public Cliente obtenerCliente(String ruc, String claUsu){
        Call<Cliente> call = rest.getCliente(ruc,claUsu);

        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                Log.i("response status", ""+response.code());
                respuesta = response.body();

            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                Log.e("error", t.toString());
            }
        });

        return respuesta;

    }

}
