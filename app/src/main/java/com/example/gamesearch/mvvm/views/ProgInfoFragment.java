package com.example.gamesearch.mvvm.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.gamesearch.R;

public class ProgInfoFragment extends Fragment {

    private Button btnBack;
    private NavController control;

    public ProgInfoFragment() {
    }

    public static ProgInfoFragment newInstance() {
        return new ProgInfoFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.prog_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btnBack = view.findViewById(R.id.backProg);
        control = Navigation.findNavController(view);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                control.navigate(R.id.action_progInfoFragment_to_profileFragment);
            }
        });
    }
}
