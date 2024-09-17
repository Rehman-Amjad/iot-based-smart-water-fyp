package com.technogenis.iotbasedsmartwater.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.technogenis.iotbasedsmartwater.R;
import com.technogenis.iotbasedsmartwater.model.HistoryModel;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder>{


    private Context context;
    private List<HistoryModel> mDatalist;

    public HistoryAdapter(Context context, List<HistoryModel> mDatalist) {
        this.context = context;
        this.mDatalist = mDatalist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myview= LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);


        return new MyViewHolder(myview);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        HistoryModel model = mDatalist.get(position);


        holder.tvId.setText("ID: " + model.getId());
        holder.tvTds.setText("TDS Sensor: " + model.getTDSSensor());
        holder.tvTemp.setText("Temperature Sensor: " + model.getTemperatureSensor());
        holder.tvWaterLevel.setText("Water Level: " + model.getWaterlavel());
        holder.tvDate.setText("Date: " + model.getDated());
        holder.tvTime.setText("Time: " + model.getTimed());
        holder.tvTurb.setText("Turbidity Sensor: " + model.getTurbiditySensor());




    }

    @Override
    public int getItemCount() {
        return mDatalist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvId,tvTds,tvTemp,tvTurb,tvDate,tvTime,tvWaterLevel;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvId=itemView.findViewById(R.id.tvId);
            tvTds=itemView.findViewById(R.id.tvTds);
            tvTemp=itemView.findViewById(R.id.tvTemp);
            tvTurb=itemView.findViewById(R.id.tvTurb);
            tvDate=itemView.findViewById(R.id.tvDate);
            tvTime=itemView.findViewById(R.id.tvTime);
            tvWaterLevel=itemView.findViewById(R.id.tvWaterLevel);
        }
    }
}
