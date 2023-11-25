package com.dc.primer_avance;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
public class FinalizarEsperaActivity extends  AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finalizar_espera);


        Button volverAlMenuPrincipalButton = findViewById(R.id.button3);
        volverAlMenuPrincipalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle "Volver al menú principal" button click
                // For example, start MainActivity
                Intent mainActivityIntent = new Intent(FinalizarEsperaActivity.this, MainActivity.class);
                startActivity(mainActivityIntent);
            }
        });
    }
}