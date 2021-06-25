package com.eduardojoao.casosDeUso.Servicos;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.eduardojoao.casosDeUso.Politicas.CustoViagem;
import com.eduardojoao.casosDeUso.Politicas.CalculoCustoViagem;
import com.eduardojoao.casosDeUso.Repositorios.RepositorioBairros;
import com.eduardojoao.casosDeUso.Repositorios.RepositorioPassageiros;
import com.eduardojoao.entidades.Bairro;
import com.eduardojoao.entidades.Passageiro;
import com.eduardojoao.entidades.Roteiro;
import com.eduardojoao.entidades.Viagem;

public class ServicosPassageiro {
    private RepositorioBairros repBairros;
    private RepositorioPassageiros repPassageiros;
    private CustoViagem custoViagem;

    public ServicosPassageiro(RepositorioBairros repBairros, RepositorioPassageiros repPassageiros,
            CalculoCustoViagem ccv) {
        this.repBairros = repBairros;
        this.repPassageiros = repPassageiros;
        this.custoViagem = new CustoViagem(ccv);
    }

    public List<String> getListaBairros() {
        return repBairros.recuperaListaBairros().stream().map(b -> b.getNome()).collect(Collectors.toList());
    }

    public List<String> getPassageirosCadastrados() {
        return repPassageiros.listaPassageiros().stream().map(p -> p.getNome()).collect(Collectors.toList());
    }

    public Roteiro criaRoteiro(String bairroOrigem, String bairroDestino) {
        Collection<Bairro> todosBairros = repBairros.recuperaListaBairros();
        Bairro bOrigem = repBairros.recuperaPorNome(bairroOrigem);
        Bairro bDestino = repBairros.recuperaPorNome(bairroDestino);
        return new Roteiro(bOrigem, bDestino, todosBairros);
    }

    public Viagem criaViagem(int id, Roteiro roteiro, String cpfPassageiro) {
        LocalDateTime data = LocalDateTime.now();
        Passageiro passageiro = repPassageiros.recuperaPorCPF(cpfPassageiro);
        double valorCobrado = custoViagem.custoViagem(roteiro, passageiro);
        return new Viagem(id, data, roteiro, passageiro, valorCobrado);
    }
}