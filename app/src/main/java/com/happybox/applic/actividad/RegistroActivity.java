package com.happybox.applic.actividad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.gson.reflect.TypeToken;
import com.happybox.applic.R;
import com.happybox.applic.adaptador.CategoriaAdapter;
import com.happybox.applic.modelo.Categoria;
import com.happybox.applic.modelo.Cliente;
import com.happybox.applic.utilitario.Utilitario;
import java.util.ArrayList;
import java.util.List;


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
                if(radio_si.isChecked()){
                    cliente.setFlgAboCli(1);
                }else{
                    cliente.setFlgAboCli(0);
                }
                cliente.setLstCat(lstCategoriasTotales);
                procesarRegistroCliente(cliente);

            }

        }

    }

    public void procesarRegistroCliente(Cliente cliente){

            AndroidNetworking.post(getResources().getString(R.string.url_base)+getResources().getString(R.string.url_nuevo_cliente))
                    .addJSONObjectBody(Utilitario.convertirClaseaObjeto(cliente))
                    .setTag(this)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsParsed(new TypeToken<Cliente>(){}, new ParsedRequestListener<Cliente>() {
                        @Override
                        public void onResponse(Cliente cliente) {
                            if(cliente.getCodCli()==-2){
                                Toast.makeText(getBaseContext(), "El cliente con RUC "+cliente.getRucCli()+" ya se encuentra registrado",
                                        Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getBaseContext(), "El cliente con RUC "+cliente.getRucCli()+" se registró correctamente",
                                        Toast.LENGTH_SHORT).show();
                                Intent objIntent = new Intent(getBaseContext(), LoginActivity.class);
                                startActivity(objIntent);
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(getBaseContext(), "Error al conectarse al API REST "+anError.getErrorDetail(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });





    }

    public void procesarCategorias(){

        AndroidNetworking.get(getResources().getString(R.string.url_base)+getResources().getString(R.string.url_lista_categorias))
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsParsed(new TypeToken<List<Categoria>>(){}, new ParsedRequestListener<List<Categoria>>() {
                    @Override
                    public void onResponse(List<Categoria> lstCategorias) {
                        lstCategoriasTotales.addAll(lstCategorias);
                        categoriaAdapter = new CategoriaAdapter();
                        categoriaAdapter.setCategorias(lstCategoriasTotales);
                        categoriaRecyclerView.setAdapter(categoriaAdapter);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getBaseContext(), "Error al conectarse al API REST",
                                Toast.LENGTH_SHORT).show();
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
