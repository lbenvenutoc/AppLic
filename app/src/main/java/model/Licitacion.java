package model;

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
    private Date fecPubLic;
    private Date fecTerLic;
    private String monLic;
    private int verSeaLic;
    private int estLic;
    private int codEnt;
    private int codCat;

    private String desEnt;
    private String desCat;

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

    public Date getFecPubLic() {
        return fecPubLic;
    }

    public void setFecPubLic(Date fecPubLic) {
        this.fecPubLic = fecPubLic;
    }

    public Date getFecTerLic() {
        return fecTerLic;
    }

    public void setFecTerLic(Date fecTerLic) {
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
}
