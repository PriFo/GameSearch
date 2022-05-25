package com.example.gamesearch.mvvm.views;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gamesearch.R;
import com.example.gamesearch.mvvm.viewmodels.LoginViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener{

    private EditText emailEdit, passEdit;
    private TextView signUpText;
    private TextView frgtnPass;
    private Button signInBtn;
    private LoginViewModel viewModel;
    BottomNavigationView bottomNavigationView;
    NavController controller;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this , ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(LoginViewModel.class);
        viewModel.getUserData().observe(this, firebaseUser -> {
            if (firebaseUser != null){
                controller.navigate(R.id.action_loginFragment_to_profileFragment);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.log_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        emailEdit = view.findViewById(R.id.emailTextL);
        passEdit = view.findViewById(R.id.passTextL);
        signUpText = view.findViewById(R.id.textLinkSignIn);
        signInBtn = view.findViewById(R.id.logInBut);
        frgtnPass = view.findViewById(R.id.textLinkForgPass);


        bottomNavigationView = view.findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        controller = Navigation.findNavController(view);

        signUpText.setOnClickListener(v -> controller.navigate(R.id.action_loginFragment_to_signInFragment));

        signInBtn.setOnClickListener(v -> {
            String email = emailEdit.getText().toString();
            String pass = passEdit.getText().toString();

            if (!email.isEmpty() && !pass.isEmpty()){
                viewModel.signIn(email , pass);
            }
        });

        frgtnPass.setOnClickListener(view1 -> controller.navigate(R.id.action_loginFragment_to_profileFragment));
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.page_1:
                controller.navigate(R.id.action_loginFragment_to_searchFragment);
                return true;
            case R.id.page_2:
                controller.navigate(R.id.action_loginFragment_to_homeFragment);
                return true;
            case R.id.page_3:
                controller.navigate(R.id.action_loginFragment_to_profileFragment);
                return true;
        }
        return false;
    }

}