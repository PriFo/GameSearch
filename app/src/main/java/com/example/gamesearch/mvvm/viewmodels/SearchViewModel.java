package com.example.gamesearch.mvvm.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.gamesearch.JSONParser;
import com.example.gamesearch.mvvm.models.GameCard;
import com.example.gamesearch.mvvm.repos.GameRepo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class SearchViewModel extends ViewModel {

    private static String url_all = "https://api.steampowered.com/ISteamApps/GetAppList/v0002/?format=json";
    private static String url_game = "https://store.steampowered.com/api/appdetails?appids=";

    // JSON Node names
    private static final String TAG_ID = "appid";
    private static final String TAG_NAME = "name";
    private static final String TAG_DESCRIPTION = "description";
    private static final String TAG_PRICE = "price";
    private static final String TAG_GENRE = "genres";
    private final JSONParser jParser = new JSONParser();
    private String cur_name;
    private GameRepo repo = GameRepo.getInstance();

    public GameRepo getRepo() {
        return repo;
    }

    MutableLiveData<ArrayList<GameCard>> gameLiveData;
    ArrayList<String> appid_games;

    ArrayList<GameCard> gameArrayList;
    GameCard tmp_gmCrd;

    public GameCard getTmp_gmCrd() {
        return tmp_gmCrd;
    }

    public void setCur_name(String cur_name) {
        this.cur_name = cur_name;
    }

    public void start(){
        init();
    }

    public SearchViewModel() throws JSONException {
        gameLiveData = new MutableLiveData<>();
        tmp_gmCrd = new GameCard();
        // call your Rest API in init method
    }

    public MutableLiveData<ArrayList<GameCard>> getUserMutableLiveData() {
        return gameLiveData;
    }

    public void init() {
            try {
                getGame();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            gameLiveData.postValue(gameArrayList);
    }

    public void getGame() throws JSONException {

        // Creating JSON Parser instance

        // getting JSON string from URL
        System.out.println("Тут не пиздец?");
        JSONObject json = jParser.getJSONFromUrl(url_all);
        if (json != null) {
            JSONArray jAllGames = json.getJSONObject("applist").getJSONArray("apps");
            for (int i = 0; i < jAllGames.length(); i++) {
                JSONObject jTMP = jAllGames.getJSONObject(i);
                if (jTMP.getString("name").toLowerCase().contains(cur_name.toLowerCase())) {
                    appid_games.add(jTMP.getString("appid"));
                }
            }
        }
        if (!appid_games.isEmpty()) {
            for (int i = 0; i < appid_games.size(); i++) {
                json = jParser.getJSONFromUrl(url_game + appid_games.get(i));
                if (json != null) {
                    try {
                        // Getting Array of Contacts
                        // looping through All Contacts
                        GameCard game = new GameCard();
                        JSONObject jGame;
                        JSONObject jTMP;
                        jTMP = json.getJSONObject(appid_games.get(i));
                        if (jTMP.getBoolean("success")) {
                            JSONArray jAr = jTMP.getJSONObject("data").getJSONArray(TAG_GENRE);
                            jGame = jAr.getJSONObject(0);
                            game.setGameGenre(jGame.getString(TAG_DESCRIPTION));
                            game.setGameName(jTMP.getJSONObject("data").getString(TAG_NAME));
                            if (!jTMP.getJSONObject("data").getBoolean("is_free")) {
                                try {
                                    game.setGamePrice(jTMP.getJSONObject("data").getJSONObject("price_overview").getString("final_formatted"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    game.setGamePrice("N/A");
                                }
                            } else game.setGamePrice("Free");
                            gameArrayList.add(game);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}