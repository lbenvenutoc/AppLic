package com.happybox.applic.utilitario;

import com.google.gson.Gson;

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

}
