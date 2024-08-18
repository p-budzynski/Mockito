package pl.kurs.controller;

import pl.kurs.service.SpeedSensor;

public class OnBoardComputerController {
    private RoofController roofController;
    private SpeedSensor speedSensor;

    public OnBoardComputerController(RoofController roofController, SpeedSensor speedSensor) {
        this.roofController = roofController;
        this.speedSensor = speedSensor;
    }

    public void openRoof() {
        double speed = speedSensor.getSpeed();
        if (speed <= 30) {
            roofController.openRoof();
        } else {
            throw new IllegalStateException("Jedziesz za szybko, żeby otworzyć dach!");
        }
    }

    public void closeRoof() {
        double speed = speedSensor.getSpeed();
        if (speed <= 30) {
            roofController.closeRoof();
        } else {
            throw new IllegalStateException("Jedziesz za szybko, żeby zamknąć dach!");
        }
    }
}
