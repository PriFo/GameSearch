package com.example.gamesearch.mvvm.repos;

import androidx.navigation.NavController;

public class GameRepo {
    private static volatile GameRepo instance;

    private String appid = "";
    private String genre = "";
    private String name = "";
    private Boolean home;

    public Boolean getHome() {
        return home;
    }

    public void setHome(Boolean home) {
        this.home = home;
    }

    private NavController controller;

    public NavController getController() {
        return controller;
    }

    public void setController(NavController controller) {
        this.controller = controller;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static GameRepo getInstance() {
        GameRepo localInstance = instance;
        if (localInstance == null){
            synchronized (GameRepo.class){
                localInstance = instance;
                if (localInstance == null){
                    instance = localInstance = new GameRepo();
                }

            }
        }
        return localInstance;
    }
}
