package com.example.reshugoel.swachhbharat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {

    List<Waste> mwaste;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private Button logout1;
    int backpress=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth=FirebaseAuth.getInstance();
        setContentView(R.layout.activity_home_page);
        logout1= findViewById(R.id.btn_logout);
        logout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(HomePage.this,Login.class));
            }
        });
//        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
//        authStateListener= new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                if (firebaseAuth.getCurrentUser()==null){
//                    startActivity(new Intent(HomePage.this,Login.class));
//                    finish();
//                }
//            }
//        };
        mwaste= new ArrayList<>();
        mwaste.add(new Waste("2Bin 1Bag","Waste Segregation",R.drawable.waste_segregation));
        mwaste.add(new Waste("Complaint Box","Place your Complain",R.drawable.complaint));
        mwaste.add(new Waste("General Knowledge","Expand your Horizons",R.drawable.awareness));
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
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(recyclerViewAdapter);

    }
    public void onBackPressed(){
        backpress = (backpress + 1);
        Toast.makeText(getApplicationContext(), " Press Back again to Exit ", Toast.LENGTH_SHORT).show();

        if (backpress>1) {
            super.onBackPressed();
        }
    }
}
