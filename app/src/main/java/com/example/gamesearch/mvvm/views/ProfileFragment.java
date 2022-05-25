package com.example.gamesearch.mvvm.views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.gamesearch.R;
import com.example.gamesearch.mvvm.repos.UsersRepo;
import com.example.gamesearch.mvvm.viewmodels.LoginViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class ProfileFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Button btnLogIn;
    private Button btnRecently;
    private Button btnFavorites;
    private Button btnLang;
    private Button btnProgInfo;
    private FirebaseAuth mAuth;
    private TextView nickname;
    private UsersRepo user;

    public ProfileFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        user = new UsersRepo();
        return inflater.inflate(R.layout.profile, container, false);
    }

    BottomNavigationView bottomNavigationView;
    NavController controller;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        bottomNavigationView = view.findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.page_3);
        controller = Navigation.findNavController(view);

        nickname = view.findViewById(R.id.textLogin);

        btnLogIn = view.findViewById(R.id.editProfBut);

        btnFavorites = view.findViewById(R.id.favorBut);
        if (mAuth.getCurrentUser() != null) {
            if (!Objects.equals(mAuth.getCurrentUser().getEmail(), "")) {
                btnFavorites.setClickable(true);
                btnFavorites.setAlpha((float) 1.0);
                btnFavorites.setOnClickListener(view1 -> controller.navigate(R.id.action_profileFragment_to_favoritesFragment));
                nickname.setText(user.getLogin());
                btnLogIn.setText("Edit profile");
                btnLogIn.setOnClickListener(view12 -> controller.navigate(R.id.action_profileFragment_to_editProfFragment));
            }
        }
        else {

            btnLogIn.setOnClickListener(view13 -> controller.navigate(R.id.action_profileFragment_to_loginFragment));
        }

        btnLang = view.findViewById(R.id.languageBut);
        btnProgInfo = view.findViewById(R.id.progInfoBut);
        btnRecently = view.findViewById(R.id.recentlyBut);

        btnLang.setOnClickListener(view14 -> controller.navigate(R.id.action_profileFragment_to_languageFragment));

        btnRecently.setOnClickListener(view15 -> controller.navigate(R.id.action_profileFragment_to_recentlyFragment));

        btnProgInfo.setOnClickListener(view16 -> controller.navigate(R.id.action_profileFragment_to_progInfoFragment));
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.page_1:
                controller.navigate(R.id.action_profileFragment_to_searchFragment);
                return true;
            case R.id.page_2:
                controller.navigate(R.id.action_profileFragment_to_homeFragment);
                return true;
            case R.id.page_3:
                ///controller.navigate(R.id.profileFragment);
                return true;
        }
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAuth.getCurrentUser() == null) {

            btnFavorites.setClickable(false);
            btnFavorites.setAlpha((float) 0.5);
            nickname.setText("Nickname");
            btnLogIn.setText("Log in");

            btnLogIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    controller.navigate(R.id.action_profileFragment_to_loginFragment);
                }
            });
        }
        else {
            btnLogIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    controller.navigate(R.id.action_profileFragment_to_editProfFragment);
                }
            });
        }
    }
}