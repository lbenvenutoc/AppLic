package com.happybox.applic.modelo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by USUARIO on 11/11/2016.
 */

public class Licitacion implements Serializable{

    private int codLic;
    private String nomLic;
    private String desLic;
    private String norAplLic;
    private double valRef;
    private String fecPubLic;
    private String fecTerLic;
    private String monLic;
    private int verSeaLic;
    private int estLic;

    private int codCat;
    private String desCat;

    //DATOS ENTIDAD
    private int codEnt;
    private String desEnt;
    private String dirEnt;


    public String getDirEnt() {
        return dirEnt;
    }

    public void setDirEnt(String dirEnt) {
        this.dirEnt = dirEnt;
    }

    public int getCodLic() {
        return codLic;
    }

    public void setCodLic(int codLic) {
        this.codLic = codLic;
    }

    public String getNomLic() {
        return nomLic;
    }

    public void setNomLic(String nomLic) {
        this.nomLic = nomLic;
    }

    public String getDesLic() {
        return desLic;
    }

    public void setDesLic(String desLic) {
        this.desLic = desLic;
    }

    public String getNorAplLic() {
        return norAplLic;
    }

    public void setNorAplLic(String norAplLic) {
        this.norAplLic = norAplLic;
    }

    public double getValRef() {
        return valRef;
    }

    public void setValRef(double valRef) {
        this.valRef = valRef;
    }

    public String getFecPubLic() {
        return fecPubLic;
    }

    public void setFecPubLic(String fecPubLic) {
        this.fecPubLic = fecPubLic;
    }

    public String getFecTerLic() {
        return fecTerLic;
    }

    public void setFecTerLic(String fecTerLic) {
        this.fecTerLic = fecTerLic;
    }

    public String getMonLic() {
        return monLic;
    }

    public void setMonLic(String monLic) {
        this.monLic = monLic;
    }

    public int getVerSeaLic() {
        return verSeaLic;
    }

    public void setVerSeaLic(int verSeaLic) {
        this.verSeaLic = verSeaLic;
    }

    public int getEstLic() {
        return estLic;
    }

    public void setEstLic(int estLic) {
        this.estLic = estLic;
    }

    public int getCodEnt() {
        return codEnt;
    }

    public void setCodEnt(int codEnt) {
        this.codEnt = codEnt;
    }

    public int getCodCat() {
        return codCat;
    }

    public void setCodCat(int codCat) {
        this.codCat = codCat;
    }

    public String getDesEnt() {
        return desEnt;
    }

    public void setDesEnt(String desEnt) {
        this.desEnt = desEnt;
    }

    public String getDesCat() {
        return desCat;
    }

    public void setDesCat(String desCat) {
        this.desCat = desCat;
    }

    @Override
    public String toString() {
        return "Licitacion{" +
                "codLic=" + codLic +
                ", nomLic='" + nomLic + '\'' +
                ", desLic='" + desLic + '\'' +
                ", norAplLic='" + norAplLic + '\'' +
                ", valRef=" + valRef +
                ", fecPubLic=" + fecPubLic +
                ", fecTerLic=" + fecTerLic +
                ", monLic='" + monLic + '\'' +
                ", verSeaLic=" + verSeaLic +
                ", estLic=" + estLic +
                ", codEnt=" + codEnt +
                ", codCat=" + codCat +
                ", desEnt='" + desEnt + '\'' +
                ", desCat='" + desCat + '\'' +
                '}';
    }
}
