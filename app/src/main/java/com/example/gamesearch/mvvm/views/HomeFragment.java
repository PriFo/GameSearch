package com.example.gamesearch.mvvm.views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamesearch.R;
import com.example.gamesearch.mvvm.adapters.RecyclerViewAdapter;
import com.example.gamesearch.mvvm.models.GameCard;
import com.example.gamesearch.mvvm.viewmodels.HomeViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {

    public HomeFragment(){
        // require a empty public constructor
    }


    TextView gameName;
    TextView gameGenre;
    TextView gamePrice;
    LinearLayout pop_game;
    HomeViewModel viewModel;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    BottomNavigationView bottomNavigationView;
    NavController controller;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        pop_game = view.findViewById(R.id.pop_game);
        gameName = view.findViewById(R.id.gameNamePop2);
        gameGenre = view.findViewById(R.id.gameGenrePop2);
        gamePrice = view.findViewById(R.id.gamePricePop2);
        bottomNavigationView = view.findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.page_2);
        controller = Navigation.findNavController(view);
        recyclerView = view.findViewById(R.id.rv_home);
        recyclerViewAdapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(recyclerViewAdapter);
        viewModel = new ViewModelProvider(this , ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(HomeViewModel.class);
        viewModel.getUserMutableLiveData().observe(getViewLifecycleOwner(), gameListUpdateObserver);

        gameName.setText(viewModel.getTmp_gmCrd().getGameName());
        gameGenre.setText(viewModel.getTmp_gmCrd().getGameGenre());
        gamePrice.setText(viewModel.getTmp_gmCrd().getGamePrice());
        viewModel.getRepo().setController(controller);
        viewModel.getRepo().setHome(true);

        pop_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.getRepo().setAppid(viewModel.getAppid_pop());
                viewModel.getRepo().setName(gameName.getText().toString());
                viewModel.getRepo().setGenre(gameGenre.getText().toString());
                controller.navigate(R.id.action_homeFragment_to_openGameFragment);
            }
        });
    }

    final Observer<ArrayList<GameCard>> gameListUpdateObserver = new Observer<ArrayList<GameCard>>() {

        @Override
        public void onChanged(ArrayList<GameCard> gameArrayList) {
            recyclerViewAdapter = (RecyclerViewAdapter) recyclerView.getAdapter();
            recyclerViewAdapter.updateGameList(gameArrayList);
            recyclerView.setAdapter(recyclerViewAdapter);
        }
    };

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.page_1:
                controller.navigate(R.id.action_homeFragment_to_searchFragment);
                return true;
            case R.id.page_2:
                ///controller.navigate(R.id.homeFragment);
                return true;
            case R.id.page_3:
                controller.navigate(R.id.action_homeFragment_to_profileFragment);
                return true;
        }
        return false;
    }
}