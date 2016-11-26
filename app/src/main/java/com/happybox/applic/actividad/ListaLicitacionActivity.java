package com.happybox.applic.actividad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;


import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.gson.reflect.TypeToken;
import com.happybox.applic.R;
import com.happybox.applic.adaptador.LicitacionAdapter;
import com.happybox.applic.modelo.Categoria;
import com.happybox.applic.modelo.Cliente;
import com.happybox.applic.modelo.Licitacion;
import java.util.ArrayList;
import java.util.List;



public class ListaLicitacionActivity extends AppCompatActivity {
    RecyclerView licitacionRecyclerView;
    TextView clienteTextView;
    TextView estatusTextView;
    LicitacionAdapter licitacionAdapter;
    LinearLayoutManager licitacionLayoutManager;
    List<Licitacion> lstLicitacionesTotales= new ArrayList<Licitacion>();
    int flgEstatusCliente=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_licitacion);
        clienteTextView =(TextView) findViewById(R.id.clienteTextView);
        estatusTextView =(TextView) findViewById(R.id.estatusTextView);
        licitacionRecyclerView = (RecyclerView) findViewById(R.id.licitacionRecyclerView);

        Intent intent = getIntent();
        Cliente cliente = (Cliente) intent.getExtras().getSerializable("cliente");
        clienteTextView.setText("Usuario: "+cliente.getRucCli()+" - "+cliente.getRazSocCli());
        estatusTextView.setText(cliente.getFlgAboCli()==0?"Estatus: Normal":"Estatus: VIP");
        flgEstatusCliente=cliente.getFlgAboCli();
        licitacionLayoutManager = new LinearLayoutManager(this);
        licitacionRecyclerView.setLayoutManager(licitacionLayoutManager);
        procesarLicitaciones(cliente.getLstCat());

    }

    public void procesarLicitaciones(List<Categoria>lstCategorias){

        for(Categoria cat:lstCategorias){
            AndroidNetworking.get(getResources().getString(R.string.url_base)+getResources().getString(R.string.url_lista_licitaciones))
                    .addPathParameter("codCat", ""+cat.getCodCat())
                    .addPathParameter("flgAboCli", ""+flgEstatusCliente)
                    .setTag(this)
                    .setPriority(Priority.LOW)
                    .build()
                    .getAsParsed(new TypeToken<List<Licitacion>>(){}, new ParsedRequestListener<List<Licitacion>>() {
                        @Override
                        public void onResponse(List<Licitacion> lstLicitaciones) {
                            lstLicitacionesTotales.addAll(lstLicitaciones);
                            licitacionAdapter = new LicitacionAdapter();
                            licitacionAdapter.setLicitaciones(lstLicitacionesTotales);
                            licitacionRecyclerView.setAdapter(licitacionAdapter);
                        }

                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(getBaseContext(), "Error al conectarse al API REST",
                                    Toast.LENGTH_SHORT).show();
                        }

                    });
        }



    }


    }




