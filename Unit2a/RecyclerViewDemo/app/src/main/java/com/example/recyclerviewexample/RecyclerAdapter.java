package com.example.recyclerviewexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    private final List<String> countryNames;
    private final List<String> countryAbbreviations;

    RecyclerAdapter(List<String> countryNames, List<String> countryAbbreviations) {
        this.countryNames = countryNames;
        this.countryAbbreviations = countryAbbreviations;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.Tx_country.setText(countryNames.get(position));
        holder.Tx_country_abr.setText(countryAbbreviations.get(position));

    }

    @Override
    public int getItemCount() {
        return countryNames.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView Tx_country;
        TextView Tx_country_abr;

        RecyclerViewHolder(View view) {
            super(view);
            Tx_country = view.findViewById(R.id.tx_country_name);
            Tx_country_abr = view.findViewById(R.id.tx_country_name_abr);
        }
    }
}

