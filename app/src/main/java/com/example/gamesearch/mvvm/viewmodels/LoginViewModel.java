package com.example.gamesearch.mvvm.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.gamesearch.mvvm.repos.LoginRepo;
import com.google.firebase.auth.FirebaseUser;

public class LoginViewModel extends AndroidViewModel {

    private LoginRepo repository;
    private MutableLiveData<FirebaseUser> userData;
    private MutableLiveData<Boolean> loggedStatus;

    public MutableLiveData<FirebaseUser> getUserData() {
        return userData;
    }

    public MutableLiveData<Boolean> getLoggedStatus() {
        return loggedStatus;
    }

    public LoginViewModel(@NonNull  Application application) {
        super(application);
        repository = new LoginRepo(application);
        userData = repository.getFirebaseUserMutableLiveData();
        loggedStatus = repository.getUserLoggedMutableLiveData();
    }

    public void register(String email , String pass){
        repository.register(email, pass);
    }
    public void signIn(String email , String pass){
        repository.login(email, pass);
    }
    public void signOut(){
        repository.signOut();
    }

}