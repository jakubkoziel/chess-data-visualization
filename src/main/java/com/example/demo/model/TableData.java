package com.example.demo.model;

public class TableData {
    private String uci;
    private String white;
    private String draws;
    private String black;
    private String totalGames;

    public TableData(String uci, long white, long draws, long black) {
        this.uci = uci;
        double w = (double)white;
        double d = (double)draws;
        double b = (double)black;
        double totalGames = w + d + b;
        if (totalGames == 0) {
            this.totalGames = "";
            this.white = "";
            this.draws = "";
            this.black = "";
        } else {
            this.totalGames = String.valueOf((int)totalGames);
            double wh = (w * 100) / totalGames;
            this.white = String.format("%.1f", wh) + "%";
            double bl = (b * 100) / totalGames;
            this.black = String.format("%.1f", bl) + "%";
            double dr = (d * 100) / totalGames;
            this.draws = String.format("%.1f", dr) + "%";
        }
    }

    public String getUci() {
        return uci;
    }

    public void setUci(String uci) {
        this.uci = uci;
    }

    public String getWhite() {
        return white;
    }

    public void setWhite(String white) {
        this.white = white;
    }

    public String getDraws() {
        return draws;
    }

    public void setDraws(String draws) {
        this.draws = draws;
    }

    public String getBlack() {
        return black;
    }

    public void setBlack(String black) {
        this.black = black;
    }

    public String getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(String totalGames) {
        this.totalGames = totalGames;
    }
}