package com.example.recyclerviewexample;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import com.example.recyclerviewexample.RecyclerAdapter.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public Adapter<RecyclerViewHolder> recyclerViewAdapter;
    public LayoutManager recyclerViewLayoutManager;
    private final String[] locales = Locale.getISOCountries();
    private final List<String> countryNames = new ArrayList<>();
    private final List<String> countryAbbreviations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCountryAbbreviationsAndNames();
        recyclerView = findViewById(R.id.rv);
        recyclerViewAdapter = new RecyclerAdapter(countryNames, countryAbbreviations);
        recyclerViewLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void setCountryAbbreviationsAndNames() {
        for (String countryCode : locales) {
            Locale obj = new Locale("", countryCode);
            countryNames.add(obj.getDisplayCountry());
            countryAbbreviations.add(obj.getCountry());
        }
    }
}
