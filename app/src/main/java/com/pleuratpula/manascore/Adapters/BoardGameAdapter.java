package com.pleuratpula.manascore.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pleuratpula.manascore.Models.BoardGames;
import com.pleuratpula.manascore.R;

import java.util.List;

public class BoardGameAdapter extends RecyclerView.Adapter<BoardGameAdapter.MyViewHolder> {

    private List<BoardGames> boardGameList;

    public BoardGameAdapter(List<BoardGames> boardGame) {

        this.boardGameList = boardGame;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_item, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        BoardGames boardGames = boardGameList.get(i);

        myViewHolder.name.setText(boardGames.getName());
        myViewHolder.year.setText(String.valueOf(boardGames.getYear()));
        myViewHolder.maxPlayer.setText(String.valueOf(boardGames.getMaxPlayer()));
        myViewHolder.price.setText(boardGames.getPrice());
    }

    @Override
    public int getItemCount() {
        return boardGameList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView year;
        TextView maxPlayer;
        TextView price;

        public MyViewHolder(View view) {
            super(view);

            // Lidhja e itemave me elementet e listes ne RecyclerView
            name = view.findViewById(R.id.nameOfGame);
            year = view.findViewById(R.id.year);
            maxPlayer = view.findViewById(R.id.maxPlayers);
            price = view.findViewById(R.id.price);
        }
    }
}
