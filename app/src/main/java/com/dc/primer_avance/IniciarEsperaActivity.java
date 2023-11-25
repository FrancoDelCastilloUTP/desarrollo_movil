package com.dc.primer_avance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class IniciarEsperaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iniciar_espera);

        // Retrieve data passed from the clicked item
        Intent intent = getIntent();
        if (intent.hasExtra("busStopName")) {
            String busStopName = intent.getStringExtra("busStopName");
            // Use the data as needed
        }

        Button finalizarEsperaButton = findViewById(R.id.button);
        finalizarEsperaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle "Finalizar Espera" button click
                // For example, start a new activity
                Intent finalizarEsperaIntent = new Intent(IniciarEsperaActivity.this, FinalizarEsperaActivity.class);
                startActivity(finalizarEsperaIntent);
            }
        });

        Button cancelarRegistroButton = findViewById(R.id.button7);
        cancelarRegistroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle "Cancelar Registro" button click
                // For example, start MainActivity
                Intent mainActivityIntent = new Intent(IniciarEsperaActivity.this, MainActivity.class);
                startActivity(mainActivityIntent);
            }
        });

        Button reportarUnidadButton = findViewById(R.id.button8);
        reportarUnidadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle "Reportar Unidad" button click
                // For example, start ReportarUnidadActivity
                Intent reportarUnidadIntent = new Intent(IniciarEsperaActivity.this, ReportarUnidadActivity.class);
                startActivity(reportarUnidadIntent);
            }
        });

    }
}