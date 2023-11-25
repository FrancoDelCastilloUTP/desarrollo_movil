package com.dc.primer_avance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class MainActivity extends AppCompatActivity {

    private TextView cardIdTextView;
    private TextView freeTripsTextView;
    private TextView visitsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardIdTextView = findViewById(R.id.tarjetaId);
        freeTripsTextView = findViewById(R.id.freeTrips);
        visitsTextView = findViewById(R.id.visitas);

        // Obtener el Intent que inició esta actividad
        Intent intent = getIntent();

        // Verificar si el Intent tiene extras y contiene la clave "registro"
        if (intent.hasExtra("registro")) {
            String registroString = intent.getStringExtra("registro");

            if (registroString != null && !registroString.isEmpty()) {
                try {
                    // Convertir la cadena JSON a un JsonObject
                    JsonObject registro = new JsonParser().parse(registroString).getAsJsonObject();

                    // Verificar si el JsonObject "registro" contiene las claves necesarias
                    if (registro.has("card_id") && registro.has("free_trips") && registro.has("visits")) {
                        // Obtener los valores necesarios del JsonObject
                        String cardId = registro.get("card_id").getAsString();
                        int freeTrips = registro.get("free_trips").getAsInt();
                        int visits = registro.get("visits").getAsInt();

                        // Actualizar los TextViews con los valores obtenidos
                        cardIdTextView.setText("Tarjeta: " + cardId);
                        freeTripsTextView.setText("Viajes gratis acumulados: " + freeTrips);
                        visitsTextView.setText("Visitas registradas: " + visits);
                    }
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Error al analizar los datos JSON", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "La cadena JSON es nula o vacía", Toast.LENGTH_SHORT).show();
            }
        }

        Button btnAbrirCargandoParaderos = findViewById(R.id.btnAbrirCargandoParaderos);

        btnAbrirCargandoParaderos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Al hacer clic, inicia la actividad con el nuevo layout
                Intent intent = new Intent(MainActivity.this, CargandoParaderosActivity.class);
                startActivity(intent);
            }
        });
    }
}