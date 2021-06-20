package com.eduardojoao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.eduardojoao.casosDeUso.Politicas.CalculoCustoViagemBasico;
import com.eduardojoao.casosDeUso.Politicas.CalculoCustoViagem;
import com.eduardojoao.casosDeUso.Politicas.CustoViagem;

import org.junit.jupiter.api.*;

public class CustoViagemTest {

    @Test
    public void itShouldTestCustoViagem() {
        CalculoCustoViagem ccv = mock(CalculoCustoViagemBasico.class);
        when(ccv.custoViagem()).thenReturn(35.0);

        CustoViagem cv = new CustoViagem(ccv);
        double custoObs = cv.custoViagem(null, null);
        double custoEsp = 35;
        assertEquals(custoEsp, custoObs, 0.001);
    }
}
