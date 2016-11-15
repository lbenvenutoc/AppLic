package com.happybox.applic.endpoint;

import com.happybox.applic.modelo.Cliente;
import com.happybox.applic.modelo.Licitacion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by USUARIO on 14/11/2016.
 */

public interface LicitacionEndPoint {


    @GET("agenda/licitaciones/{codCat}")
    Call<List<Licitacion>> getLicitaciones(@Path("codCat") int codCat);

}
