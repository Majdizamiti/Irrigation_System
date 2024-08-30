package com.example.irrigation_system;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class HourlyAdapter extends RecyclerView.Adapter<HourlyAdapter.viewholder> {
    ArrayList<Hourly> items;
    Context context ;

    public HourlyAdapter(ArrayList<Hourly> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context= parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_hourly,parent,false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.hourtext.setText(items.get(position).getHour());
        holder.hourtemp.setText(String.valueOf(items.get(position).getTemp()+"°"));


        int drawableRessourceId = holder.itemView.getResources().getIdentifier(items.get(position).getPicpath(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableRessourceId)
                .into(holder.pic);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView hourtext,hourtemp;
        ImageView pic;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            hourtemp=itemView.findViewById(R.id.hourtemp);
            hourtext=itemView.findViewById(R.id.hourtext);
            pic=itemView.findViewById(R.id.pic);
        }
    }
}
