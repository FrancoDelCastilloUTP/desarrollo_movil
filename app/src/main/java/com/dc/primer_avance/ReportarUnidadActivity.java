package com.dc.primer_avance;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
public class ReportarUnidadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reportar_unidad);

        Button enviarButton = findViewById(R.id.button6);
        enviarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle "Enviar" button click
                // For example, start IniciarEsperaActivity
                Intent iniciarEsperaIntent = new Intent(ReportarUnidadActivity.this, IniciarEsperaActivity.class);
                startActivity(iniciarEsperaIntent);
            }
        });

        Button cancelarButton = findViewById(R.id.buttonCancelar);
        cancelarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event, for example, navigate to IniciarEsperaActivity
                Intent intent = new Intent(ReportarUnidadActivity.this, IniciarEsperaActivity.class);
                startActivity(intent);
            }
        });

    }
}