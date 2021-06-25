package com.eduardojoao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import com.eduardojoao.casosDeUso.Politicas.CalculoCustoViagemBasico;
import com.eduardojoao.casosDeUso.Politicas.CalculoCustoViagemVerao;
import com.eduardojoao.entidades.Bairro;
import com.eduardojoao.entidades.Passageiro;
import com.eduardojoao.entidades.Roteiro;
import com.eduardojoao.entidades.geometria.Area;
import com.eduardojoao.entidades.geometria.Ponto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculoCustoViagemVeraoTest {
    
    private Passageiro passageiro;
    private Roteiro roteiro;
    private CalculoCustoViagemBasico custoBasico;
    private CalculoCustoViagemVerao calculoCustoViagemVerao;

    @BeforeEach
    void setUp() {

        calculoCustoViagemVerao = new CalculoCustoViagemVerao();

        Area area = new Area(new Ponto(2, 5), new Ponto(4, 3));

        roteiro = mock(Roteiro.class);
        when(roteiro.bairrosPercoridos())
                .thenReturn(List.of(new Bairro("Bom Fim", area, 10), new Bairro("Petropolis", area, 20),
                        new Bairro("Menino Deus", area, 30), new Bairro("Centro", area, 15)));

        custoBasico = mock(CalculoCustoViagemBasico.class);
        when(custoBasico.calculoCustoBasico()).thenReturn(150.0);

        passageiro = mock(Passageiro.class);
        when(passageiro.getPontuacaoMedia()).thenReturn(20);
        when(passageiro.getQtdadeAvaliacoes()).thenReturn(40);

        calculoCustoViagemVerao.definePassageiro(passageiro);
        calculoCustoViagemVerao.defineRoteiro(roteiro);
    }

    @Test
    public void testarDescontoPontuacao() {
        double descontoPontuacao = calculoCustoViagemVerao.descontoPontuacao();
        assertEquals(67.5, descontoPontuacao);
    }

    @Test
    public void testarDescontoPromocaoSazonal() {
        double descontoPromocaoSazonal = calculoCustoViagemVerao.descontoPromocaoSazonal();
        assertEquals(7.5, descontoPromocaoSazonal);
    }
}
