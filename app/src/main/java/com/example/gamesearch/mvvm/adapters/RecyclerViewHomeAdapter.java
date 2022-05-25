package com.example.gamesearch.mvvm.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamesearch.R;
import com.example.gamesearch.mvvm.models.GameCard;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewHomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<GameCard> gameArrayList;

    public RecyclerViewHomeAdapter() {
        this.gameArrayList = new ArrayList<>();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_card,parent,false);
        return new RecyclerViewViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        GameCard game = gameArrayList.get(position);
        RecyclerViewViewHolder viewHolder= (RecyclerViewViewHolder) holder;

        viewHolder.txtView_name.setText(game.getGameName());
        viewHolder.txtView_genre.setText(game.getGameGenre());
        viewHolder.txtView_price.setText(game.getGamePrice());
    }

    @Override
    public int getItemCount() {
        return gameArrayList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateGameList(final List<GameCard> gameArrayList) {
        this.gameArrayList.clear();
        this.gameArrayList = (ArrayList<GameCard>) gameArrayList;
        notifyDataSetChanged();
    }

    static class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
        ImageView imgView_icon;
        TextView txtView_name;
        TextView txtView_genre;
        TextView txtView_price;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView_icon = itemView.findViewById(R.id.gameImage);
            txtView_name = itemView.findViewById(R.id.textGameName);
            txtView_genre = itemView.findViewById(R.id.textGameGenre);
            txtView_price = itemView.findViewById(R.id.textGamePrice);


        }
    }
}