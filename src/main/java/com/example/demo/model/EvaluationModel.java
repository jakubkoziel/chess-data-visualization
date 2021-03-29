package com.example.demo.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class EvaluationModel {

    private long knodes;
    private long depth;
    private List<ComputerMoves> pvs;
    private boolean isError;

    @Override
    public String toString() {
        return "EvaluationModel{" +
                "knodes=" + knodes +
                ", depth=" + depth +
                ", pvs=" + pvs +
                ", isError=" + isError +
                '}';
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public long getKnodes() {
        return knodes;
    }

    public void setKnodes(long knodes) {
        this.knodes = knodes;
    }

    public long getDepth() {
        return depth;
    }

    public void setDepth(long depth) {
        this.depth = depth;
    }

    public List<ComputerMoves> getPvs() {
        return pvs;
    }

    public void setPvs(List<ComputerMoves> pvs) {
        this.pvs = pvs;
    }

}
