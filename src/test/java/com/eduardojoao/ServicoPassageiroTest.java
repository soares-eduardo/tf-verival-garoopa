package com.eduardojoao;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import com.eduardojoao.casosDeUso.Politicas.CalculoCustoViagem;
import com.eduardojoao.casosDeUso.Politicas.CalculoCustoViagemBasico;
import com.eduardojoao.casosDeUso.Politicas.CustoViagem;
import com.eduardojoao.casosDeUso.Repositorios.RepositorioBairros;
import com.eduardojoao.casosDeUso.Repositorios.RepositorioPassageiros;
import com.eduardojoao.casosDeUso.Servicos.ServicosPassageiro;
import com.eduardojoao.entidades.Bairro;
import com.eduardojoao.entidades.Passageiro;
import com.eduardojoao.entidades.geometria.Area;
import com.eduardojoao.entidades.geometria.Ponto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ServicoPassageiroTest {

    private RepositorioBairros repositorioBairros;
    private RepositorioPassageiros repositorioPassageiros;

    private Area area1, area2;

    private List<Bairro> listaBairros;
    private List<Passageiro> listaPassageiros;

    private CustoViagem custoViagem;

    private CalculoCustoViagem calculoCustoViagem;

    private ServicosPassageiro servicosPassageiro;

    @BeforeEach
    void setUp() {

        // Repositorio Bairros

        area1 = new Area(new Ponto(2, 5), new Ponto(4, 3));
        repositorioBairros = mock(RepositorioBairros.class);

        // Método 1

        when(repositorioBairros.recuperaPorNome("Bom Fim")).thenReturn(new Bairro("Bom Fim", area1, 10));

        // Método 2

        listaBairros = new ArrayList<>();
        listaBairros.add(Bairro.novoBairroRetangular("Bom Fim", new Ponto(10,40), 20, 10, 10.0));
        listaBairros.add(Bairro.novoBairroRetangular("Independencia", new Ponto(30,40), 20, 10, 20.0));
        listaBairros.add(Bairro.novoBairroRetangular("Moinhos de Vento", new Ponto(20,30), 20, 10, 30.0));

        when(repositorioBairros.recuperaListaBairros()).thenReturn(listaBairros);

        // Repositorio Passageiros

        repositorioPassageiros = mock(RepositorioPassageiros.class);

        // Método 1

        listaPassageiros = new ArrayList<>();
        Passageiro passageiro = Passageiro.novoPassageiro("60076180050", "Eduardo");

        listaPassageiros.add(passageiro);
        listaPassageiros.add(Passageiro.novoPassageiro("00244797056", "Joao"));
        listaPassageiros.add(Passageiro.novoPassageiro("53597788134", "Lucas"));
        
        when(repositorioPassageiros.listaPassageiros()).thenReturn(listaPassageiros);

        // Método 2

        when(repositorioPassageiros.recuperaPorCPF("60076180050")).thenReturn(passageiro);

        // Custo Viagem

        CalculoCustoViagem ccv = mock(CalculoCustoViagemBasico.class);

        servicosPassageiro = new ServicosPassageiro(repositorioBairros, repositorioPassageiros, ccv);
    }

    @Test
    public void retornarListaBairros() {
        assertThat(servicosPassageiro.getListaBairros(), listaPassageiros);
    }
}
