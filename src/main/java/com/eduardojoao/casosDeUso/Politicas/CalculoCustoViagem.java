package com.eduardojoao.casosDeUso.Politicas;

import com.eduardojoao.entidades.Passageiro;
import com.eduardojoao.entidades.Roteiro;

public interface CalculoCustoViagem {
    void defineRoteiro(Roteiro roteiro);
    void definePassageiro(Passageiro passageiro);
	public Roteiro getRoteiro();
	public Passageiro getPassageiro();
    double calculoCustoBasico();
    double descontoPontuacao();
    double descontoPromocaoSazonal();
    double custoViagem();
}