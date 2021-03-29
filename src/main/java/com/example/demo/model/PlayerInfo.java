package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerInfo {
    private String id;
    private String username;
    private Perfs perfs;
    private String title;
    private boolean online;
    private boolean patrion;


    @Override
    public String toString() {
        return "PlayerInfo{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", perfs=" + perfs +
                ", title='" + title + '\'' +
                ", online=" + online +
                ", patrion=" + patrion +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Perfs getPerfs() {
        return perfs;
    }

    public void setPerfs(Perfs perfs) {
        this.perfs = perfs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean isPatrion() {
        return patrion;
    }

    public void setPatrion(boolean patrion) {
        this.patrion = patrion;
    }

}
