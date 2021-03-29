package com.example.demo.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EvaluationData {
    private List<String> first_moves;
    private List<String> cp;
    private List<String> move_sequence;
    private String depth;
    private String white_height;
    private String black_height;

    public EvaluationData(EvaluationModel evaluationModel) {
        if (evaluationModel.isError()) {
            depth = "";
            first_moves = new ArrayList<>(Arrays.asList("", "", ""));
            cp = new ArrayList<>(Arrays.asList("", "", ""));
            move_sequence = new ArrayList<>(Arrays.asList("", "", ""));
            white_height = "0px";
            black_height = "0px";
        } else {
            depth = String.valueOf(evaluationModel.getDepth());
            first_moves = new ArrayList<>();
            cp = new ArrayList<>();
            move_sequence = new ArrayList<>();
            for (ComputerMoves computerMoves : evaluationModel.getPvs()) {
                first_moves.add(computerMoves.getMoves().substring(0, 4));
                cp.add(String.valueOf(computerMoves.getCp()));
                move_sequence.add(computerMoves.getMoves().substring(0, 34));
            }
            for (int i = first_moves.size(); i < 3; i++) {
                first_moves.add("");
                cp.add("");
                move_sequence.add("");
            }
            double white_h = Math.min(835, Math.max(0, ((Double.parseDouble(cp.get(0)) + 400)/800) * 835));
            double black_h = 835 - white_h;
            white_height = white_h + "px";
            black_height = black_h + "px";
        }
    }


    public List<String> getFirst_moves() {
        return first_moves;
    }

    public void setFirst_moves(List<String> first_moves) {
        this.first_moves = first_moves;
    }

    public List<String> getCp() {
        return cp;
    }

    public void setCp(List<String> cp) {
        this.cp = cp;
    }

    public List<String> getMove_sequence() {
        return move_sequence;
    }

    public void setMove_sequence(List<String> move_sequence) {
        this.move_sequence = move_sequence;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public String getWhite_height() {
        return white_height;
    }

    public void setWhite_height(String white_height) {
        this.white_height = white_height;
    }

    public String getBlack_height() {
        return black_height;
    }

    public void setBlack_height(String black_height) {
        this.black_height = black_height;
    }

    @Override
    public String toString() {
        return "EvaluationData{" +
                "first_moves=" + first_moves +
                ", cp=" + cp +
                ", move_sequence=" + move_sequence +
                ", depth='" + depth + '\'' +
                ", white_height='" + white_height + '\'' +
                ", black_height='" + black_height + '\'' +
                '}';
    }
}
