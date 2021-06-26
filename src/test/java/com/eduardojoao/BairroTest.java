package com.eduardojoao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

    private Bairro bairroTeste;
    private Ponto pSupEsq;
    private Area a1, a2;
    private Reta reta1, reta2;

    @BeforeEach
    void setUp() {
        pSupEsq = new Ponto(3, 5);
        bairroTeste = Bairro.novoBairroQuadrado("Bom Fim", pSupEsq, 2, 10.0);

        reta1 = new Reta(new Ponto(10,10), new Ponto(20,10));
        reta2 = new Reta(new Ponto(10,10), new Ponto(20,20));

        a1 = mock(Area.class);
        when(a1.classifica(reta1)).thenReturn(SituacaoReta.TODA_DENTRO);

        a2 = mock(Area.class);
        when(a2.classifica(reta2)).thenReturn(SituacaoReta.TODA_FORA);
        when(a2.classifica(reta1)).thenReturn(SituacaoReta.INTERSECTA);
    }
    
    @Test
    public void criarUmNovoBairro() {
        // setup mocks
        Bairro mockBairro = mock(Bairro.class);
        when(mockBairro.getArea()).thenReturn(a2);
        when(mockBairro.getNome()).thenReturn("Auxiliadora");
        when(mockBairro.getCustoTransporte()).thenReturn(10.0);

        // create Bairro for test
        Bairro bairro = new Bairro("Auxiliadora", a2, 10);

        // Asserts
        assertEquals(mockBairro.getArea(), bairro.getArea());
        assertEquals(mockBairro.getCustoTransporte(), bairro.getCustoTransporte());
        assertEquals(mockBairro.getNome(), bairro.getNome());
        // Assert for toString layout
        assertTrue(bairro.toString().contains("Bairro [area="));
        assertTrue(bairro.toString().contains(", nome="));
        assertTrue(bairro.toString().contains("]"));

    }
    
    @Test
    public void testNovoBairroRetangular() {
        // setup mocks
        Bairro mockBairroRetangular = mock(Bairro.class);
        when(mockBairroRetangular.getNome()).thenReturn("Cristal");
        when(mockBairroRetangular.getCustoTransporte()).thenReturn(5.0);

        // create Bairro Retangular for test
        Bairro bairroRetangular =  Bairro.novoBairroRetangular("Cristal", pSupEsq, 1, 2, 5);

        // Asserts
        assertEquals(mockBairroRetangular.getCustoTransporte(), bairroRetangular.getCustoTransporte());
        assertEquals(mockBairroRetangular.getNome(), bairroRetangular.getNome());
        assertEquals(4, bairroRetangular.getArea().getPInfDir().getX());
        assertEquals(3, bairroRetangular.getArea().getPInfDir().getY());
        assertEquals(3, bairroRetangular.getArea().getPSupEsq().getX());
        assertEquals(5, bairroRetangular.getArea().getPSupEsq().getY());

    }
    
    @Test
    public void testNovoBairroQuadrado() {
        // setup mocks
        Bairro mockBairroQuadrado = mock(Bairro.class);
        when(mockBairroQuadrado.getNome()).thenReturn("Alvorada");
        when(mockBairroQuadrado.getCustoTransporte()).thenReturn(5.0);

        // create Bairro Retangular for test
        Bairro bairroQuadrado =  Bairro.novoBairroQuadrado("Alvorada", pSupEsq, 1, 5);

        // Asserts
        assertEquals(mockBairroQuadrado.getCustoTransporte(), bairroQuadrado.getCustoTransporte());
        assertEquals(mockBairroQuadrado.getNome(), bairroQuadrado.getNome());
        assertEquals(4, bairroQuadrado.getArea().getPInfDir().getX());
        assertEquals(4, bairroQuadrado.getArea().getPInfDir().getY());
        assertEquals(3, bairroQuadrado.getArea().getPSupEsq().getX());
        assertEquals(5, bairroQuadrado.getArea().getPSupEsq().getY());

    }

    @Test
    public void mudarCustoTransporte() {
        
        // given
        // when
        bairroTeste.alteraCustoTransporte(12);

        // then
        assertEquals(12, bairroTeste.getCustoTransporte());
    }

    @Test
    public void dispararIllegalArgumentAoMudarCustoTransporte() {

        // given
        // when
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bairroTeste.alteraCustoTransporte(-1);
        });

        String mensagemEsperada = "Valor invalido";
        String mensagemAtual = exception.getMessage();

        // then
        assertTrue(mensagemAtual.contains(mensagemEsperada));
    }
    
    @Test
    public void testEquals() {

        // setup instances
        Bairro BairroExperado = new Bairro("Restinga Velha", a1, 10);
        Bairro bairroIgual = new Bairro("Restinga Velha", a1, 10);
        Bairro bairroDiferente = new Bairro("Assunção", a2, 5);

        // then
        assertTrue(BairroExperado.equals(bairroIgual));
        assertFalse(BairroExperado.equals(bairroDiferente));
    }
}