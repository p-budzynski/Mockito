package pl.kurs.homework.service;

import pl.kurs.homework.model.DistanceSensor;

public class DistanceSensorService implements DistanceSensor {
    @Override
    public double getDistance() {
        return 20;
    }
}
