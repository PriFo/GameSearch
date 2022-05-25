package com.example.gamesearch.mvvm.views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.gamesearch.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {

    public HomeFragment(){
        // require a empty public constructor
    }

    BottomNavigationView bottomNavigationView;
    NavController controller;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        bottomNavigationView = view.findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.page_2);
        controller = Navigation.findNavController(view);
    }

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