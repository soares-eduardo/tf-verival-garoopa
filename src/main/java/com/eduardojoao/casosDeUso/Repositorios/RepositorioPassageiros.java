package com.eduardojoao.casosDeUso.Repositorios;

import java.util.List;

import com.eduardojoao.entidades.Passageiro;

public interface RepositorioPassageiros {
    List<Passageiro> listaPassageiros();
    Passageiro recuperaPorCPF(String cpf);
    void atualizaPassageiro(Passageiro passageiro);
}