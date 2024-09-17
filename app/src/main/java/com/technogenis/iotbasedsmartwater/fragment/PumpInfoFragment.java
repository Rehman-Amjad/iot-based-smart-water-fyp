package com.technogenis.iotbasedsmartwater.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.technogenis.iotbasedsmartwater.R;


public class PumpInfoFragment extends Fragment {

    TextView date_text,time_text;
    Button btnOpen,btnClose;
    String date,time;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pump_info, container, false);

        init(view);
        currentValue();

        btnOpen.setOnClickListener(v -> {
            // Get the reference to the specific location in Firebase
            DatabaseReference callRef = FirebaseDatabase.getInstance()
                    .getReference("Automatic");

            callRef.child("PumpInfo").setValue("1")
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Bubble Pump On", Toast.LENGTH_SHORT).show();
                        } else {
                            // If the task fails, log the error or notify the user
                            Toast.makeText(getActivity(), "Failed to save data", Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        btnClose.setOnClickListener(v -> {
            // Get the reference to the specific location in Firebase
            DatabaseReference callRef = FirebaseDatabase.getInstance()
                    .getReference("Automatic");

            callRef.child("PumpInfo").setValue("0")
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Bubble Pump Off", Toast.LENGTH_SHORT).show();
                        } else {
                            // If the task fails, log the error or notify the user
                            Toast.makeText(getActivity(), "Failed to save data", Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        return view;
    }

    void init(View view){
        date_text = view.findViewById(R.id.date_text);
        time_text = view.findViewById(R.id.time_text);
        btnOpen = view.findViewById(R.id.btnOpen);
        btnClose = view.findViewById(R.id.btnClose);
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
                    date = snapshot.child("Dated").getValue(String.class);
                    time = snapshot.child("Timed").getValue(String.class);


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