package com.example.reshugoel.swachhbharat;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Reshu Goel on 6/16/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private Context mcontext;
    private List<Waste> mdata;

    public RecyclerViewAdapter(Context mcontext, List<Waste> mdata) {
        this.mcontext = mcontext;
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
        view=layoutInflater.inflate(R.layout.cardview_item_book,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.waste_name.setText(mdata.get(position).getName());
        holder.img_waste_thumbnail.setImageResource(mdata.get(position).getThumbnail());

        //Set listener for cards here
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position)
                {
                    case 0: Intent intent=new Intent(mcontext,Segregation.class);
                            mcontext.startActivity(intent); break;
                    case 1: Intent intent1 =new Intent(mcontext,Complain.class);
                        mcontext.startActivity(intent1); break;
                }

                //passing data to activity
               // Intent intent= new Intent(mcontext,Segregation.class);
              /*  intent.putExtra("Waste",mdata.get(position).getName());
                intent.putExtra("Type",mdata.get(position).getType());  */
                //start activity
               // mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView waste_name;
        ImageView img_waste_thumbnail;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);

            waste_name=(TextView)itemView.findViewById(R.id.segregation);
            img_waste_thumbnail=(ImageView) itemView.findViewById(R.id.waste);
            cardView=(CardView)itemView.findViewById(R.id.card);

        }
    }
}
