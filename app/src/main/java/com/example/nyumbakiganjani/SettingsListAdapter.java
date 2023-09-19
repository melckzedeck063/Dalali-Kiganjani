package com.example.nyumbakiganjani;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SettingsListAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] values;

    public SettingsListAdapter(Context context, String[] values) {
        super(context, R.layout.list_item, values);
        this.context = context;
        this.values = values;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item, parent, false);

        TextView textView = rowView.findViewById(R.id.list_item_name);
        ImageView imageView = rowView.findViewById(R.id.forward_arrow);

        textView.setText(values[position]);
        imageView.setImageResource(R.drawable.arrow_forward_icon);
        // You can set different icons based on the list item here

        return rowView;
    }
}
