package com.pleuratpula.manascore.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pleuratpula.manascore.Models.ResultGame;
import com.pleuratpula.manascore.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<ResultGame> rg ;
    Context context;
    int gamesno;

    public MyAdapter(Context ct, ArrayList<ResultGame> resultGames)
    {
        rg=resultGames;
        context=ct;
        gamesno=resultGames.size();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_matchdetails,parent,false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.matchid.setText(rg.get(position).getId());
        holder.numberofplayers.setText(rg.get(position).getPlayers().size());


    }

    @Override
    public int getItemCount() {
        return gamesno;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView matchid,numberofplayers;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            matchid = itemView.findViewById(R.id.matchid);
            numberofplayers=itemView.findViewById(R.id.NoOfPlayers);
        }
    }
}
