package com.example.nyumbakiganjani;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<Property>  propertyArrayList;
    private Context context;
    private ImageView imageView;

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
        Glide.with(context)
                .load(propertyArrayList.get(position).getCoverPhoto()+".jpg") // Use imageURl here
                .into(holder.image);
        holder.price.setText(String.valueOf(propertyArrayList.get(position).getPrice()));
        holder.rooms.setText(String.valueOf(propertyArrayList.get(position).getBedrooms()));
        holder.status.setText(propertyArrayList.get(position).getStatus());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "You have clicked " + propertyArrayList.get(position).getProperty(), Toast.LENGTH_SHORT).show();

                Intent intent =  new Intent(context, PropertyDetailActivity.class);

                intent.putExtra("property", propertyArrayList.get(position).getProperty());
                intent.putExtra("location", propertyArrayList.get(position).getLocation());
                intent.putExtra("price", propertyArrayList.get(position).getPrice());
                intent.putExtra("duration", propertyArrayList.get(position).getDuration());
                intent.putExtra("bedrooms", propertyArrayList.get(position).getBedrooms());
                intent.putExtra("bathrooms", propertyArrayList.get(position).getBathrooms());
                intent.putExtra("parking", propertyArrayList.get(position).getParking());
                intent.putExtra("description", propertyArrayList.get(position).getDescription());
                intent.putExtra("image", propertyArrayList.get(position).getCoverPhoto());
                intent.putExtra("owner_id", propertyArrayList.get(position).getUser_id());
                intent.putExtra("status", propertyArrayList.get(position).getStatus());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return propertyArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView property, price,duration,location,rooms,status;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);
            property = itemView.findViewById(R.id.desc_text);
            price = itemView.findViewById(R.id.price_text);
            location = itemView.findViewById(R.id.location_text);
            duration =  itemView.findViewById(R.id.pay_duration);
            image = itemView.findViewById(R.id.image_item);
            rooms = itemView.findViewById(R.id.status_text);
            status = itemView.findViewById(R.id.status_msg);

        }
    }
}
