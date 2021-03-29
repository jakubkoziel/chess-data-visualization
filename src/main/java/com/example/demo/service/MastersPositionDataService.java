package com.example.demo.service;

import com.example.demo.model.PositionData;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MastersPositionDataService {

    public PositionData getDataPosition(String fenPosition){

        RestTemplate restTemplate = new RestTemplate();
        PositionData positionData = restTemplate.getForObject(
                "https://explorer.lichess.ovh/master?fen=" +
                        fenPosition + "&moves=5&topGames=0", PositionData.class);

        return positionData;
    }
}
