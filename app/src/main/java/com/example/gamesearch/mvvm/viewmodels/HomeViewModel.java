package com.example.gamesearch.mvvm.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.gamesearch.mvvm.models.GameCard;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    MutableLiveData<ArrayList<GameCard>> gameLiveData;
    ArrayList<GameCard> gameArrayList;

    public HomeViewModel() {
        gameLiveData = new MutableLiveData<>();

        // call your Rest API in init method
        init();
    }

    public MutableLiveData<ArrayList<GameCard>> getUserMutableLiveData(){
        return gameLiveData;
    }

    public void init(){
        populateList();
        gameLiveData.setValue(gameArrayList);
    }

    public void populateList(){

        GameCard game = new GameCard();
        game.setGameName("Darknight");
        game.setGameGenre("Best rating movie");
        game.setGamePrice("100$");

        gameArrayList = new ArrayList<>();
        gameArrayList.add(game);
        gameArrayList.add(game);
        gameArrayList.add(game);
        gameArrayList.add(game);
        gameArrayList.add(game);
        gameArrayList.add(game);
    }
}