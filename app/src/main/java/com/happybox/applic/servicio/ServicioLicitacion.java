package com.happybox.applic.servicio;

import com.happybox.applic.modelo.Cliente;
import com.happybox.applic.modelo.Licitacion;

/**
 * Created by USUARIO on 27/11/2016.
 */

public class ServicioLicitacion {

    private Cliente cliente;
    private Licitacion licitacion;


    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Licitacion getLicitacion() {
        return licitacion;
    }

    public void setLicitacion(Licitacion licitacion) {
        this.licitacion = licitacion;
    }
}
