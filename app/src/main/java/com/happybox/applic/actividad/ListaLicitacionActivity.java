package com.happybox.applic.actividad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import com.happybox.applic.R;
import com.happybox.applic.adaptador.LicitacionAdapter;
import com.happybox.applic.endpoint.ClienteEndPoint;
import com.happybox.applic.endpoint.LicitacionEndPoint;
import com.happybox.applic.modelo.Categoria;
import com.happybox.applic.modelo.Cliente;
import com.happybox.applic.modelo.Licitacion;
import com.happybox.applic.servicio.LicitacionService;

import java.security.AccessController;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ListaLicitacionActivity extends AppCompatActivity {
    RecyclerView licitacionRecyclerView;
    TextView lblUsuarioDes;
    TextView lblStatusDes;
    LicitacionAdapter licitacionAdapter;
    LinearLayoutManager licitacionLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_licitacion);
        lblUsuarioDes =(TextView) findViewById(R.id.lblUsuarioDes);
        lblStatusDes =(TextView) findViewById(R.id.lblStatusDes);
        licitacionRecyclerView = (RecyclerView) findViewById(R.id.licitacionRecyclerView);

        Intent intent = getIntent();
        Cliente cliente = (Cliente) intent.getExtras().getSerializable("cliente");
        lblUsuarioDes.setText(cliente.getRucCli()+" - "+cliente.getRazSocCli());
        lblStatusDes.setText(cliente.getFlgAboCli()==0?"Normal":"VIP");


        licitacionLayoutManager = new LinearLayoutManager(this);
        licitacionRecyclerView.setLayoutManager(licitacionLayoutManager);
        procesarLicitaciones(cliente.getLstCat().get(0).getCodCat());


/*
        licitacionRecyclerView = (RecyclerView) findViewById(R.id.licitacionRecyclerView);
        licitacionAdapter = new LicitacionAdapter();
        */

    /*

        Intent intent = getIntent();
        String ruc = intent.getStringExtra("ruc");
        String claUsu = intent.getStringExtra("claUsu");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.url_base))
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ClienteEndPoint restCliente=retrofit.create(ClienteEndPoint.class);
        LicitacionEndPoint restLicitacion=retrofit.create(LicitacionEndPoint.class);

        Call<Cliente> call = restCliente.getCliente(ruc,claUsu);

        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                Log.i("response status", ""+response.code());
                switch (response.code()) {
                    case 200:
                        Cliente data = response.body();
                        lblUsuarioDes.setText(data.getRucCli()+" - "+data.getRazSocCli());
                        lblStatusDes.setText(data.getFlgAboCli()==0?"Normal":"VIP");
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




        Call<List<Licitacion>> callList = restLicitacion.getLicitaciones(1);

        callList.enqueue(new Callback<List<Licitacion>>() {
            @Override
            public void onResponse(Call<List<Licitacion>> call, Response<List<Licitacion>> response) {
                Log.i("response status", ""+response.code());
                switch (response.code()) {
                    case 200:
                        List<Licitacion> data = response.body();
                        licitacionAdapter.setLicitaciones(data);


                        break;
                    case 401:
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onFailure(Call<List<Licitacion>> call, Throwable t) {
                Log.e("error", t.toString());
            }
        });

*/
/*
        licitacionLayoutManager = new LinearLayoutManager(this);
        licitacionRecyclerView.setAdapter(licitacionAdapter);
        licitacionRecyclerView.setLayoutManager(licitacionLayoutManager);
*/

    }

    public void procesarLicitaciones(int categoria){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.url_base))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LicitacionEndPoint restLicitacion=retrofit.create(LicitacionEndPoint.class);
        //for(Categoria cat:lstCategorias){
            Call<List<Licitacion>> callList = restLicitacion.getLicitaciones(categoria);

            callList.enqueue(new Callback<List<Licitacion>>() {
                @Override
                public void onResponse(Call<List<Licitacion>> call, Response<List<Licitacion>> response) {
                    Log.i("response status", ""+response.code());
                    switch (response.code()) {
                        case 200:
                            List<Licitacion> data=response.body();
                            licitacionAdapter = new LicitacionAdapter();
                            licitacionAdapter.setLicitaciones(data);
                            licitacionRecyclerView.setAdapter(licitacionAdapter);

                            break;
                        case 401:
                            break;
                        default:
                            break;
                    }

                }

                @Override
                public void onFailure(Call<List<Licitacion>> call, Throwable t) {
                    Log.e("error", t.toString());
                }
            });
       // }



    }


    }




