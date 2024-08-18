package pl.kurs.homework.controller;

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
public class MasterTrafficLightControllerTest2 {
    @Mock
    private SpeedSensorService speedSensorMock;
    @Mock
    private DistanceSensorService distanceSensorMock;
    @Mock
    private LightController lightController;
    @InjectMocks
    private MasterTrafficLightController masterTrafficLightController;

    @ParameterizedTest
    @MethodSource("provideArgumentsForSpeedAbove40AndDistanceBelow50")
    void shouldChangeLightToRedAtSpeedAbove40AndDistanceBelow50(double speed, double distance) {
        //given
        Mockito.when(speedSensorMock.getSpeed()).thenReturn(speed);
        Mockito.when(distanceSensorMock.getDistance()).thenReturn(distance);

        //when
        masterTrafficLightController.changeTrafficLight();

        //then
        Mockito.verify(lightController).turnRed();
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForSpeedAbove40AndDistanceAbove50")
    void shouldChangeLightToGreenAtSpeedAbove40AndDistanceAbove50() {
        //given
        Mockito.when(speedSensorMock.getSpeed()).thenReturn(70.0);
        Mockito.when(distanceSensorMock.getDistance()).thenReturn(100.0);

        //when
        masterTrafficLightController.changeTrafficLight();

        //then
        Mockito.verify(lightController).turnGreen();
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForSpeedBelow40AndDistanceBelow50")
    void shouldChangeLightToGreenAtSpeedBelow40AndDistanceBelow50() {
        //given
        Mockito.when(speedSensorMock.getSpeed()).thenReturn(35.0);
        Mockito.when(distanceSensorMock.getDistance()).thenReturn(45.0);

        //when
        masterTrafficLightController.changeTrafficLight();

        //then
        Mockito.verify(lightController).turnGreen();
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForSpeedBelow40AndDistanceAbove50")
    void shouldChangeLightToGreenAtSpeedBelow40AndDistanceAbove50() {
        //given
        Mockito.when(speedSensorMock.getSpeed()).thenReturn(35.0);
        Mockito.when(distanceSensorMock.getDistance()).thenReturn(150.0);

        //when
        masterTrafficLightController.changeTrafficLight();

        //then
        Mockito.verify(lightController).turnGreen();
    }

    private static Stream<Arguments> provideArgumentsForSpeedAbove40AndDistanceBelow50() {
        return Stream.of(
                Arguments.of(45, 30),
                Arguments.of(60, 15),
                Arguments.of(100, 47),
                Arguments.of(120, 49),
                Arguments.of(90, 5)
        );
    }

    private static Stream<Arguments> provideArgumentsForSpeedAbove40AndDistanceAbove50() {
        return Stream.of(
                Arguments.of(65, 100),
                Arguments.of(72, 115),
                Arguments.of(51, 200),
                Arguments.of(120, 300),
                Arguments.of(99, 190)
        );
    }

    private static Stream<Arguments> provideArgumentsForSpeedBelow40AndDistanceBelow50() {
        return Stream.of(
                Arguments.of(25, 48),
                Arguments.of(20, 15),
                Arguments.of(38, 33),
                Arguments.of(10, 44),
                Arguments.of(5, 17)
        );
    }

    private static Stream<Arguments> provideArgumentsForSpeedBelow40AndDistanceAbove50() {
        return Stream.of(
                Arguments.of(10, 100),
                Arguments.of(20, 200),
                Arguments.of(30, 300),
                Arguments.of(35, 170),
                Arguments.of(29, 900)
        );
    }
}
