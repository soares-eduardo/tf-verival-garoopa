package com.eduardojoao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

    private Area areaTeste;
    private Ponto pSupEsq;
    private Ponto pInfDir;

    @BeforeEach
    void setUp() {
        pSupEsq = new Ponto(2, 5);
        pInfDir = new Ponto(4, 3);
        areaTeste = new Area(pSupEsq, pInfDir);
    }

    @Test
    public void testarLogicaConstrutorArea() {

        // then
        assertEquals(2, pSupEsq.getX());
        assertEquals(5, pSupEsq.getY());
        assertEquals(4, pInfDir.getX());
        assertEquals(3, pInfDir.getY());
    }

    @Test
    public void dispararIllegalArgumentAoCriarObjetoArea() {

        // given
        Ponto pSupEsq = new Ponto(8, 6);
        Ponto pInfDir = new Ponto(5, 9);

        // when
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            areaTeste = new Area(pSupEsq, pInfDir);
        });

        String mensagemEsperada = "O retangulo deve ser definido pela diagonal principal";
        String mensagemAtual = exception.getMessage();

        // then
        assertTrue(mensagemAtual.contains(mensagemEsperada));
    }

    @Test
    public void retornarObjectoPontoCentral() {

        // given
        int pontoCentralX = 3;
        int pontoCentralY = 4;

        // when
        // then
        Ponto pontoCentral = areaTeste.pontoCentral();

        assertEquals(pontoCentralX, pontoCentral.getX());
        assertEquals(pontoCentralY, pontoCentral.getY());
    }

    @Test
    public void retornarPontoAcimaDireita() {

        // given
        Ponto ponto = new Ponto(8, 10);

        // when
        byte cod = areaTeste.codificaPonto(ponto);

        // then
        assertEquals(5, cod);
    }

    @Test
    public void retornarPontoAcimaEsquerda() {

        // given
        Ponto ponto = new Ponto(1, 10);

        // when
        byte cod = areaTeste.codificaPonto(ponto);

        // then
        assertEquals(9, cod);
    }

    @Test
    public void retornarPontoAbaixoDireita() {

        // given
        Ponto ponto = new Ponto(5, 2);

        // when
        byte cod = areaTeste.codificaPonto(ponto);

        // then
        assertEquals(6, cod);
    }

    @Test
    public void retornarPontoAbaixoEsquerda() {

        // given
        Ponto ponto = new Ponto(1, 2);

        // when
        byte cod = areaTeste.codificaPonto(ponto);

        // then
        assertEquals(10, cod);
    }

    @ParameterizedTest
    @CsvSource({"15, 40, 35, 40, TODA_DENTRO",
                "15,  5, 35,  5, TODA_FORA"})
    public void retornarAlgumStatus(int x1, int y1, int x2, int y2, String status) {

        //given
        areaTeste = new Area(new Ponto(10,50), new Ponto(60,10));
        Reta reta = new Reta(new Ponto(x1,y1), new Ponto(x2, y2));
        reta.toString();
        double tamanho = reta.tamanho();

        // when
        SituacaoReta statusReta = switch(status) {
            case "TODA_DENTRO" -> SituacaoReta.TODA_DENTRO;
            case "TODA_FORA" -> SituacaoReta.TODA_FORA;
            case "INTERSECTA" -> SituacaoReta.INTERSECTA;
            default -> SituacaoReta.TODA_DENTRO;
        };

        //then
        assertEquals(statusReta, areaTeste.classifica(reta));
        assertEquals(20, tamanho);
    }

    @Test
    public void retornarEqualsFalse() {

        // given
        Object object = new Object();

        // when
        boolean cod = areaTeste.equals(object);

        // then
        assertFalse(cod);
    }

    @Test
    public void retornarEqualsPontoFalse() {

        // given
        Object object = new Object();

        // when
        boolean cod = pSupEsq.equals(object);

        // then
        assertFalse(cod);
    }
}