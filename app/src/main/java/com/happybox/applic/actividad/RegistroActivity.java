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
    LinearLayout pagoLinearLayout;
    RadioButton siRadioButton;
    RadioButton noRadioButton;
    Spinner tipoTarjetaSpinner;
    CategoriaAdapter categoriaAdapter;
    LinearLayoutManager categoriaLayoutManager;
    List<Categoria> lstCategoriasTotales= new ArrayList<Categoria>();
    EditText rucAutoCompleteTextView;
    EditText claveAutoCompleteTextView;
    EditText razonSocialAutoCompleteTextView;
    EditText numeroTarjetaAutoCompleteTextView;
    EditText correoAutoCompleteTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        tipoTarjetaSpinner=(Spinner)findViewById(R.id.tipoTarjetaSpinner);
        categoriaRecyclerView = (RecyclerView) findViewById(R.id.categoriaRecyclerView);
        pagoLinearLayout=(LinearLayout) findViewById(R.id.pagoLinearLayout);
        rucAutoCompleteTextView=(EditText)findViewById(R.id.rucAutoCompleteTextView);
        claveAutoCompleteTextView=(EditText)findViewById(R.id.claveAutoCompleteTextView);
        razonSocialAutoCompleteTextView=(EditText)findViewById(R.id.razonSocialAutoCompleteTextView);
        numeroTarjetaAutoCompleteTextView=(EditText)findViewById(R.id.numeroTarjetaAutoCompleteTextView);
        correoAutoCompleteTextView=(EditText)findViewById(R.id.correoAutoCompleteTextView);

        siRadioButton=(RadioButton) findViewById(R.id.siRadioButton);
        siRadioButton.setChecked(true);
        noRadioButton=(RadioButton) findViewById(R.id.noRadioButton);

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

        String rucCli=rucAutoCompleteTextView.getText().toString();
        String pasCli=claveAutoCompleteTextView.getText().toString();
        String corCli=correoAutoCompleteTextView.getText().toString();
        String razSocCli=razonSocialAutoCompleteTextView.getText().toString();
        String numTarCli=numeroTarjetaAutoCompleteTextView.getText().toString();
        String tipTarCli=tipoTarjetaSpinner.getSelectedItem().toString();

        rucAutoCompleteTextView.setError(null);
        razonSocialAutoCompleteTextView.setError(null);
        correoAutoCompleteTextView.setError(null);
        claveAutoCompleteTextView.setError(null);

        if(TextUtils.isEmpty(rucCli)){
            rucAutoCompleteTextView.setError("Ruc es requerido");
            focusView = rucAutoCompleteTextView;
            cancel = true;
        }
        else if(rucCli.length()<11){
            rucAutoCompleteTextView.setError("Ruc debe tener 11 dígitos");
            focusView = rucAutoCompleteTextView;
            cancel = true;
        }
        else if(TextUtils.isEmpty(razSocCli)){
            razonSocialAutoCompleteTextView.setError("Razón Social es requerida");
            focusView = razonSocialAutoCompleteTextView;
            cancel = true;
        }else if(TextUtils.isEmpty(corCli)){
            correoAutoCompleteTextView.setError("Correo es requerido");
            focusView = correoAutoCompleteTextView;
            cancel = true;
        }else if(!corCli.contains("@")){
            correoAutoCompleteTextView.setError("Correo es inválido");
            focusView = correoAutoCompleteTextView;
            cancel = true;
        }
        else if(TextUtils.isEmpty(pasCli)){
            claveAutoCompleteTextView.setError("Clave es requerida");
            focusView = claveAutoCompleteTextView;
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
                if(siRadioButton.isChecked()){
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
            case R.id.siRadioButton:
                if (checked){
                    pagoLinearLayout.setVisibility(View.VISIBLE);
                    noRadioButton.setChecked(false);
                }else{
                    pagoLinearLayout.setVisibility(View.GONE);
                }

                    break;
            case R.id.noRadioButton:
                if (checked){
                    pagoLinearLayout.setVisibility(View.GONE);
                    siRadioButton.setChecked(false);
                }else{
                    pagoLinearLayout.setVisibility(View.VISIBLE);
                }

                    break;
        }


    }
}
