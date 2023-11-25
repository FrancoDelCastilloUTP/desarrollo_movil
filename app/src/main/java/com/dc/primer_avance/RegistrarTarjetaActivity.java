package com.dc.primer_avance;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.JsonObject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrarTarjetaActivity extends AppCompatActivity {

    private EditText editTextNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_tarjeta);

        editTextNumber = findViewById(R.id.editTextNumber);
        Button registerButton = findViewById(R.id.button);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codigo = editTextNumber.getText().toString();
                validarTarjeta(codigo);
            }
        });

        Button salirButton = findViewById(R.id.button5);

        salirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Cierra la actividad actual y, por lo tanto, vuelve a la actividad anterior o cierra la aplicación si no hay ninguna actividad anterior.
                finish();
            }
        });
    }

    private void validarTarjeta(String codigo) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://busapputp.000webhostapp.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        // Cambia la llamada a validarTarjeta
        Call<JsonObject> call = apiService.validarTarjeta(codigo);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (response.body().has("estado")) {
                        int estado = response.body().get("estado").getAsInt();

                        if (estado == 1) {
                            // Tarjeta válida, proceder con el registro
                            JsonObject registro = response.body().getAsJsonObject("registro");

                            // Redirige a la actividad principal
                            Intent intent = new Intent(RegistrarTarjetaActivity.this, MainActivity.class);
                            intent.putExtra("registro", registro.toString());
                            startActivity(intent);

                            // Finaliza la actividad actual
                            finish();

                        } else if (estado == 2) {
                            // La tarjeta no existe o no está registrada
                            registrarTarjeta(codigo);
                        } else {
                            // Manejar otros estados si es necesario
                            Toast.makeText(RegistrarTarjetaActivity.this, "Estado desconocido en la validación: " + estado, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegistrarTarjetaActivity.this, "Respuesta no válida del servidor en la validación", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegistrarTarjetaActivity.this, "Error en la validación de la tarjeta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(RegistrarTarjetaActivity.this, "Error en la llamada de validación de tarjeta: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void registrarTarjeta(String codigo) {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://busapputp.000webhostapp.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<JsonObject> call = apiService.registrarTarjeta(codigo);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    // Verificar si la respuesta contiene el campo "estado"
                    if (response.body().has("estado")) {
                        int estado = response.body().get("estado").getAsInt();

                        if (estado == 1) {
                            // Tarjeta registrada exitosamente
                            Toast.makeText(RegistrarTarjetaActivity.this, "Tarjeta registrada con éxito", Toast.LENGTH_SHORT).show();
                            // Puedes acceder a otros campos del JSON si es necesario
                            String mensaje = response.body().get("mensaje").getAsString();
                            JsonObject registro = response.body().getAsJsonObject("registro");

                            // Redirige a la actividad principal
                            Intent intent = new Intent(RegistrarTarjetaActivity.this, MainActivity.class);
                            intent.putExtra("registro", registro.toString());
                            startActivity(intent);

                            // Finaliza la actividad actual
                            finish();


                        } else if (estado == 2) {
                            // No se pudo registrar la tarjeta
                            Toast.makeText(RegistrarTarjetaActivity.this, "Tarjeta no existe o ya fue registrada", Toast.LENGTH_SHORT).show();
                        } else {
                            // Manejar otros estados si es necesario
                            Toast.makeText(RegistrarTarjetaActivity.this, "Estado desconocido: " + estado, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // El campo "estado" no está presente en la respuesta
                        Toast.makeText(RegistrarTarjetaActivity.this, "Respuesta no válida del servidor", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Manejar la respuesta de error aquí
                    Toast.makeText(RegistrarTarjetaActivity.this, "Error al registrar la tarjeta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                // Manejar el fallo de la llamada aquí
                Toast.makeText(RegistrarTarjetaActivity.this, "Error en servidor" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}