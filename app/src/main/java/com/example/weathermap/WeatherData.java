package com.example.weathermap;

import java.util.List;

public class WeatherData {
    private List<Weather> weather;
    private Main main;
    private String name;

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WeatherData(List<Weather> weather, Main main, String name) {
        this.weather = weather;
        this.main = main;
        this.name = name;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "weather=" + weather +
                ", main=" + main +
                ", name='" + name + '\'' +
                '}';
    }
}
