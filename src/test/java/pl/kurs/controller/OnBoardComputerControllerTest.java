package pl.kurs.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.kurs.service.SpeedSensor;

public class OnBoardComputerControllerTest {
    @Test
    void shouldOpenRoofWhenSpeedIsBelow30() {
        //given
        RoofController roofControllerMock = Mockito.mock(RoofController.class); // utworzenie mocka typu RoofController
        SpeedSensor speedSensorMock = Mockito.mock(SpeedSensor.class);
        OnBoardComputerController onBoardComputerController = new OnBoardComputerController(roofControllerMock, speedSensorMock);
        Mockito.when(speedSensorMock.getSpeed()).thenReturn(20.0); // powiedzenie mockowi, ze ma odpowiadac wartoscia 20, na kazde wywolanie getSpeed()

        //when
        onBoardComputerController.openRoof();

        //then
        Mockito.verify(roofControllerMock).openRoof(); // weryfikacja czy pod spodem została wywolana metoda .openRoof() z mocka
    }

    @Test
    void shouldThrowExceptionWhenSpeedIsOver30OnOpenRoof() {
        //given
        RoofController roofControllerMock = Mockito.mock(RoofController.class);
        SpeedSensor speedSensorMock = Mockito.mock(SpeedSensor.class);
        OnBoardComputerController onBoardComputerController = new OnBoardComputerController(roofControllerMock, speedSensorMock);
        Mockito.when(speedSensorMock.getSpeed()).thenReturn(50.0);
//        Mockito.doThrow(new IllegalStateException()).when(roofControllerMock).closeRoof(); Rzucanie wyjatku, gdy funkcja zwraca void

        //when then
        Assertions.assertThrows(IllegalStateException.class, onBoardComputerController::openRoof);
        Mockito.verify(roofControllerMock, Mockito.times(0)).openRoof(); //Weryfikujemy czy bylo 0 wywołan funkcji openRoof()
        Mockito.verifyNoInteractions(roofControllerMock);// Weryfikujemy czy bylo 0 interakcji z całym mockiem
    }

    @Test
    void shouldCloseRoofWhenSpeedIsBelow30() {
        //given
        RoofController roofControllerMock = Mockito.mock(RoofController.class);
        SpeedSensor speedSensorMock = Mockito.mock(SpeedSensor.class);
        OnBoardComputerController onBoardComputerController = new OnBoardComputerController(roofControllerMock, speedSensorMock);
        Mockito.when(speedSensorMock.getSpeed()).thenReturn(0.0);

        //when
        onBoardComputerController.closeRoof();

        //then
        Mockito.verify(roofControllerMock).closeRoof();
    }

    @Test
    void shouldThrowExceptionWhenSpeedIsOver30OnCloseRoof() {
        //given
        RoofController roofControllerMock = Mockito.mock(RoofController.class);
        SpeedSensor speedSensorMock = Mockito.mock(SpeedSensor.class);
        OnBoardComputerController onBoardComputerController = new OnBoardComputerController(roofControllerMock, speedSensorMock);
        Mockito.when(speedSensorMock.getSpeed()).thenReturn(70.0);

        //when then
        Assertions.assertThrows(IllegalStateException.class, onBoardComputerController::closeRoof);
        Mockito.verifyNoInteractions(roofControllerMock);
    }
}
