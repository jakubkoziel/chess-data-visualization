package com.example.demo.service;


import com.example.demo.model.PlayerHistory;
import com.example.demo.model.PlayerHistoryChart;
import com.example.demo.model.PositionData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PositionDataService {

    public PositionData getDataPosition(String fenPosition){

        RestTemplate restTemplate = new RestTemplate();
        PositionData positionData = restTemplate.getForObject(
                "https://explorer.lichess.ovh/lichess?variant=standard&speeds[]=blitz&speeds[]=rapid&speeds[]=classical&ratings[]=2200&ratings[]=2500&ratings[]=2000&ratings[]=1800&fen=" +
                        fenPosition + "&moves=5&topGames=0&recentGames=0", PositionData.class);

        return positionData;
    }
}
