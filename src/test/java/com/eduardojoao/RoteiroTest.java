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

    private List<Bairro> listaBairros;

    @BeforeEach
    void setUp() {
        listaBairros = new ArrayList<>();
        listaBairros.add(Bairro.novoBairroRetangular("Bom Fim", new Ponto(10,40), 20, 10, 10.0));
        listaBairros.add(Bairro.novoBairroRetangular("Independencia", new Ponto(30,40), 20, 10, 20.0));
        listaBairros.add(Bairro.novoBairroRetangular("Moinhos de Vento", new Ponto(20,30), 20, 10, 30.0));
        listaBairros.add(Bairro.novoBairroRetangular("Auxiliadora", new Ponto(40,30), 20, 10, 20.0));
        listaBairros.add(Bairro.novoBairroRetangular("Boa Vista", new Ponto(40,20), 20, 10, 20.0));
    }

    @Test
    public void testarRota() {
        Roteiro script  = new Roteiro(listaBairros.get(1), listaBairros.get(4), listaBairros);
        Reta rota = new Reta(new Ponto(40, 35), new Ponto(50,15));
        assertEquals(rota, script.getRota());
    }

    @Test
    public void testarRotasPercorridas() {

        Roteiro script = new Roteiro(listaBairros.get(1), listaBairros.get(4), listaBairros);
        Collection<Bairro> bairrosPercorridos = new ArrayList<>();

        bairrosPercorridos.add(listaBairros.get(1));
        bairrosPercorridos.add(listaBairros.get(2));
        bairrosPercorridos.add(listaBairros.get(3));
        bairrosPercorridos.add(listaBairros.get(4));

        Collection<Bairro> bairrosTestados = script.bairrosPercoridos();
        assertEquals(bairrosPercorridos, bairrosTestados);
    }
}