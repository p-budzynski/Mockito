package pl.kurs.homework.controller;

import pl.kurs.homework.service.DistanceSensorService;
import pl.kurs.homework.service.SpeedSensorService;

public class MasterTrafficLightController {
    private SpeedSensorService speedSensor;
    private DistanceSensorService distanceSensor;
    private LightController lightController;

    private MasterTrafficLightController(SpeedSensorService speedSensor, DistanceSensorService distanceSensor, LightController lightController) {
        this.speedSensor = speedSensor;
        this.distanceSensor = distanceSensor;
        this.lightController = lightController;
    }

    public void changeTrafficLight() {
        double currentSpeed = speedSensor.getSpeed();
        double currentDistance = distanceSensor.getDistance();
        if (currentSpeed > 40 && currentDistance < 50) {
            lightController.turnRed();
        } else {
            lightController.turnGreen();
        }
    }

}
