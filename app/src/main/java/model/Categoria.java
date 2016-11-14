package model;

import java.io.Serializable;

/**
 * Created by USUARIO on 13/11/2016.
 */

public class Categoria implements Serializable{

    private int codCat;
    private String desCat;
    private int estSus;

    public int getCodCat() {
        return codCat;
    }

    public void setCodCat(int codCat) {
        this.codCat = codCat;
    }

    public String getDesCat() {
        return desCat;
    }

    public void setDesCat(String desCat) {
        this.desCat = desCat;
    }

    public int getEstSus() {
        return estSus;
    }

    public void setEstSus(int estSus) {
        this.estSus = estSus;
    }
}
