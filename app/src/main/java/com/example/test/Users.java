package com.example.test;

public class Users {
    private String nameUser;
    private int scoreUser;
    private int position;


    public Users(String nameUser, int scoreUser, int position) {
        this.nameUser = nameUser;
        this.scoreUser = scoreUser;
        this.position = position;
    }

    public Users() { }


    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getScoreUser() {
        return String.valueOf(scoreUser);
    }

    public void setScoreUser(int scoreUser) {
        this.scoreUser = scoreUser;
    }

    public String getPosition() {
        return String.valueOf(position);
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
