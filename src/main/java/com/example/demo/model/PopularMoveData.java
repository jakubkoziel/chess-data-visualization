package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PopularMoveData {
    private String uci;
    private long white;
    private long draws;
    private long black;
    private long averageRating;

    public String getUci() {
        return uci;
    }

    public void setUci(String uci) {
        this.uci = uci;
    }

    public long getWhite() {
        return white;
    }

    public void setWhite(long white) {
        this.white = white;
    }

    public long getDraws() {
        return draws;
    }

    public void setDraws(long draws) {
        this.draws = draws;
    }

    public long getBlack() {
        return black;
    }

    public void setBlack(long black) {
        this.black = black;
    }

    public long getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(long averageRating) {
        this.averageRating = averageRating;
    }

    @Override
    public String toString() {
        return "PopularMoveData{" +
                "uci='" + uci + '\'' +
                ", white=" + white +
                ", draws=" + draws +
                ", black=" + black +
                ", averageRating=" + averageRating +
                '}';
    }
}
