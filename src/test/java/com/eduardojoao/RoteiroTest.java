package com.eduardojoao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import com.eduardojoao.entidades.Bairro;
import com.eduardojoao.entidades.Roteiro;
import com.eduardojoao.entidades.geometria.Ponto;
import com.eduardojoao.entidades.geometria.Reta;

import org.junit.jupiter.api.*;

/**
 * RoteiroTest
 */
public class RoteiroTest {

    private List<Bairro> bairroList;

    @BeforeEach
    void setUp() {
        bairroList = new ArrayList<>();
        bairroList.add(Bairro.novoBairroRetangular("Bom Fim", new Ponto(10,40), 20, 10, 10.0));
        bairroList.add(Bairro.novoBairroRetangular("Independencia", new Ponto(30,40), 20, 10, 20.0));
        bairroList.add(Bairro.novoBairroRetangular("Moinhos de Vento", new Ponto(20,30), 20, 10, 30.0));
        bairroList.add(Bairro.novoBairroRetangular("Auxiliadora", new Ponto(40,30), 20, 10, 20.0));
        bairroList.add(Bairro.novoBairroRetangular("Boa Vista", new Ponto(40,20), 20, 10, 20.0));
    }

    @Test
    public void itShouldATestARoute() {
        Roteiro script  = new Roteiro(bairroList.get(1), bairroList.get(4), bairroList);
        Reta route = new Reta(new Ponto(40, 35), new Ponto(50,15));
        assertEquals(route, script.getRota());
    }

    @Test
    public void itShouldTestTraveledRoutes() {

        Roteiro script = new Roteiro(bairroList.get(1), bairroList.get(4), bairroList);
        Collection<Bairro> expected = new ArrayList<>();

        expected.add(bairroList.get(1));
        expected.add(bairroList.get(2));
        expected.add(bairroList.get(3));
        expected.add(bairroList.get(4));

        Collection<Bairro> underTest = script.bairrosPercoridos();
        assertEquals(expected, underTest);
    }

    

}