package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TopPlayers {

    public List<PlayerInfo> bullet;
    public List<PlayerInfo> blitz;
    public List<PlayerInfo> rapid;
    public List<PlayerInfo> classical;

    @Override
    public String toString() {
        return "TopPlayers{" +
                "bullet=" + bullet +
                ", blitz=" + blitz +
                ", rapid=" + rapid +
                ", classical=" + classical +
                '}';
    }

    public List<PlayerInfo> getBullet() {
        return bullet;
    }

    public void setBullet(List<PlayerInfo> bullet) {
        this.bullet = bullet;
    }

    public List<PlayerInfo> getBlitz() {
        return blitz;
    }

    public void setBlitz(List<PlayerInfo> blitz) {
        this.blitz = blitz;
    }

    public List<PlayerInfo> getRapid() {
        return rapid;
    }

    public void setRapid(List<PlayerInfo> rapid) {
        this.rapid = rapid;
    }

    public List<PlayerInfo> getClassical() {
        return classical;
    }

    public void setClassical(List<PlayerInfo> classical) {
        this.classical = classical;
    }

}
