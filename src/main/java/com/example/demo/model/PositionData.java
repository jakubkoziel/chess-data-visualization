package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PositionData {

    private long white;
    private long draws;
    private long black;
    private List<PopularMoveData> moves;
    private long averageRating;

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

    public List<PopularMoveData> getMoves() {
        return moves;
    }

    public void setMoves(List<PopularMoveData> moves) {
        this.moves = moves;
    }

    public long getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(long averageRating) {
        this.averageRating = averageRating;
    }

    @Override
    public String toString() {
        return "PositionData{" +
                "white=" + white +
                ", draws=" + draws +
                ", black=" + black +
                ", moves=" + moves +
                ", averageRating=" + averageRating +
                '}';
    }
}
