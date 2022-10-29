package com.example.gamesearch.mvvm.viewmodels;
import androidx.lifecycle.ViewModel;


import com.example.gamesearch.JSONParser;
import com.example.gamesearch.mvvm.repos.GameRepo;

import org.json.JSONException;
import org.json.JSONObject;

public class OpenGameVM extends ViewModel {

    // JSON Node names
    private static final String TAG_DEVELOPER = "developers";
    private static final String TAG_PUBLISHER = "publishers";
    private static final String TAG_DESCRIPTION = "detailed_description";
    private final String appid;
    private String description = "";
    private String developer = "";
    private String publisher = "";
    private String SysReq = "";
    private final JSONParser jParser = new JSONParser();
    private final GameRepo repo = GameRepo.getInstance();

    public OpenGameVM(){
        // call your Rest API in init method
        appid = repo.getAppid();
        init();
    }

    public void init() {
        try {
            getGame();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getDescription() {
        return description;
    }

    public String getDeveloper() {
        return developer;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getSysReq() {
        return SysReq;
    }

    public GameRepo getRepo() {
        return repo;
    }

    public void getGame() throws JSONException {

        // Creating JSON Parser instance

// getting JSON string from URL
        System.out.println("Тут не пиздец?");
        String url_game = "https://store.steampowered.com/api/appdetails?appids=";
        JSONObject json = jParser.getJSONFromUrl(url_game + appid);
        if (json != null) {
            try {
                String key = appid;
                // Getting Array of Contacts
                // looping through All Contacts
                JSONObject jTMP;
                jTMP = json.getJSONObject(key);
                System.out.println(json.getString(key));
                description = jTMP.getJSONObject("data").getString(TAG_DESCRIPTION);
                developer = jTMP.getJSONObject("data").getString(TAG_DEVELOPER);
                publisher = jTMP.getJSONObject("data").getString(TAG_PUBLISHER);
                SysReq = jTMP.getJSONObject("data").getJSONObject("pc_requirements").getString("minimum");
                try {
                    SysReq += jTMP.getJSONObject("data").getJSONObject("pc_requirements").getString("recommended");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}