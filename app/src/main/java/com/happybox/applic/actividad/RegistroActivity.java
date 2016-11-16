package com.happybox.applic.actividad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;

import com.happybox.applic.R;
import com.happybox.applic.adaptador.CategoriaAdapter;
import com.happybox.applic.adaptador.LicitacionAdapter;
import com.happybox.applic.endpoint.CategoriaEndPoint;
import com.happybox.applic.modelo.Categoria;
import com.happybox.applic.modelo.Licitacion;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RegistroActivity extends AppCompatActivity {

    RecyclerView categoriaRecyclerView;
    Spinner cboTipoTarjeta;
    CategoriaAdapter categoriaAdapter;
    LinearLayoutManager categoriaLayoutManager;
    List<Categoria> lstCategoriasTotales= new ArrayList<Categoria>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        cboTipoTarjeta=(Spinner)findViewById(R.id.cboTipoTarjeta);
        categoriaRecyclerView = (RecyclerView) findViewById(R.id.categoriaRecyclerView);

        categoriaLayoutManager = new LinearLayoutManager(this);
        categoriaRecyclerView.setLayoutManager(categoriaLayoutManager);
        procesarCategorias();



    }

    public void registrarCliente(View view) {

        Intent objIntent = new Intent(this, LoginActivity.class);
        startActivity(objIntent);

    }

    public void procesarCategorias(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.url_base))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CategoriaEndPoint restCategoria=retrofit.create(CategoriaEndPoint.class);

        Call<List<Categoria>> callList = restCategoria.getCategorias();
        callList.enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {

                switch (response.code()) {
                    case 200:
                        List<Categoria> data=response.body();
                        lstCategoriasTotales.addAll(data);
                        categoriaAdapter = new CategoriaAdapter();
                        categoriaAdapter.setCategorias(lstCategoriasTotales);
                        categoriaRecyclerView.setAdapter(categoriaAdapter);
                        break;
                    case 401:
                        break;
                    default:
                        break;
                }


            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                Log.e("error", t.toString());
            }
        });


    }
}
