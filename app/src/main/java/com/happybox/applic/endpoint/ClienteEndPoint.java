package com.happybox.applic.endpoint;

import com.happybox.applic.modelo.Cliente;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by USUARIO on 14/11/2016.
 */

public interface ClienteEndPoint {

    @GET("agenda/clientes/{ruc}/{claUsu}")
    Call<Cliente> getCliente(@Path("ruc") String ruc,@Path("claUsu")  String claUsu);

    @POST("agenda/clientes/nuevo")
    Call<Cliente> insertarCliente(@Body Cliente cliente);


}
