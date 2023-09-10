package com.example.nyumbakiganjani;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<Property>  propertyArrayList;
    private Context context;

    public PropertyAdapter(ArrayList<Property> propertyArrayList, Context context) {
        inflater =  LayoutInflater.from(context);
        this.propertyArrayList = propertyArrayList;
        this.context = context;
    }



    @Override
    public PropertyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = inflater.inflate(R.layout.property,parent,false);
        PropertyAdapter.MyViewHolder holder = new PropertyAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PropertyAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.property.setText(propertyArrayList.get(position).getProperty());
        holder.location.setText(propertyArrayList.get(position).getLocation());
        holder.duration.setText(propertyArrayList.get(position).getDuration());
        holder.image.setImageResource(propertyArrayList.get(position).getImage());
        holder.price.setText(String.valueOf(propertyArrayList.get(position).getPrice()));
        holder.rooms.setText(String.valueOf(propertyArrayList.get(position).getRooms()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "You have clicked " + propertyArrayList.get(position).getProperty(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {

        return propertyArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView property, price,duration,location,rooms;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);
            property = itemView.findViewById(R.id.desc_text);
            price = itemView.findViewById(R.id.price_text);
            location = itemView.findViewById(R.id.location_text);
            duration =  itemView.findViewById(R.id.pay_duration);
            image = itemView.findViewById(R.id.image_item);
            rooms = itemView.findViewById(R.id.status_text);

        }
    }
}
