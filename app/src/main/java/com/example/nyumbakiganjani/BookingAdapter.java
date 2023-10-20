package com.example.nyumbakiganjani;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<BookingModel> bookingModelArrayList;

    public BookingAdapter(ArrayList<BookingModel> bookingModelArrayList, Context context) {
        inflater = LayoutInflater.from(context);
        this.bookingModelArrayList = bookingModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public BookingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view  =  inflater.inflate(R.layout.booking_item,parent,false);
        BookingAdapter.ViewHolder holder = new BookingAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookingAdapter.ViewHolder holder, int position) {
        holder.property.setText(bookingModelArrayList.get(position).getProperty_name());
        holder.userName.setText(bookingModelArrayList.get(position).getUser());
        holder.status.setText(bookingModelArrayList.get(position).getBook_status());
        holder.dateBooked.setText(bookingModelArrayList.get(position).getDate_booked());
    }

    @Override
    public int getItemCount() {
        return bookingModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView property,userName,status,dateBooked;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            property = itemView.findViewById(R.id.propertyName);
            userName = itemView.findViewById(R.id.customerName);
            status = itemView.findViewById(R.id.bookingStatus);
            dateBooked = itemView.findViewById(R.id.timeBooked);
        }
    }
}
