package com.example.test;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    LayoutInflater inflater;
    List<Users> users;
    int i = 0;

    public Adapter(Context ctx, List<Users> users) {
        this.inflater = LayoutInflater.from(ctx);
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_leaderboards_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.position.setText(users.get(position).getPosition());
        holder.pseudo_user.setText(users.get(position).getNameUser());
        holder.points.setText(users.get(position).getScoreUser());
        Log.d("BindViewHolder: ", "From item count");
    }

    @Override
    public int getItemCount() {

        Log.d("ItemCount: ", "From item count");
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView position, pseudo_user, points;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            position = itemView.findViewById(R.id.classement);
            pseudo_user = itemView.findViewById(R.id.pseudo_user);
            points = itemView.findViewById(R.id.points);
            Log.d("ViewHolder: ", "From view holder");
        }
    }
}
