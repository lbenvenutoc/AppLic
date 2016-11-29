package com.happybox.applic.utilitario;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.google.gson.Gson;
import com.happybox.applic.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by USUARIO on 25/11/2016.
 */

public class Utilitario {

        public static JSONObject convertirClaseaObjeto(Object objeto){
            Gson gson = new Gson();
            String jsonString = gson.toJson(objeto);
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(jsonString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jsonObject;
        }

    public static void crearNotificacion(Context context,int icono, String titulo, String descripcion){
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(icono)
                        .setContentTitle(titulo)
                        .setContentText(descripcion);
        // Add as notification
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

}
