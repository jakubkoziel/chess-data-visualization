package com.example.demo.model;

public class ComputerMoves {
    private String moves;
    private long cp;

    public String getMoves() {
        return moves;
    }

    public void setMoves(String moves) {
        this.moves = moves;
    }

    public long getCp() {
        return cp;
    }

    public void setCp(long cp) {
        this.cp = cp;
    }

    @Override
    public String toString() {
        return "ComputerMoves{" +
                "moves='" + moves + '\'' +
                ", cp=" + cp +
                '}';
    }
}
