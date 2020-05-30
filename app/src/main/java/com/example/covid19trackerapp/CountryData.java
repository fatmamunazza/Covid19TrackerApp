package com.example.covid19trackerapp;

public class CountryData {

   private String country;
   private String countryCases;
   private String countryTodayCases;
   private String countryDeaths;
   private String countryTodayDeath;
   private String countryRecovered;
   private String countryActive;
   private String countryCritical;
   private String countryTests;
   private String countryPopulation;
   private String countryFlag;

    public String getCountry(){
        return country;
    }

    public String getCountryActive() {
        return countryActive;
    }

    public String getCountryCases() {
        return countryCases;
    }

    public String getCountryCritical() {
        return countryCritical;
    }

    public String getCountryDeaths() {
        return countryDeaths;
    }

    public String getCountryFlag() {
        return countryFlag;
    }

    public String getCountryPopulation() {
        return countryPopulation;
    }

    public String getCountryRecovered() {
        return countryRecovered;
    }

    public String getCountryTests() {
        return countryTests;
    }

    public String getCountryTodayCases() {
        return countryTodayCases;
    }

    public String getCountryTodayDeath() {
        return countryTodayDeath;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCountryActive(String countryActive) {
        this.countryActive = countryActive;
    }

    public void setCountryCases(String countryCases) {
        this.countryCases = countryCases;
    }

    public void setCountryCritical(String countryCritical) {
        this.countryCritical = countryCritical;
    }

    public void setCountryDeaths(String countryDeaths) {
        this.countryDeaths = countryDeaths;
    }

    public void setCountryFlag(String countryFlag) {
        this.countryFlag = countryFlag;
    }

    public void setCountryPopulation(String countryPopulation) {
        this.countryPopulation = countryPopulation;
    }

    public void setCountryRecovered(String countryRecovered) {
        this.countryRecovered = countryRecovered;
    }

    public void setCountryTests(String countryTests) {
        this.countryTests = countryTests;
    }

    public void setCountryTodayCases(String countryTodayCases) {
        this.countryTodayCases = countryTodayCases;
    }

    public void setCountryTodayDeath(String countryTodayDeath) {
        this.countryTodayDeath = countryTodayDeath;
    }
}
