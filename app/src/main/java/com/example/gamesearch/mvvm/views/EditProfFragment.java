package com.example.gamesearch.mvvm.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.gamesearch.R;
import com.example.gamesearch.mvvm.viewmodels.LoginViewModel;

public class EditProfFragment extends Fragment {

    private Button btnBack;
    private Button signOut;
    private EditText login;
    private LoginViewModel viewModel;
    private NavController controller;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.edit_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        login = view.findViewById(R.id.nicknameProf);
        controller = Navigation.findNavController(view);
        btnBack = view.findViewById(R.id.backEditProf);
        signOut = view.findViewById(R.id.exitBut);
        viewModel = new ViewModelProvider(this , ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(LoginViewModel.class);

        login.setText((CharSequence) viewModel.getUserData().getValue());

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.signOut();
                controller.navigate(R.id.action_editProfFragment_to_profileFragment);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.action_editProfFragment_to_profileFragment);
            }
        });
    }
}
