package com.example.nyumbakiganjani;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private  PropertyAdapter propertyAdapter;
    private ArrayList<Property> propertyArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView =  view.findViewById(R.id.recycler_view2);


        createProperty();



        return view;
    }

    private void createProperty(){
        propertyArrayList =  new ArrayList<>();
        propertyArrayList.add(new Property("House for rent", "280K/Month", "3/6 Months", "Iyumbu Dodoma", "", 1,R.mipmap.house1,4));
        propertyArrayList.add(new Property("Room for rent", "80K/Month", "3/6 Months", "Kikuyu Dodoma", "", 1,R.mipmap.house2,4));
        propertyArrayList.add(new Property("House for rent", "280K/Month", "3/6 Months", "Makulu Dodoma", "", 1,R.mipmap.house3,4));
        propertyArrayList.add(new Property("House for rent", "200K/Month", "3/6 Months", "Area C Dodoma", "", 1,R.mipmap.house1,4));
        propertyArrayList.add(new Property("House for rent", "250K/Month", "3/6 Months", "Nzuguni Dodoma", "", 1,R.mipmap.house2,4));
        propertyArrayList.add(new Property("House for rent", "280K/Month", "3/6 Months", "Iyumbo Dodoma", "", 1,R.mipmap.house3,4));
        propertyArrayList.add(new Property("House for rent", "280K/Month", "3/6 Months", "Iyumbu Dodoma", "", 1,R.mipmap.house1,4));
        propertyArrayList.add(new Property("Room for rent", "80K/Month", "3/6 Months", "Kikuyu Dodoma", "", 1,R.mipmap.house2,4));
        propertyArrayList.add(new Property("House for rent", "280K/Month", "3/6 Months", "Makulu Dodoma", "", 1,R.mipmap.house3,4));

        propertyAdapter =  new PropertyAdapter(propertyArrayList, getContext());
        recyclerView.setAdapter(propertyAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        recyclerView.setNestedScrollingEnabled(false);
    }
}