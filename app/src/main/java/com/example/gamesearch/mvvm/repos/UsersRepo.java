package com.example.gamesearch.mvvm.repos;

public class UsersRepo {
    String login;
    String email;

    public UsersRepo() {
        login = "eboba";
        email = "eboba";
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
