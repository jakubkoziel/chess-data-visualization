package com.example.demo.service;

import com.example.demo.model.EvaluationModel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EvaluationService {

    public EvaluationModel getEvaluations(String fenPosition){

        String[] fenPositionArray = fenPosition.split(" ");
        fenPositionArray[3] = "-";
        fenPosition = String.join(" ", fenPositionArray);

        RestTemplate restTemplate = new RestTemplate();
        EvaluationModel evaluationModel;
        try {
            evaluationModel = restTemplate.getForObject(
                    "https://lichess.org/api/cloud-eval?multiPv=3&fen=" + fenPosition,
                    EvaluationModel.class);
        } catch (Exception e) {
            evaluationModel = new EvaluationModel();
            evaluationModel.setError(true);
        }

        return evaluationModel;
    }
}