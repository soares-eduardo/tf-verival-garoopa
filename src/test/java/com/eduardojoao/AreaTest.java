package com.eduardojoao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.eduardojoao.entidades.geometria.Area;
import com.eduardojoao.entidades.geometria.Ponto;
import com.eduardojoao.entidades.geometria.Reta;
import com.eduardojoao.entidades.geometria.SituacaoReta;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * AreaTest
 */
public class AreaTest {

    private Area areaUnderTest;
    private Ponto pSupEsq;
    private Ponto pInfDir;

    @BeforeEach
    void setUp() {
        pSupEsq = new Ponto(2, 5);
        pInfDir = new Ponto(4, 3);
        areaUnderTest = new Area(pSupEsq, pInfDir);
    }

    @Test
    public void itShouldCreateAreaConstructor() {

        // then
        assertEquals(2, pSupEsq.getX());
        assertEquals(5, pSupEsq.getY());
        assertEquals(4, pInfDir.getX());
        assertEquals(3, pInfDir.getY());
    }

    @Test
    public void throwIllegalArgumentWhenCreatingConstructor() {

        // given
        Ponto pSupEsq = new Ponto(8, 6);
        Ponto pInfDir = new Ponto(5, 9);

        // when
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            areaUnderTest = new Area(pSupEsq, pInfDir);
        });

        String expectedMessage = "O retangulo deve ser definido pela diagonal principal";
        String actualMessage = exception.getMessage();

        // then
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void itShouldReturnObjectPontoCentral() {

        // given
        int pontoCentralX = 3;
        int pontoCentralY = 4;

        // when
        // then
        Ponto pontoCentral = areaUnderTest.pontoCentral();

        assertEquals(pontoCentralX, pontoCentral.getX());
        assertEquals(pontoCentralY, pontoCentral.getY());
    }

    @Test
    public void pontoShouldBeAboveAndRight() {

        // given
        Ponto givenPonto = new Ponto(8, 10);

        // when
        byte cod = areaUnderTest.codificaPonto(givenPonto);

        // then
        assertEquals(5, cod);
    }

    @Test
    public void pontoShouldBeAboveAndLeft() {

        // given
        Ponto givenPonto = new Ponto(1, 10);

        // when
        byte cod = areaUnderTest.codificaPonto(givenPonto);

        // then
        assertEquals(9, cod);
    }

    @Test
    public void pontoShouldBeBellowAndRight() {

        // given
        Ponto givenPonto = new Ponto(5, 2);

        // when
        byte cod = areaUnderTest.codificaPonto(givenPonto);

        // then
        assertEquals(6, cod);
    }

    @Test
    public void pontoShouldBeBellowAndLeft() {

        // given
        Ponto givenPonto = new Ponto(1, 2);

        // when
        byte cod = areaUnderTest.codificaPonto(givenPonto);

        // then
        assertEquals(10, cod);
    }

    @ParameterizedTest
    @CsvSource({"15, 40, 35, 40, TODA_DENTRO",
                "15,  5, 35,  5, TODA_FORA"})
    public void itShouldRetornAnySituacaoRetaValue(int x1, int y1, int x2, int y2, String status) {

        //given
        areaUnderTest = new Area(new Ponto(10,50), new Ponto(60,10));
        Reta reta = new Reta(new Ponto(x1,y1), new Ponto(x2, y2));

        // when
        SituacaoReta statusReta = switch(status) {
            case "TODA_DENTRO" -> SituacaoReta.TODA_DENTRO;
            case "TODA_FORA" -> SituacaoReta.TODA_FORA;
            case "INTERSECTA" -> SituacaoReta.INTERSECTA;
            default -> SituacaoReta.TODA_DENTRO;
        };

        //then
        assertEquals(statusReta, areaUnderTest.classifica(reta));
    }
}