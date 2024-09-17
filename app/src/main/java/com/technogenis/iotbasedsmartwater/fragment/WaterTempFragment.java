package com.technogenis.iotbasedsmartwater.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.technogenis.iotbasedsmartwater.R;


public class WaterTempFragment extends Fragment {

    TextView valueText,date_text,time_text;

    String value,date,time;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_water_temp, container, false);

        init(view);
        currentValue();

        return view;
    }

    void init(View view){
        valueText = view.findViewById(R.id.valueText);
        date_text = view.findViewById(R.id.date_text);
        time_text = view.findViewById(R.id.time_text);
    }

    private void currentValue() {
        DatabaseReference callRef = FirebaseDatabase.getInstance()
                .getReference("CurrentData")
                .child("1000");

        callRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    value = snapshot.child("TemperatureSensor").getValue(String.class);
                    date = snapshot.child("Dated").getValue(String.class);
                    time = snapshot.child("Timed").getValue(String.class);


                    valueText.setText("Water Temperature: "+value);
                    date_text.setText("Date: "+date);
                    time_text.setText("Time: "+time);

                } else {
                    Log.w("Firebase", "Data snapshot doesn't exist");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Data fetch cancelled", error.toException());
            }
        });
    }
}