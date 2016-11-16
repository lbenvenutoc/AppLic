package com.happybox.applic.endpoint;

import com.happybox.applic.modelo.Categoria;
import com.happybox.applic.modelo.Cliente;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by USUARIO on 14/11/2016.
 */

public interface CategoriaEndPoint {

    @GET("agenda/categorias")
    Call<List<Categoria>> getCategorias();


}
