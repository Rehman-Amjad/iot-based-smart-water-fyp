package com.technogenis.iotbasedsmartwater.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.technogenis.iotbasedsmartwater.R;


public class HomeFragment extends Fragment {

        ImageView gifImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

                gifImageView = view.findViewById(R.id.gifImageView);
        Glide.with(requireActivity())
                .asGif()
                .load(R.drawable.aquarium_one)
                .into(gifImageView);

        return view;
    }
}