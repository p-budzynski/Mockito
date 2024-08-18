package pl.kurs.homework.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kurs.homework.service.DistanceSensorService;
import pl.kurs.homework.service.SpeedSensorService;

@ExtendWith(MockitoExtension.class)
public class MasterTrafficLightControllerTest {
    @Mock
    private SpeedSensorService speedSensorMock;
    @Mock
    private DistanceSensorService distanceSensorMock;
    @Mock
    private LightController lightController;
    @InjectMocks
    private MasterTrafficLightController masterTrafficLightController;

    @Test
    void shouldChangeLightToRedAtSpeedAbove40AndDistanceBelow50() {
        //given
        Mockito.when(speedSensorMock.getSpeed()).thenReturn(60.0);
        Mockito.when(distanceSensorMock.getDistance()).thenReturn(40.0);

        //when
        masterTrafficLightController.changeTrafficLight();

        //then
        Mockito.verify(lightController).turnRed();
    }

    @Test
    void shouldChangeLightToGreenAtSpeedAbove40AndDistanceAbove50() {
        //given
        Mockito.when(speedSensorMock.getSpeed()).thenReturn(70.0);
        Mockito.when(distanceSensorMock.getDistance()).thenReturn(100.0);

        //when
        masterTrafficLightController.changeTrafficLight();

        //then
        Mockito.verify(lightController).turnGreen();
    }

    @Test
    void shouldChangeLightToGreenAtSpeedBelow40AndDistanceBelow50() {
        //given
        Mockito.when(speedSensorMock.getSpeed()).thenReturn(35.0);
        Mockito.when(distanceSensorMock.getDistance()).thenReturn(45.0);

        //when
        masterTrafficLightController.changeTrafficLight();

        //then
        Mockito.verify(lightController).turnGreen();
    }

    @Test
    void shouldChangeLightToGreenAtSpeedBelow40AndDistanceAbove50() {
        //given
        Mockito.when(speedSensorMock.getSpeed()).thenReturn(35.0);
        Mockito.when(distanceSensorMock.getDistance()).thenReturn(150.0);

        //when
        masterTrafficLightController.changeTrafficLight();

        //then
        Mockito.verify(lightController).turnGreen();
    }
}
