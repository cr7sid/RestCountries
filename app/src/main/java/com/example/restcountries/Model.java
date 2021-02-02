package com.example.restcountries;

import java.util.ArrayList;

public class Model {

    String name;
    String capital;
    String flag;


    public String getCapital() {

        return capital;

    }

    public void setCapital(String capital) {

        this.capital = capital;

    }

    public String getFlag() {

        return flag;

    }

    public void setFlag(String flag) {

        this.flag = flag;

    }

    public String getRegion() {

        return region;

    }

    public void setRegion(String region) {

        this.region = region;

    }

    public String getSubRegion() {

        return subRegion;

    }

    public void setSubRegion(String subRegion) {

        this.subRegion = subRegion;

    }

    public String getPopulation() {

        return population;

    }

    public void setPopulation(String population) {

        this.population = population;

    }

    public ArrayList<String> getBorders() {

        return borders;

    }

    public void setBorders(ArrayList<String> borders) {

        this.borders = borders;

    }

    public ArrayList<String> getLanguages() {

        return languages;

    }

    public void setLanguages(ArrayList<String> languages) {

        this.languages = languages;

    }

    String region;
    String subRegion;
    String population;
    ArrayList<String> borders;
    ArrayList<String> languages;

    public Model(String name, String capital, String flag, String region, String subRegion, String population, ArrayList<String> borders, ArrayList<String> languages) {

        super();
        this.name = name;
        this.capital = capital;
        this.flag = flag;
        this.region = region;
        this.subRegion = subRegion;
        this.population = population;
        this.borders = borders;
        this.languages = languages;

    }

    public String getCountryName() {

        return name;

    }
    public void setCountryName(String name) {

        this.name = name;

    }

}
