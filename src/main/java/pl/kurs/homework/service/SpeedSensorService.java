package pl.kurs.homework.service;

import pl.kurs.homework.model.SpeedSensor;

public class SpeedSensorService implements SpeedSensor {
    @Override
    public double getSpeed() {
        return 60;
    }
}
