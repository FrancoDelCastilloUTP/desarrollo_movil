package com.dc.primer_avance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BusStopAdapter extends RecyclerView.Adapter<BusStopAdapter.ViewHolder> {
    private List<BusStop> busStops;
    private Context context;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(BusStop busStop);
    }
    // Constructor
    public BusStopAdapter(Context context, List<BusStop> busStops, OnItemClickListener listener) {
        this.context = context;
        this.busStops = busStops;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.paradero_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BusStop busStop = busStops.get(position);

        // Configurar la información del paradero
        holder.nameTextView.setText(busStop.getName());
        holder.addressTextView.setText(busStop.getAddress());
        holder.districtTextView.setText(busStop.getDistrict());
        holder.stateTextView.setText(busStop.getState());

        // Set click listener for the item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Trigger the item click
                if (listener != null) {
                    listener.onItemClick(busStops.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return busStops.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView addressTextView;
        public TextView districtTextView;
        public TextView stateTextView;

        public ViewHolder(View view) {
            super(view);
            nameTextView = view.findViewById(R.id.textView2);
            addressTextView = view.findViewById(R.id.textViewAddress);
            districtTextView = view.findViewById(R.id.textViewDistrict);
            stateTextView = view.findViewById(R.id.textViewState);
        }
    }
}