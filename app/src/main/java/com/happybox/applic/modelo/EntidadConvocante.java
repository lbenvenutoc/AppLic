package com.happybox.applic.modelo;

import java.io.Serializable;

/**
 * Created by USUARIO on 11/11/2016.
 */

public class EntidadConvocante implements Serializable{

    private int codEnt;
    private String rucEnt;
    private String desEnt;
    private String dirEnt;
    public int estEnt;

    public int getCodEnt() {
        return codEnt;
    }

    public void setCodEnt(int codEnt) {
        this.codEnt = codEnt;
    }

    public String getRucEnt() {
        return rucEnt;
    }

    public void setRucEnt(String rucEnt) {
        this.rucEnt = rucEnt;
    }

    public String getDesEnt() {
        return desEnt;
    }

    public void setDesEnt(String desEnt) {
        this.desEnt = desEnt;
    }

    public String getDirEnt() {
        return dirEnt;
    }

    public void setDirEnt(String dirEnt) {
        this.dirEnt = dirEnt;
    }

    public int getEstEnt() {
        return estEnt;
    }

    public void setEstEnt(int estEnt) {
        this.estEnt = estEnt;
    }
}
