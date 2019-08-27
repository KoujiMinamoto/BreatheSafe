package com.example.breathesafe;

public class Geo {

    private String sub;
    private String geo;
    public Geo(String personName, String personAddress) {
        super();
        this.sub = personName;
        this.geo = personAddress;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String date) {
        this.sub = date;
    }

    public String getGeo() {
        return geo;
    }

    public void setGeo(String cityname) {
        this.geo = cityname;
    }
}
