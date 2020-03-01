package com.alphabet;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDateTime;

public class Player {
    private final IntegerProperty ranking = new SimpleIntegerProperty(this, "ranking");
    private final StringProperty userName = new SimpleStringProperty(this, "userName");
    private final IntegerProperty score = new SimpleIntegerProperty(this, "score");

    //constructors
    public Player(String userName) {
        if (userName==null && userName.isEmpty())
            setUserName(new StringBuilder("Guest").append(LocalDateTime.now().toString()).toString());
        else
            setUserName(userName);
    }
    public Player(int ranking, String userName, int score) {
        this.ranking.set(ranking);
        this.userName.set(userName);
        this.score.set(score);
    }

    //getters
    public int getRanking() { return ranking.get(); }
    public String getUserName() { return userName.get(); }
    public int getScore() { return score.get(); }
    //setters
    public void setRanking(int ranking) {
        this.ranking.set(ranking);
    }
    public void setUserName(String userName) {
        this.userName.setValue(userName);
    }
    public void setScore(int score) {
        this.score.set(score);
    }

}
