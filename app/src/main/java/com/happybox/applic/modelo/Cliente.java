package com.happybox.applic.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by USUARIO on 11/11/2016.
 */

public class Cliente implements Serializable{

    private int codCli;
    private String rucCli;
    private String  razSocCli;
    private String  corCli;
    private String  pasCli;
    public Date fecRegCli;
    private String tipTarCli;
    private String  numTarCli;
    private int flgAboCli;
    private int estCli;
    private List<Categoria> lstCat;

    public int getCodCli() {
        return codCli;
    }

    public void setCodCli(int codCli) {
        this.codCli = codCli;
    }

    public String getRucCli() {
        return rucCli;
    }

    public void setRucCli(String rucCli) {
        this.rucCli = rucCli;
    }

    public String getRazSocCli() {
        return razSocCli;
    }

    public void setRazSocCli(String razSocCli) {
        this.razSocCli = razSocCli;
    }

    public String getCorCli() {
        return corCli;
    }

    public void setCorCli(String corCli) {
        this.corCli = corCli;
    }

    public String getPasCli() {
        return pasCli;
    }

    public void setPasCli(String pasCli) {
        this.pasCli = pasCli;
    }

    public Date getFecRegCli() {
        return fecRegCli;
    }

    public void setFecRegCli(Date fecRegCli) {
        this.fecRegCli = fecRegCli;
    }

    public String getTipTarCli() {
        return tipTarCli;
    }

    public void setTipTarCli(String tipTarCli) {
        this.tipTarCli = tipTarCli;
    }

    public String getNumTarCli() {
        return numTarCli;
    }

    public void setNumTarCli(String numTarCli) {
        this.numTarCli = numTarCli;
    }

    public int getFlgAboCli() {
        return flgAboCli;
    }

    public void setFlgAboCli(int flgAboCli) {
        this.flgAboCli = flgAboCli;
    }

    public int getEstCli() {
        return estCli;
    }

    public void setEstCli(int estCli) {
        this.estCli = estCli;
    }

    public List<Categoria> getLstCat() {
        return lstCat;
    }

    public void setLstCat(List<Categoria> lstCat) {
        this.lstCat = lstCat;
    }
}
