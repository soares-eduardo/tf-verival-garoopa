package com.eduardojoao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.eduardojoao.entidades.Bairro;
import com.eduardojoao.entidades.geometria.Area;
import com.eduardojoao.entidades.geometria.Ponto;
import com.eduardojoao.entidades.geometria.Reta;
import com.eduardojoao.entidades.geometria.SituacaoReta;

import org.junit.jupiter.api.*;

/**
 * BairroTest
 */
public class BairroTest {

    private Bairro bairroUnderTest;
    private Ponto pSupEsq;
    private Area a1, a2;
    private Reta reta1, reta2;

    @BeforeEach
    void setUp() {
        pSupEsq = new Ponto(3, 5);
        bairroUnderTest = Bairro.novoBairroQuadrado("Bom Fim", pSupEsq, 2, 10.0);

        reta1 = new Reta(new Ponto(10,10), new Ponto(20,10));
        reta2 = new Reta(new Ponto(10,10), new Ponto(20,20));

        a1 = mock(Area.class);
        when(a1.classifica(reta1)).thenReturn(SituacaoReta.TODA_DENTRO);

        a2 = mock(Area.class);
        when(a2.classifica(reta2)).thenReturn(SituacaoReta.TODA_FORA);
        when(a2.classifica(reta1)).thenReturn(SituacaoReta.INTERSECTA);
    }

    @Test
    public void itShouldCreateANewBairro() {
        Bairro bairro = new Bairro("Auxiliadora", a2, 10);
    }

    @Test
    public void itShouldChangeCustoTransporteValue() {
        
        // given
        // when
        bairroUnderTest.alteraCustoTransporte(12);

        // then
        assertEquals(12, bairroUnderTest.getCustoTransporte());
    }

    @Test
    public void itShouldThrowIllegalArgumentWhenChangingCustoTransporteValue() {

        // given
        // when
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bairroUnderTest.alteraCustoTransporte(-1);
        });

        String expectedMessage = "Valor invalido";
        String actualMessage = exception.getMessage();

        // then
        assertTrue(actualMessage.contains(expectedMessage));
    }
}