package com.dc.primer_avance;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    @FormUrlEncoded
    @POST("Validar_tarjeta.php")
    Call<JsonObject> validarTarjeta(@Field("codigo") String codigo);
    @FormUrlEncoded
    @POST("Registrar_tarjeta.php")
    Call<JsonObject> registrarTarjeta(@Field("codigo") String codigo);
}