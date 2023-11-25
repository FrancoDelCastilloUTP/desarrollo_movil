package com.dc.primer_avance;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;


public class CargandoParaderosActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cargando_paraderos);

        // Espera y luego cambia a la actividad ParaderoListActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(CargandoParaderosActivity.this, ResultadoParaderosActivity.class);
                startActivity(intent);
                finish(); // Esto cierra la actividad actual para que no pueda volver atrás con el botón "Atrás"
            }
        }, 2000); // 4000 milisegundos = 4 segundos
    }
}