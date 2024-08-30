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

public class FutureAdapter extends RecyclerView.Adapter<FutureAdapter.viewholder> {
    ArrayList<FutureDomain> items;
    Context context ;

    public FutureAdapter(ArrayList<FutureDomain> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context= parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_future,parent,false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.dattxt.setText(items.get(position).getDay());
        holder.status.setText(items.get(position).getStatus());
        holder.highttxt.setText(items.get(position).getHightemp()+"°");
        holder.lowtxt.setText(items.get(position).getLowtemp()+"°");

        int drawableRessourceId = holder.itemView.getResources().getIdentifier(items.get(position).getPicpath(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableRessourceId)
                .into(holder.pic2);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView dattxt,status,lowtxt,highttxt;
        ImageView pic2;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            dattxt = itemView.findViewById(R.id.dattxt);
            status = itemView.findViewById(R.id.status);
            lowtxt = itemView.findViewById(R.id.lowtxt);
            highttxt = itemView.findViewById(R.id.highttxt);
            pic2= itemView.findViewById(R.id.pic2);
        }
    }
}
