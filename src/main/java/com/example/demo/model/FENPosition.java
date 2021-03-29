package com.example.demo.model;

public class FENPosition {

    private String FENPosition = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

    public String getFENPosition() {
        return FENPosition;
    }

    public void setFENPosition(String FENPosition) {
        this.FENPosition = FENPosition;
    }

    @Override
    public String toString() {
        return FENPosition;
    }
}
