package com.example.gamesearch.mvvm.views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.gamesearch.mvvm.viewmodels.SearchViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SearchFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {

    EditText game;
    RecyclerView rv_search;
    String game_name;
    SearchViewModel viewModel;
    RecyclerViewAdapter rv_adapter;
    Button btnSearch;


    public SearchFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.search_menu, container, false);
    }

    BottomNavigationView bottomNavigationView;
    NavController controller;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        game = view.findViewById(R.id.searchName);
        rv_search = view.findViewById(R.id.rv_search);
        btnSearch = view.findViewById(R.id.buttonFilters);
        rv_adapter = new RecyclerViewAdapter();
        rv_search.setAdapter(rv_adapter);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.start();
            }
        });

        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        viewModel.getUserMutableLiveData().observe(getViewLifecycleOwner(), gameListUpdateObserver);

        game.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                game_name = game.getText().toString();
                viewModel.setCur_name(game_name);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        bottomNavigationView = view.findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.page_1);
        controller = Navigation.findNavController(view);
        viewModel.getRepo().setController(controller);
        viewModel.getRepo().setHome(false);
    }


    final Observer<ArrayList<GameCard>> gameListUpdateObserver = new Observer<ArrayList<GameCard>>() {

        @Override
        public void onChanged(ArrayList<GameCard> gameArrayList) {
            rv_adapter = (RecyclerViewAdapter) rv_search.getAdapter();
            rv_adapter.updateGameList(gameArrayList);
            rv_search.setAdapter(rv_adapter);
        }
    };

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.page_1:
                ///controller.navigate(R.id.searchFragment);
                return true;
            case R.id.page_2:
                controller.navigate(R.id.action_searchFragment_to_homeFragment);
                return true;
            case R.id.page_3:
                controller.navigate(R.id.action_searchFragment_to_profileFragment);
                return true;
        }
        return false;
    }
}