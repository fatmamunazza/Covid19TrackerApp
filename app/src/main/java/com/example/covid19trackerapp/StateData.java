package com.example.covid19trackerapp;

import java.util.ArrayList;

public class StateData {
    private String state,death,active,recovered,total;
    private ArrayList<String> cities=new ArrayList<String>();
    private ArrayList<Integer> deathCases=new ArrayList<Integer>();
    private ArrayList<Integer> activeCases=new ArrayList<Integer>();
    private ArrayList<Integer> recoveredCases=new ArrayList<Integer>();
    private ArrayList<Integer> totalCases=new ArrayList<Integer>();

    public ArrayList<Integer> getActiveCases() {
        return activeCases;
    }

    public ArrayList<Integer> getDeathCases() {
        return deathCases;
    }

    public ArrayList<String> getCities() {
        return cities;
    }

    public ArrayList<Integer> getRecoveredCases() {
        return recoveredCases;
    }

    public ArrayList<Integer> getTotalCases() {
        return totalCases;
    }

    public String getState() {
        return state;
    }

    public void setActiveCases(ArrayList<Integer> activeCases) {
        this.activeCases = activeCases;
    }

    public void setDeathCases(ArrayList<Integer> deathCases) {
        this.deathCases = deathCases;
    }

    public void setRecoveredCases(ArrayList<Integer> recoveredCases) {
        this.recoveredCases = recoveredCases;
    }

    public void setTotalCases(ArrayList<Integer> totalCases) {
        this.totalCases = totalCases;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCities(ArrayList<String> cities) {
        this.cities = cities;
    }

    public String getActive() {
        int activeNo=0;
        for(int i=0;i<activeCases.size();i++){
            activeNo=activeNo+activeCases.get(i);
        }
        active=String.valueOf(activeNo);
        return active;
    }

    public String getDeath() {
        int deathNo=0;
        for(int i=0;i<deathCases.size();i++){
            deathNo=deathNo+deathCases.get(i);
        }
        death=String.valueOf(deathNo);
        return death;
    }

    public String getRecovered() {
        int recoveredNo=0;
        for(int i=0;i<recoveredCases.size();i++){
            recoveredNo=recoveredNo+recoveredCases.get(i);
        }
        recovered=String.valueOf(recoveredNo);
        return recovered;
    }

    public String getTotal() {
        int totalNo=0;
        for(int i=0;i<totalCases.size();i++){
            totalNo=totalNo+totalCases.get(i);
        }
        total=String.valueOf(totalNo);
        return total;
    }
}
