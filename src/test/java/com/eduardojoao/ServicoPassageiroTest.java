package com.eduardojoao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;

import com.eduardojoao.casosDeUso.Politicas.CalculoCustoViagem;
import com.eduardojoao.casosDeUso.Politicas.CustoViagem;
import com.eduardojoao.casosDeUso.Repositorios.RepositorioBairros;
import com.eduardojoao.casosDeUso.Repositorios.RepositorioPassageiros;
import com.eduardojoao.casosDeUso.Servicos.ServicosPassageiro;
import com.eduardojoao.entidades.Bairro;
import com.eduardojoao.entidades.Passageiro;
import com.eduardojoao.entidades.Roteiro;
import com.eduardojoao.entidades.Viagem;
import com.eduardojoao.entidades.geometria.Area;
import com.eduardojoao.entidades.geometria.Ponto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ServicoPassageiroTest {

    private RepositorioBairros repositorioBairros;
    private RepositorioPassageiros repositorioPassageiros;
    private ServicosPassageiro servicosPassageiro;
    private Bairro bairro1, bairro2;
    private Passageiro passageiro;

    @BeforeEach
    void setUp() {

        Area area = new Area(new Ponto(2, 5), new Ponto(4, 3));

        bairro1 = new Bairro("Bom Fim", area, 25);
        bairro2 = new Bairro("Menino Deus", area, 40);

        repositorioBairros = mock(RepositorioBairros.class);
        when(repositorioBairros.recuperaListaBairros()).thenReturn(List.of(
            bairro1,
            bairro2
        ));
        when(repositorioBairros.recuperaPorNome("Bom Fim")).thenReturn(bairro1);
        when(repositorioBairros.recuperaPorNome("Menino Deus")).thenReturn(bairro2);

        passageiro = Passageiro.novoPassageiro("60076180050", "Eduardo");

        repositorioPassageiros = mock(RepositorioPassageiros.class);
        when(repositorioPassageiros.listaPassageiros()).thenReturn(List.of(
            passageiro,
            Passageiro.novoPassageiro("53597785242", "Lucas"),
            Passageiro.novoPassageiro("00244797056", "João")
        ));
        when(repositorioPassageiros.recuperaPorCPF("60076180050")).thenReturn(passageiro);

        CalculoCustoViagem ccv = mock(CalculoCustoViagem.class);
        
        servicosPassageiro = new ServicosPassageiro(repositorioBairros, repositorioPassageiros, ccv);
    }

    @Test
    public void testarGetListaBairros() {
        List<String> listaBairrosAtual = servicosPassageiro.getListaBairros();
        List<String> listaBairrosEsperada = repositorioBairros.recuperaListaBairros()
                                            .stream()
                                            .map(b->b.getNome())
                                            .collect(Collectors.toList());
        assertEquals(listaBairrosEsperada, listaBairrosAtual);
    }

    @Test
    public void testarGetPassageirosCadastrados() {
        List<String> listaPassageirosAtual = servicosPassageiro.getPassageirosCadastrados();
        List<String> listaPassageirosEsperado = repositorioPassageiros.listaPassageiros()
                                            .stream()
                                            .map(b->b.getNome())
                                            .collect(Collectors.toList());
        assertEquals(listaPassageirosEsperado, listaPassageirosAtual);
    }

    @Test
    public void testarCriaRoteiro() {
        Roteiro roteiroAtual = servicosPassageiro.criaRoteiro("Bom Fim", "Menino Deus");
        assertEquals(new Roteiro(bairro1, bairro2, repositorioBairros.recuperaListaBairros()), roteiroAtual);
    }

    @Test
    public void testarCriaViagem() {
        Roteiro roteiro = mock(Roteiro.class);
        CustoViagem cv = mock(CustoViagem.class);

        Viagem viagemAtual = servicosPassageiro.criaViagem(1, roteiro, "60076180050");
        viagemAtual.toString();
        Viagem viagemEsperada = new Viagem(1, viagemAtual.getDataHora(), roteiro, passageiro, cv.custoViagem(roteiro, passageiro));
        
        assertEquals(viagemEsperada.getId(), viagemAtual.getId());
        assertEquals(viagemEsperada.getDataHora(), viagemAtual.getDataHora());
        assertEquals(viagemEsperada.getRoteiro(), viagemAtual.getRoteiro());
        assertEquals(viagemEsperada.getPassageiro(), viagemAtual.getPassageiro());
        assertEquals(viagemEsperada.getValorCobrado(), viagemAtual.getValorCobrado());
    }
}
