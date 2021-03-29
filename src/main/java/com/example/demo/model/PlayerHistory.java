package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerHistory {


    private String name;

    private Integer[][] points;


    @Override
    public String toString() {
        return "PlayerHistory{" +
                "name='" + name + '\'' +
                ", points=" + Arrays.deepToString(points) +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer[][] getPoints() {
        return points;
    }

    public void setPoints(Integer[][] points) {
        this.points = points;
    }
}
