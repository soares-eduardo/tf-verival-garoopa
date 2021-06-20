package com.eduardojoao.casosDeUso.Politicas;

import com.eduardojoao.entidades.Passageiro;
import com.eduardojoao.entidades.Roteiro;

public class CustoViagem {
    private CalculoCustoViagem ccv;
    
    public CustoViagem(CalculoCustoViagem ccv){
        this.ccv = ccv;
    }            

    public double custoViagem(Roteiro roteiro,Passageiro passageiro){
        ccv.defineRoteiro(roteiro);
        ccv.definePassageiro(passageiro);
        return ccv.custoViagem();
    }
}