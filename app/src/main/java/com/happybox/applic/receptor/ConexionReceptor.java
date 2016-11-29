package com.happybox.applic.receptor;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.gson.reflect.TypeToken;
import com.happybox.applic.LicApp;
import com.happybox.applic.R;
import com.happybox.applic.modelo.Categoria;
import com.happybox.applic.modelo.Cliente;
import com.happybox.applic.modelo.Notificacion;
import com.happybox.applic.utilitario.Utilitario;

/**
 * Created by USUARIO on 28/11/2016.
 */

public class ConexionReceptor extends BroadcastReceiver {

    SharedPreferences sharedPreferences;
    int resultado=0;


    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connMgr.getActiveNetworkInfo();

        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()){
           sharedPreferences = context.getSharedPreferences(
                   LicApp.obtenerInstancia().obtenerServicio().PREFS, Context.MODE_PRIVATE);

            String usuario=sharedPreferences.getString(LicApp.obtenerInstancia().obtenerServicio().PREF_USUARIO, "");
            String clave=sharedPreferences.getString(LicApp.obtenerInstancia().obtenerServicio().PREF_CLAVE, "");

            if(!usuario.equals("") && !clave.equals("")){
                procesarLicitacionesaCaducar(context, usuario,  clave);


            }

        }

    }

    public void procesarLicitacionesaCaducar(final Context context, String ruc, String claUsu){

        AndroidNetworking.get(context.getResources().getString(R.string.url_base)+context.getResources().getString(R.string.url_obtiene_cliente))
                .addPathParameter("ruc", ruc)
                .addPathParameter("claUsu", claUsu)
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsParsed(new TypeToken<Cliente>(){}, new ParsedRequestListener<Cliente>() {
                    @Override
                    public void onResponse(final Cliente cliente) {
                        if (cliente.getCodCli() != -1) {
                            for(Categoria cat:cliente.getLstCat()){

                                AndroidNetworking.get(context.getResources().getString(R.string.url_base)+context.getResources().getString(R.string.url_nrolicitaciones_caduca))
                                        .addPathParameter("codCat", ""+cat.getCodCat())
                                        .setTag(this)
                                        .setPriority(Priority.LOW)
                                        .build()
                                        .getAsParsed(new TypeToken<Notificacion>(){}, new ParsedRequestListener<Notificacion>() {
                                                    @Override
                                                    public void onResponse(Notificacion response) {
                                                        resultado+=response.getNumeroNorificacionesPorVencer();
                                                        Utilitario.crearNotificacion(context,R.drawable.ic_check_ok,"AppLic","Hola "+cliente.getRucCli()+" revise la información del app, pues estan por caducar "+resultado+" licitaciones");
                                                    }

                                                    @Override
                                                    public void onError(ANError anError) {
                                                        Toast.makeText(context, "Error al conectarse al API REST "+anError.getErrorDetail(),
                                                                Toast.LENGTH_SHORT).show();
                                                    }

                                                    });


                            }



                        }



                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(context, "Error al conectarse al API REST "+anError.getErrorDetail(),
                                Toast.LENGTH_SHORT).show();
                    }

                });

    }
}
