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

public class RecentlyFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {

    public RecentlyFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.recently, container, false);
    }

    BottomNavigationView bottomNavigationView;
    NavController controller;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        bottomNavigationView = view.findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        controller = Navigation.findNavController(view);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.page_1:
                controller.navigate(R.id.action_recentlyFragment_to_searchFragment);
                return true;
            case R.id.page_2:
                controller.navigate(R.id.action_recentlyFragment_to_homeFragment);
                return true;
            case R.id.page_3:
                controller.navigate(R.id.action_recentlyFragment_to_profileFragment);
                return true;
        }
        return false;
    }
}