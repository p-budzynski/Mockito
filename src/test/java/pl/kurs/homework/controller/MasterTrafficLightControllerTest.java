package pl.kurs.homework.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kurs.homework.service.DistanceSensorService;
import pl.kurs.homework.service.SpeedSensorService;

import java.util.stream.Stream;

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

    @ParameterizedTest
    @MethodSource("provideArgumentsForChangeLightToGreen")
    void shouldChangeLightToGreen(double speed, double distance) {
        //given
        Mockito.when(speedSensorMock.getSpeed()).thenReturn(speed);
        Mockito.when(distanceSensorMock.getDistance()).thenReturn(distance);

        //when
        masterTrafficLightController.changeTrafficLight();

        //then
        Mockito.verify(lightController).turnGreen();
    }

    private static Stream<Arguments> provideArgumentsForChangeLightToGreen() {
        return Stream.of(
                Arguments.of(65, 100),
                Arguments.of(25, 48),
                Arguments.of(23, 234)
        );
    }

}