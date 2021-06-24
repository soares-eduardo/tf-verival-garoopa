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

    // TODO
    @Test
    public void criarUmNovoBairro() {
        Bairro bairro = new Bairro("Auxiliadora", a2, 10);
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
}