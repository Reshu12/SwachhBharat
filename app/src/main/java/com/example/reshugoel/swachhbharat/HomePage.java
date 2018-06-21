package com.example.reshugoel.swachhbharat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {

    List<Waste> mwaste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        mwaste= new ArrayList<>();

        mwaste.add(new Waste("2Bin 1Bag","Waste Segregation",R.drawable.waste_segregation));
        mwaste.add(new Waste("Complain Box","Place your Complain",R.drawable.complaint));
        //Here add all functionality card boxes
       /* mwaste.add(new Waste("Vegetable peels","Biodegradable",R.drawable.vegetable));
        mwaste.add(new Waste("Paper","Biodegradable",R.drawable.paper));
        mwaste.add(new Waste("Leaves","Biodegradable",R.drawable.leaves));
        mwaste.add(new Waste("Plastic","Non Biodegradable",R.drawable.plastic));
        mwaste.add(new Waste("Metal Cans","Non Biodegradable",R.drawable.cans));
        mwaste.add(new Waste("Construction waste","Non Biodegradable",R.drawable.construction));
        mwaste.add(new Waste("Electronic gadgets","Non Biodegradable",R.drawable.ewaste));  */

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerid);
        RecyclerViewAdapter recyclerViewAdapter= new RecyclerViewAdapter(this,mwaste);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(recyclerViewAdapter);

    }
}
