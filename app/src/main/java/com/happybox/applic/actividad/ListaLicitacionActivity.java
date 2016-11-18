package com.happybox.applic.actividad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;


import com.happybox.applic.R;
import com.happybox.applic.adaptador.LicitacionAdapter;
import com.happybox.applic.endpoint.LicitacionEndPoint;
import com.happybox.applic.modelo.Categoria;
import com.happybox.applic.modelo.Cliente;
import com.happybox.applic.modelo.Licitacion;
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
    List<Licitacion> lstLicitacionesTotales= new ArrayList<Licitacion>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_licitacion);
        lblUsuarioDes =(TextView) findViewById(R.id.lblUsuarioDes);
        lblStatusDes =(TextView) findViewById(R.id.lblStatusDes);
        licitacionRecyclerView = (RecyclerView) findViewById(R.id.licitacionRecyclerView);

        Intent intent = getIntent();
        Cliente cliente = (Cliente) intent.getExtras().getSerializable("cliente");
        lblUsuarioDes.setText("Usuario: "+cliente.getRucCli()+" - "+cliente.getRazSocCli());
        lblStatusDes.setText(cliente.getFlgAboCli()==0?"Estatus: Normal":"Estatus: VIP");

        licitacionLayoutManager = new LinearLayoutManager(this);
        licitacionRecyclerView.setLayoutManager(licitacionLayoutManager);
        procesarLicitaciones(cliente.getLstCat());

    }

    public void procesarLicitaciones(List<Categoria>lstCategorias){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.url_base))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LicitacionEndPoint restLicitacion=retrofit.create(LicitacionEndPoint.class);
        for(Categoria cat:lstCategorias){
            Call<List<Licitacion>> callList = restLicitacion.getLicitaciones(cat.getCodCat());

            callList.enqueue(new Callback<List<Licitacion>>() {
                @Override
                public void onResponse(Call<List<Licitacion>> call, Response<List<Licitacion>> response) {



                    switch (response.code()) {
                        case 200:
                            List<Licitacion> data=response.body();
                            lstLicitacionesTotales.addAll(data);
                            licitacionAdapter = new LicitacionAdapter();
                            licitacionAdapter.setLicitaciones(lstLicitacionesTotales);
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
        }



    }


    }




