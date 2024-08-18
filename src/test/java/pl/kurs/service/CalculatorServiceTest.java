package pl.kurs.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CalculatorServiceTest {
    @Mock
    private Calculator calculatorMock;
    @InjectMocks
    private CalculatorService calculatorService;

    @Test
    void shouldAdd2And3() {
        //given
        Mockito.when(calculatorMock.add(2, 3)).thenReturn(5); // Oczekujemy stricte 2 i 3, tylko wtedy mock odpowie 5

        //when
        int result = calculatorService.performAddition(2, 3);

        //then
        Assertions.assertEquals(5, result);
    }

    @Test
    void shouldAddAnyInt() {
        //given
        Mockito.when(calculatorMock.add(Mockito.anyInt(), Mockito.anyInt())).thenReturn(5); // any, anyInt(), any(class)... - oznacza ze jakakolwiek wartosc przyjdzie
                                                                                                // to bedzie uznana za zgodną z oczekiwaną, i mock nam odpowie

        //when
        int result = calculatorService.performAddition(55, 3);

        //then
        Assertions.assertEquals(5, result);
    }

    @Test
    void shouldAddAnyIntAnd3() {
        //given
        Mockito.when(calculatorMock.add(Mockito.anyInt(), Mockito.eq(3))).thenReturn(5); // pierwsza wartosc moze byc jakakolwiek, natomiast w przypadku drugiej
                                                                                                // oczekujemy wartosci rownej 3, w takiej sytuacji musimy uzyc funkcji eq()

        //when
        int result = calculatorService.performAddition(55, 3);

        //then
        Assertions.assertEquals(5, result);
    }


}
