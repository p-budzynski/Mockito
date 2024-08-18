package pl.kurs.homework.controller;

import pl.kurs.homework.model.TrafficLightColor;

public class LightController {
    private TrafficLightColor lightColor;

    public void turnRed() {
        lightColor = TrafficLightColor.RED;
    }

    public void turnGreen() {
        lightColor = TrafficLightColor.GREEN;
    }

}
