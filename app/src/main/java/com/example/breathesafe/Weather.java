package com.example.breathesafe;

public class Weather {

    private String date;//时间
    private String cityname;//城市名
    private String airquality; //pm2.5
    private String humidity;
    private String temperature;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }
    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getAirquality() {
        return airquality;
    }

    public void setAirquality(String airquality) {
        this.airquality = airquality;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = airquality;
    }
    @Override
    public String toString() {
        return "WeatherInfo [date=" + date + ", cityname=" + cityname
                + ", humidity" + humidity + ", temperature=" + temperature
                + ", airquality=" + airquality + "]";
    }



}
