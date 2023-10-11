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
    private ArrayList<BookingModel> bookingAdapterArrayList;
    private Context context;

    public BookingAdapter(ArrayList<BookingModel> bookingAdapterArrayList, Context context) {
        inflater = LayoutInflater.from(context);
        this.bookingAdapterArrayList = bookingAdapterArrayList;
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
        holder.property.setText(bookingAdapterArrayList.get(position).getProperty_name());
        holder.userName.setText(bookingAdapterArrayList.get(position).getUser());
        holder.status.setText(bookingAdapterArrayList.get(position).getBook_status());
        holder.dateBooked.setText(bookingAdapterArrayList.get(position).getDate_booked());
    }

    @Override
    public int getItemCount() {
        return bookingAdapterArrayList.size();
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
