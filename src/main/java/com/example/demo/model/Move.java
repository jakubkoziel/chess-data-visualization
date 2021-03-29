package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Move {

    private String move;

    public String getMove() {
        return move;
    }

    public void setMove(String move) {
        this.move = move;
    }

    public Move(String move) {
        this.move = move;
    }

    public Move(){
        this.move="";
    }
}
