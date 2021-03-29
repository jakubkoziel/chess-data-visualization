package com.example.demo.model;

import java.util.LinkedHashMap;
import java.util.List;

public class PlayerHistoryChart {

    private String name;

    List<LinkedHashMap<String, String>> data;

    @Override
    public String toString() {
        return "PlayerHistoryChart{" +
                "name='" + name + '\'' +
                ", data=" + data +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LinkedHashMap<String, String>> getData() {
        return data;
    }

    public void setData(List<LinkedHashMap<String, String>> data) {
        this.data = data;
    }

    public PlayerHistoryChart(String name, List<LinkedHashMap<String, String>> data) {
        this.name = name;
        this.data = data;
    }
}
