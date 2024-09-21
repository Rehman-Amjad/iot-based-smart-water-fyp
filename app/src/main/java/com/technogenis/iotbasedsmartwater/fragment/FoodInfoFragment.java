package com.technogenis.iotbasedsmartwater.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.technogenis.iotbasedsmartwater.R;


public class FoodInfoFragment extends Fragment {

    TextView date_text,time_text,timerText;
    String date,time;
    Button btnOpen;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food_info, container, false);

        init(view);
        currentValue();

        btnOpen.setOnClickListener(v -> {
            DatabaseReference callRef = FirebaseDatabase.getInstance()
                    .getReference("Automatic");

            callRef.child("TrayInfo").setValue("1")
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Food Tray On", Toast.LENGTH_SHORT).show();
                            btnOpen.setVisibility(View.INVISIBLE);
                            timerText.setVisibility(View.VISIBLE);
                            startTimerAnimation();
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
        timerText = view.findViewById(R.id.timerText);
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

    private void startTimerAnimation() {
        // CountDownTimer for 30 seconds
        new CountDownTimer(15000, 1000) {

            public void onTick(long millisUntilFinished) {
                // Update the timer TextView
                timerText.setText("Timer: " + millisUntilFinished / 1000 + "s");

                // Animate the number decreasing
                Animation animFadeOut = AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_out);
                timerText.startAnimation(animFadeOut);
            }

            public void onFinish() {
                // Timer completed, show message
                timerText.setText("Timer: 0s");

                DatabaseReference callRef = FirebaseDatabase.getInstance()
                        .getReference("Automatic");

                callRef.child("TrayInfo").setValue("0")
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                btnOpen.setVisibility(View.VISIBLE);
                                timerText.setVisibility(View.INVISIBLE);
                                Toast.makeText(getActivity(), "Food Tray Off", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "Failed to update value", Toast.LENGTH_SHORT).show();
                            }
                        });
            }

        }.start();
    }
}