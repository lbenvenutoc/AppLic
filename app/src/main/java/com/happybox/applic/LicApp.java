package com.happybox.applic;

import android.app.Application;

import com.happybox.applic.receptor.ConexionReceptor;
import com.happybox.applic.servicio.ServicioLicitacion;

/**
 * Created by USUARIO on 27/11/2016.
 */

public class LicApp extends Application {

    static LicApp instancia;
    ServicioLicitacion servicio = new ServicioLicitacion();

    public LicApp() {
        super();
        instancia = this;
    }

    public static LicApp obtenerInstancia() {
        return instancia;
    }

    public ServicioLicitacion obtenerServicio() {
        return servicio;
    }



}
