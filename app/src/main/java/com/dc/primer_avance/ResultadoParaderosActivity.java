package com.dc.primer_avance;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ResultadoParaderosActivity extends AppCompatActivity implements BusStopAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private BusStopAdapter adapter;
    private List<BusStop> busStopList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_paraderos);

        // Assuming you have a list of BusStop objects
        busStopList = generateBusStopList();

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new BusStopAdapter(this, busStopList, this);

        // Set the layout manager and adapter for the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(BusStop busStop) {
        // Handle item click here
        // For example, start a new activity
        Intent intent = new Intent(this, IniciarEsperaActivity.class);
        // Pass data to the new activity if needed
        intent.putExtra("busStopName", busStop.getName());
        startActivity(intent);
    }

    // This is just an example. Replace it with your actual data source.
    private List<BusStop> generateBusStopList() {
        List<BusStop> busStops = new ArrayList<>();
        // Add your BusStop objects to the list
        busStops.add(new BusStop("Leoncio Prado", "Av. Samuel Alcázar", "Rimac", "empty"));
        busStops.add(new BusStop("Guardia Republicana", "Av. Prol. Tacna", "Rimac", "empty"));
        // Add more items as needed
        return busStops;
    }
}