package com.example.gamesearch.mvvm.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamesearch.R;
import com.example.gamesearch.mvvm.models.GameCard;
import com.example.gamesearch.mvvm.repos.GameRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public GameCard getGame(int position) {
        return gameArrayList.get(position);
    }

    ArrayList<GameCard> gameArrayList;

    public RecyclerViewAdapter() {
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
        RecyclerViewViewHolder viewHolder = (RecyclerViewViewHolder) holder;

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
        LinearLayout card;
        private final GameRepo repo = GameRepo.getInstance();

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.card);
            imgView_icon = itemView.findViewById(R.id.gameImage);
            txtView_name = itemView.findViewById(R.id.textGameName);
            txtView_genre = itemView.findViewById(R.id.textGameGenre);
            txtView_price = itemView.findViewById(R.id.textGamePrice);
            NavController controller = repo.getController();

            card.setOnClickListener(v -> {
                int position = this.getAdapterPosition();
                RecyclerViewAdapter adapter = (RecyclerViewAdapter) this.getBindingAdapter();
                if (adapter != null) {
                    GameCard game = adapter.getGame(position);
                    repo.setAppid(game.getAppid());
                }
                if (!repo.getHome()) {
                    if (!Objects.equals(repo.getAppid(), "")) {
                        repo.setName(txtView_name.getText().toString());
                        repo.setGenre(txtView_genre.getText().toString());
                        controller.navigate(R.id.action_searchFragment_to_openGameFragment);
                    } else
                        Toast.makeText(card.getContext().getApplicationContext(), "Невозможно получить игру", Toast.LENGTH_SHORT).show();
                } else {
                    if (!repo.getAppid().isEmpty()) {
                        repo.setName(txtView_name.getText().toString());
                        repo.setGenre(txtView_genre.getText().toString());
                        controller.navigate(R.id.action_homeFragment_to_openGameFragment);
                    } else
                        Toast.makeText(card.getContext().getApplicationContext(), "Невозможно получить игру", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}