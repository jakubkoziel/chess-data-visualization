package com.example.demo.model;

import java.util.LinkedList;
import java.util.List;

public class TopPlayersChart {

    private LinkedList<String> nicknames;
    private LinkedList<Integer> points;
    private LinkedList<Integer> added;
    private LinkedList<Integer> subtracted;



    @Override
    public String toString() {
        return "TopPlayersChart{" +
                "nicknames=" + nicknames +
                ", points=" + points +
                ", added=" + added +
                ", subtracted=" + subtracted +
                '}';
    }

    public LinkedList<String> getNicknames() {
        return nicknames;
    }

    public void setNicknames(LinkedList<String> nicknames) {
        this.nicknames = nicknames;
    }

    public LinkedList<Integer> getPoints() {
        return points;
    }

    public void setPoints(LinkedList<Integer> points) {
        this.points = points;
    }

    public LinkedList<Integer> getAdded() {
        return added;
    }

    public void setAdded(LinkedList<Integer> added) {
        this.added = added;
    }

    public LinkedList<Integer> getSubtracted() {
        return subtracted;
    }

    public void setSubtracted(LinkedList<Integer> subtracted) {
        this.subtracted = subtracted;
    }
}
