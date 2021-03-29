package com.example.demo.service;



import com.example.demo.model.PlayerInfo;
import com.example.demo.model.Rating;
import com.example.demo.model.TopPlayers;
import com.example.demo.model.TopPlayersChart;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;

@Service
public class TopPlayersService {


    public List<TopPlayersChart> getTopPlayers(){


        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/vnd.lichess.v3+json");

        //Create a new HttpEntity
        HttpEntity entity = new HttpEntity(headers);


        //Execute the method writing your HttpEntity to the request

        ResponseEntity<TopPlayers> response = restTemplate.exchange("https://lichess.org/player", HttpMethod.GET, entity, TopPlayers.class);
        //log.info(response.getBody().toString());

        TopPlayers topPlayers = response.getBody();

        LinkedList<TopPlayersChart> listOfTopPlayersChart = new LinkedList<>();


        listOfTopPlayersChart.add(createChartFirendlyHistoryObjects(topPlayers.getBullet(), "bullet"));
        listOfTopPlayersChart.add(createChartFirendlyHistoryObjects(topPlayers.getBlitz(), "blitz"));
        listOfTopPlayersChart.add(createChartFirendlyHistoryObjects(topPlayers.getRapid(), "rapid"));
        listOfTopPlayersChart.add(createChartFirendlyHistoryObjects(topPlayers.getClassical(), "classical"));


        return listOfTopPlayersChart;
    }

    public TopPlayersChart createChartFirendlyHistoryObjects(List<PlayerInfo> gameStats, String type){


        TopPlayersChart typeOfGame = new TopPlayersChart();

        typeOfGame.setNicknames(new LinkedList<>());
        typeOfGame.setAdded(new LinkedList<>());
        typeOfGame.setPoints(new LinkedList<>());
        typeOfGame.setSubtracted(new LinkedList<>());


        for(PlayerInfo player : gameStats){

            Rating playerRating;

            switch (type){
                case "bullet":
                    playerRating = player.getPerfs().getBullet();
                    break;
                case "blitz":
                    playerRating = player.getPerfs().getBlitz();
                    break;
                case "rapid":
                    playerRating = player.getPerfs().getRapid();
                    break;
                case "classical":
                    playerRating = player.getPerfs().getClassical();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + type);
            }


            typeOfGame.getNicknames().add(player.getUsername());

            typeOfGame.getPoints().add(playerRating.getRating());

            if (playerRating.getProgress() >= 0){
                typeOfGame.getAdded().add(playerRating.getProgress());
                typeOfGame.getSubtracted().add(0);
            } else {
                typeOfGame.getSubtracted().add(playerRating.getProgress());
                typeOfGame.getAdded().add(0);
            }
        }

        return typeOfGame;

    }

}
