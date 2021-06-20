package com.eduardojoao.casosDeUso.Repositorios;

import java.util.List;

import com.eduardojoao.entidades.Bairro;

public interface RepositorioBairros {
    Bairro recuperaPorNome(String nomeBairro);
    List<Bairro> recuperaListaBairros();
}