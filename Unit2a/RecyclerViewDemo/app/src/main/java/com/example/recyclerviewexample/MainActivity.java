package com.example.recyclerviewexample;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public RecyclerView.Adapter recyclerViewAdapter;
    public RecyclerView.LayoutManager recyclerViewLayoutManager;
    String[] locales = Locale.getISOCountries();
    List<String> countryNames = new ArrayList<>();
    List<String> countryAbbreviations = new ArrayList<>();

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
