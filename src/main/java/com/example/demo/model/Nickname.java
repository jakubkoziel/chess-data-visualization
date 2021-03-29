package com.example.demo.model;

public class Nickname {

    private String nickname;

    @Override
    public String toString() {
        return "Nickname{" +
                "nickname='" + nickname + '\'' +
                '}';
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}