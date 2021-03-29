package com.example.demo.service;

import com.example.demo.model.PlayerHistory;
import com.example.demo.model.PlayerHistoryChart;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;


import java.io.IOException;
import java.util.*;

@Service
public class ChartsService {


    public List<PlayerHistoryChart> getDataForRatingHistory(String nickname) throws IOException {

        RestTemplate restTemplate = new RestTemplate();
        List<PlayerHistoryChart> historyCharts = new ArrayList<>();

        try {
            //previous solution, out of date due to changes in lichess api
            /*ResponseEntity<PlayerHistory[]> responseEntity = restTemplate.getForEntity("https://lichess.org/api/user/" + nickname + "/rating-history", PlayerHistory[].class);
            PlayerHistory[] histories = responseEntity.getBody();*/


            String response = restTemplate.getForObject("https://lichess.org/api/user/" + nickname + "/rating-history", String.class);
            response = response.substring( 1, response.length() - 1 ).replace("\\","");


            List<PlayerHistory> histories  = Arrays.asList(new ObjectMapper().readValue(response, PlayerHistory[].class));


            for (PlayerHistory history : histories) {
                ArrayList<LinkedHashMap<String, String>> datesList = new ArrayList<>();
                for (Integer[] table : history.getPoints()) {
                    LinkedHashMap<String, String> map = new LinkedHashMap();
                    map.put("x", table[0] + "-" + (table[1] + 1) + "-" + table[2]);
                    map.put("y", String.valueOf(table[3]));
                    datesList.add(map);
                }

                historyCharts.add(new PlayerHistoryChart(history.getName(), datesList));
            }


        } catch (HttpStatusCodeException ex) {
            throw new IOException();
        }


        return historyCharts;

    }


}






