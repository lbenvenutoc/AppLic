package com.happybox.applic.actividad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.happybox.applic.R;
import com.happybox.applic.adaptador.CategoriaAdapter;
import com.happybox.applic.endpoint.CategoriaEndPoint;
import com.happybox.applic.endpoint.ClienteEndPoint;
import com.happybox.applic.modelo.Categoria;
import com.happybox.applic.modelo.Cliente;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RegistroActivity extends AppCompatActivity {

    RecyclerView categoriaRecyclerView;
    LinearLayout linear_pago;
    RadioButton radio_si;
    RadioButton radio_no;
    Spinner cboTipoTarjeta;
    CategoriaAdapter categoriaAdapter;
    LinearLayoutManager categoriaLayoutManager;
    List<Categoria> lstCategoriasTotales= new ArrayList<Categoria>();
    EditText txtRuc;
    EditText txtPassword;
    EditText txtRazonSocial;
    EditText txtNumTarjeta;
    EditText txtCorreo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        cboTipoTarjeta=(Spinner)findViewById(R.id.cboTipoTarjeta);
        categoriaRecyclerView = (RecyclerView) findViewById(R.id.categoriaRecyclerView);
        linear_pago=(LinearLayout) findViewById(R.id.linear_pago);
        txtRuc=(EditText)findViewById(R.id.txtRuc);
        txtPassword=(EditText)findViewById(R.id.txtPassword);
        txtRazonSocial=(EditText)findViewById(R.id.txtRazonSocial);
        txtNumTarjeta=(EditText)findViewById(R.id.txtNumTarjeta);
        txtCorreo=(EditText)findViewById(R.id.txtCorreo);

        radio_si=(RadioButton) findViewById(R.id.radio_si);
        radio_si.setChecked(true);
        radio_no=(RadioButton) findViewById(R.id.radio_no);

        categoriaLayoutManager = new LinearLayoutManager(this);
        categoriaRecyclerView.setLayoutManager(categoriaLayoutManager);
        procesarCategorias();



    }

    public boolean validarSeleccionCategorias(List<Categoria> lstCat) {
        boolean resultado=true;

        for(Categoria cat:lstCat){
            if(cat.getEstaSeleccionado()){
                resultado=false;
                break;
            }
        }

        return resultado;
    }

    public void registrarCliente(View view) {

        boolean cancel = false;
        View focusView = null;

        String rucCli=txtRuc.getText().toString();
        String pasCli=txtPassword.getText().toString();
        String corCli=txtCorreo.getText().toString();
        String razSocCli=txtRazonSocial.getText().toString();
        String numTarCli=txtNumTarjeta.getText().toString();
        String tipTarCli=cboTipoTarjeta.getSelectedItem().toString();

        txtRuc.setError(null);
        txtRazonSocial.setError(null);
        txtCorreo.setError(null);
        txtPassword.setError(null);

        if(TextUtils.isEmpty(rucCli)){
            txtRuc.setError("Ruc es requerido");
            focusView = txtRuc;
            cancel = true;
        }
        else if(rucCli.length()<11){
            txtRuc.setError("Ruc debe tener 11 dígitos");
            focusView = txtRuc;
            cancel = true;
        }
        else if(TextUtils.isEmpty(razSocCli)){
            txtRazonSocial.setError("Razón Social es requerida");
            focusView = txtRazonSocial;
            cancel = true;
        }else if(TextUtils.isEmpty(corCli)){
            txtCorreo.setError("Correo es requerido");
            focusView = txtCorreo;
            cancel = true;
        }else if(!corCli.contains("@")){
            txtCorreo.setError("Correo es inválido");
            focusView = txtCorreo;
            cancel = true;
        }
        else if(TextUtils.isEmpty(pasCli)){
            txtPassword.setError("Clave es requerida");
            focusView = txtPassword;
            cancel = true;
        }

        if(cancel){
            focusView.requestFocus();
        }else{
            if(validarSeleccionCategorias(lstCategoriasTotales)){
                Toast.makeText(getBaseContext(), "Debe seleccionar al menos una categoria",
                        Toast.LENGTH_SHORT).show();
            }else{
                Cliente cliente= new Cliente();
                cliente.setRucCli(rucCli);
                cliente.setPasCli(pasCli);
                cliente.setCorCli(corCli);
                cliente.setRazSocCli(razSocCli);
                cliente.setNumTarCli(numTarCli);
                cliente.setTipTarCli(tipTarCli);
                cliente.setLstCat(lstCategoriasTotales);
                procesarRegistroCliente(cliente);

            }

        }

    }

    public void procesarRegistroCliente(Cliente cliente){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.url_base))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ClienteEndPoint restCliente=retrofit.create(ClienteEndPoint.class);
        Call<Cliente> call = restCliente.insertarCliente(cliente);
        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                Log.i("informacion post", ""+response.code());
                switch (response.code()) {
                    case 200:
                        Cliente data=response.body();
                        Toast.makeText(getBaseContext(), "El cliente con ruc "+data.getRucCli()+" se registro correctamente",
                                Toast.LENGTH_LONG).show();

                        Intent objIntent = new Intent(getBaseContext(), LoginActivity.class);
                        startActivity(objIntent);

                        break;
                    case 401:
                        break;
                    default:
                        break;
                }



            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {

            }
        });
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

    public void mostrarPago(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radio_si:
                if (checked){
                    linear_pago.setVisibility(View.VISIBLE);
                    radio_no.setChecked(false);
                }else{
                    linear_pago.setVisibility(View.GONE);
                }

                    break;
            case R.id.radio_no:
                if (checked){
                    linear_pago.setVisibility(View.GONE);
                    radio_si.setChecked(false);
                }else{
                    linear_pago.setVisibility(View.VISIBLE);
                }

                    break;
        }


    }
}
