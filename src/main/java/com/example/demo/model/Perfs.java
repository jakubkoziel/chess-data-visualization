package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Perfs {


    private Rating bullet;
    private Rating blitz;
    private Rating rapid;
    private Rating classical;


    @Override
    public String toString() {
        return "Perfs{" +
                "bullet=" + bullet +
                ", blitz=" + blitz +
                ", rapid=" + rapid +
                ", classical=" + classical +
                '}';
    }

    public Rating getBullet() {
        return bullet;
    }

    public void setBullet(Rating bullet) {
        this.bullet = bullet;
    }

    public Rating getBlitz() {
        return blitz;
    }

    public void setBlitz(Rating blitz) {
        this.blitz = blitz;
    }

    public Rating getRapid() {
        return rapid;
    }

    public void setRapid(Rating rapid) {
        this.rapid = rapid;
    }

    public Rating getClassical() {
        return classical;
    }

    public void setClassical(Rating classical) {
        this.classical = classical;
    }
}
