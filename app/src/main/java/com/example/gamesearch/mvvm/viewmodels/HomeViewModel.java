package com.example.gamesearch.mvvm.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.gamesearch.JSONParser;
import com.example.gamesearch.mvvm.models.GameCard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class HomeViewModel extends ViewModel {

    private static String url_pop = "https://steamspy.com/api.php?request=top100in2weeks";
    private static String url_rec = "https://steamspy.com/api.php?request=genre&genre=Action";
    private static String url_game = "https://store.steampowered.com/api/appdetails?appids=";

    // JSON Node names
    private static final String TAG_DEVELOPER = "developer";
    private static final String TAG_ID = "appid";
    private static final String TAG_NAME = "name";
    private static final String TAG_PUBLISHER = "publisher";
    private static final String TAG_DESCRIPTION = "description";
    private static final String TAG_PRICE = "price";
    private static final String TAG_GENRE = "genre";
    private final JSONParser jParser = new JSONParser();

    // contacts JSONArray
    JSONArray games = null;

    MutableLiveData<ArrayList<GameCard>> gameLiveData;
    ArrayList<GameCard> gameArrayList;
    GameCard tmp_gmCrd;

    public GameCard getTmp_gmCrd() {
        return tmp_gmCrd;
    }

    public HomeViewModel() throws JSONException {
        gameLiveData = new MutableLiveData<>();
        tmp_gmCrd = new GameCard();
        // call your Rest API in init method
        init();
    }

    public MutableLiveData<ArrayList<GameCard>> getUserMutableLiveData(){
        return gameLiveData;
    }

    public void init() throws JSONException {
        recomendedList();
        getPopGame();
        gameLiveData.setValue(gameArrayList);
    }

    public void getPopGame() throws JSONException {

        // Creating JSON Parser instance

// getting JSON string from URL
        System.out.println("Тут не пиздец?");
        JSONObject json = jParser.getJSONFromUrl(url_pop);
        if (json != null) {
            try {
                String key;
                // Getting Array of Contacts
                // looping through All Contacts
                JSONObject jTMP;
                Iterator<String> i = json.keys();
                key = i.next();
                tmp_gmCrd.setGameName(json.getJSONObject(key).getString(TAG_NAME));
                if (json.getJSONObject(key).getString(TAG_PRICE).equals("0"))
                    tmp_gmCrd.setGamePrice("Free");
                else tmp_gmCrd.setGamePrice(json.getJSONObject(key).getString(TAG_PRICE));
                jTMP = jParser.getJSONFromUrl(url_game + key);
                i = jTMP.keys();
                key = i.next();
                JSONArray jAr = jTMP.getJSONObject(key).getJSONObject("data").getJSONArray("genres");
                System.out.println(jTMP.getJSONObject(key).getJSONObject("data").getString("genres"));
                jTMP = jAr.getJSONObject(0);
                tmp_gmCrd.setGameGenre(jTMP.getString(TAG_DESCRIPTION));
                System.out.println(jTMP.getString(TAG_DESCRIPTION));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void recomendedList(){

        GameCard game = new GameCard();
        System.out.println("Тут пиздец?");
        JSONObject json = jParser.getJSONFromUrl(url_rec);
        try {
            gameArrayList = new ArrayList<>();
            JSONObject jTMP;
            String key;
            int k = 0;
            for (Iterator<String> i = json.keys(); i.hasNext() && k < 50; k++) {
                key = i.next();
                jTMP = jParser.getJSONFromUrl(url_game + key);
                if (jTMP.getString(TAG_GENRE).equals("Action")) {
                    game.setGameName(jTMP.getJSONObject(key).getString(TAG_NAME));
                    JSONArray jAr = jTMP.getJSONObject(key).getJSONObject("data").getJSONArray("genres");
                    jTMP = jAr.getJSONObject(0);
                    game.setGameGenre(jTMP.getJSONObject(key).getString(TAG_DESCRIPTION));
                    game.setGamePrice(jTMP.getString("price_in_cents_with_discount"));
                    game = new GameCard();
                    gameArrayList.add(game);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}