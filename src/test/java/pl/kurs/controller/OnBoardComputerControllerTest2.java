package pl.kurs.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kurs.service.SpeedSensor;

@ExtendWith(MockitoExtension.class)
public class OnBoardComputerControllerTest2 {
    @Mock
    private RoofController roofControllerMock;
    @Mock
    private SpeedSensor speedSensorMock;
    @InjectMocks
    private OnBoardComputerController onBoardComputerController;

    @Test
    void shouldOpenRoofWhenSpeedIsBelow30() {
        //given
        Mockito.when(speedSensorMock.getSpeed()).thenReturn(20.0); // powiedzenie mockowi, ze ma odpowiadac wartoscia 20, na kazde wywolanie getSpeed()

        //when
        onBoardComputerController.openRoof();

        //then
        Mockito.verify(roofControllerMock).openRoof(); // weryfikacja czy pod spodem została wywolana metoda .openRoof() z mocka
    }

    @Test
    void shouldThrowExceptionWhenSpeedIsOver30OnOpenRoof() {
        //given
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
        Mockito.when(speedSensorMock.getSpeed()).thenReturn(0.0);

        //when
        onBoardComputerController.closeRoof();

        //then
        Mockito.verify(roofControllerMock).closeRoof();
    }

    @Test
    void shouldThrowExceptionWhenSpeedIsOver30OnCloseRoof() {
        //given
        Mockito.when(speedSensorMock.getSpeed()).thenReturn(45.0);

        //when then
        Assertions.assertThrows(IllegalStateException.class, onBoardComputerController::closeRoof);
        Mockito.verifyNoInteractions(roofControllerMock);
    }
}
