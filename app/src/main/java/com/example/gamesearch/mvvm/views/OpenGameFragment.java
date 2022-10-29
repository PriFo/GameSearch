package com.example.gamesearch.mvvm.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.gamesearch.R;
import com.example.gamesearch.mvvm.repos.GameRepo;
import com.example.gamesearch.mvvm.viewmodels.HomeViewModel;
import com.example.gamesearch.mvvm.viewmodels.OpenGameVM;

public class OpenGameFragment extends Fragment {

    Button btnBack;
    TextView gameName;
    TextView gameDev;
    TextView gamePubl;
    TextView gameGenre;
    TextView gameDescr;
    TextView gameSysReq;
    String appid;
    OpenGameVM viewModel;
    NavController controller;


    public OpenGameFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.open_game, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btnBack = view.findViewById(R.id.back);
        gameName = view.findViewById(R.id.gameNameNow);
        gameDev = view.findViewById(R.id.gameDevNow);
        gamePubl = view.findViewById(R.id.gamePubNow);
        gameGenre = view.findViewById(R.id.gameGenreNow);
        gameDescr = view.findViewById(R.id.gameDescrNow);
        gameSysReq = view.findViewById(R.id.gameRequireNow);
        controller = Navigation.findNavController(view);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewModel.getRepo().getHome()) {
                    viewModel.getRepo().setAppid("");
                    controller.navigate(R.id.action_openGameFragment_to_homeFragment);
                }
                else {
                    viewModel.getRepo().setAppid("");
                    controller.navigate(R.id.action_openGameFragment_to_searchFragment);
                }
            }
        });

        viewModel = new ViewModelProvider(this , ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(OpenGameVM.class);

        try {
            appid = viewModel.getRepo().getAppid();
            gameName.setText(viewModel.getRepo().getName());
            gameGenre.setText(viewModel.getRepo().getGenre());
        } catch (Exception e){
            e.printStackTrace();
        }
        gameDev.setText(viewModel.getDeveloper());
        gameDescr.setText(viewModel.getDescription());
        gamePubl.setText(viewModel.getPublisher());
        gameSysReq.setText(viewModel.getSysReq());

    }
}