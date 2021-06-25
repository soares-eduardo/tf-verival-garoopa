package com.eduardojoao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.eduardojoao.casosDeUso.Politicas.CalculoCustoViagemBasico;
import com.eduardojoao.entidades.Bairro;
import com.eduardojoao.entidades.Roteiro;
import com.eduardojoao.entidades.geometria.Area;
import com.eduardojoao.entidades.geometria.Ponto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculoCustoViagemBasicoTest {

    private List<Bairro> listaBairros;
    private Roteiro roteiro;

    @BeforeEach
    void setUp() {

        // Repositorio Bairros

        Area area1 = new Area(new Ponto(2, 5), new Ponto(4, 3));
        Bairro bairro = new Bairro("Bom Fim", area1, 10);
        
        listaBairros = new ArrayList<>();

        listaBairros.add(bairro);
        listaBairros.add(Bairro.novoBairroRetangular("Independencia", new Ponto(30,40), 20, 10, 20.0));
        listaBairros.add(Bairro.novoBairroRetangular("Moinhos de Vento", new Ponto(20,30), 20, 10, 30.0));
        
        roteiro = mock(Roteiro.class);
        when(roteiro.bairrosPercoridos()).thenReturn(listaBairros);
    }

    @Test
    public void testarCalculoCustoBasico() {

        CalculoCustoViagemBasico ccvb = new CalculoCustoViagemBasico();

        ccvb.defineRoteiro(roteiro);

        double resp = ccvb.calculoCustoBasico();

        assertEquals(60, resp);
    }

    @Test
    public void testarCustoViagem() {

        CalculoCustoViagemBasico ccvb = new CalculoCustoViagemBasico();
        
        ccvb.defineRoteiro(roteiro);

        double resp = ccvb.custoViagem();

        assertEquals(60, resp);
    }
}
