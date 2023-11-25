package com.dc.primer_avance;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class InicioAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_app);

        // Agregar un Handler para retrasar la transición a la siguiente actividad (MainActivity)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Después de 4 segundos, inicia la MainActivity
                Intent intent = new Intent(InicioAppActivity.this, RegistrarTarjetaActivity.class);
                startActivity(intent);

                // Cierra la actividad actual para que no se pueda volver atrás con el botón de retroceso
                finish();
            }
        }, 2000);
    }
}