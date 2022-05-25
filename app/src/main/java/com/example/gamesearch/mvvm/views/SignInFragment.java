package com.example.gamesearch.mvvm.views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.gamesearch.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SignInFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    NavController controller;
    private EditText emailEdit;
    private EditText passEdit;
    private EditText loginEdit;
    private Button signInBtn;

    public SignInFragment() {}

    public static SignInFragment newInstance() {
        return new SignInFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sign_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bottomNavigationView = view.findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        controller = Navigation.findNavController(view);
        emailEdit = view.findViewById(R.id.emailTextS);
        loginEdit = view.findViewById(R.id.loginTextS);
        passEdit = view.findViewById(R.id.passTextS);
        signInBtn = view.findViewById(R.id.button2);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.page_1:
                controller.navigate(R.id.searchFragment);
                return true;
            case R.id.page_2:
                controller.navigate(R.id.homeFragment);
                return true;
            case R.id.page_3:
                controller.navigate(R.id.profileFragment);
                return true;
        }
        return false;
    }
}
