package com.example.reshugoel.swachhbharat;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Segregation extends AppCompatActivity {



    private EditText editText;
    private RecyclerView recyclerView;

    DatabaseReference databaseReference;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segregation);
        editText=(EditText)findViewById(R.id.name);
        button=(Button)findViewById(R.id.search);
        recyclerView=(RecyclerView)findViewById(R.id.reseg);
        databaseReference=FirebaseDatabase.getInstance().getReference("Items");

        /*firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference("Items");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
*/

       // ActionBar actionBar=getSupportActionBar();
      // actionBar.setCategory("");


        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //retrieve data passed by adapter

        // Intent intent= getIntent();
        /*
            String name= intent.getExtra().getString("Name");
            int img= intent.getExtra().getInt("Thumbnail");


         */

       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

              firebaseSearch();
           }
       });



    }
    private void firebaseSearch(){

        String searchData = editText.getText().toString();
        Query query= databaseReference.orderByChild("search").equalTo(searchData);
        FirebaseRecyclerAdapter<SegContent,ItemViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<SegContent, ItemViewHolder>(
                SegContent.class,R.layout.seglist,ItemViewHolder.class,query
        ) {
            @Override
            protected void populateViewHolder(ItemViewHolder viewHolder, SegContent model, int position) {
                viewHolder.setDetails(model.getCategory(),model.getImage());
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

   /* @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Category,ViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Category, ViewHolder>(
                Category.class, R.layout.itemimage,ViewHolder.class,databaseReference
        ) {
            @Override
            protected void populateViewHolder(ViewHolder viewHolder, Category model, int position) {

                viewHolder.setDetails(getApplicationContext(),Category.getCategory(),Category.getImage());

            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }*/
    public static class ItemViewHolder extends RecyclerView.ViewHolder{

        View mView;
        public ItemViewHolder(View itemView)
        {
            super(itemView);
            mView=itemView;
        }
        public void setDetails(String category, String image){
           TextView textView=(TextView)mView.findViewById(R.id.te);
            ImageView imageView=(ImageView)mView.findViewById(R.id.re);
            textView.setText(category);
            Picasso.get().load(image).into(imageView);
        }
   }


}
