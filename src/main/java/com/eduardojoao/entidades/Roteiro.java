package com.eduardojoao.entidades;

import java.util.Collection;
import java.util.LinkedList;

import com.eduardojoao.entidades.geometria.Ponto;
import com.eduardojoao.entidades.geometria.Reta;
import com.eduardojoao.entidades.geometria.SituacaoReta;

public class Roteiro {
    private Bairro bairroOrigem;
    private Bairro bairroDestino;
    private Reta rota;
    private Collection<Bairro> bairrosPercorridos;

    private void determinaBairrosPercorridos(Reta rota,Collection<Bairro> todosBairros){
        for(Bairro bairro:todosBairros){
            SituacaoReta sr = bairro.getArea().classifica(rota);
            if (sr != SituacaoReta.TODA_FORA){
                bairrosPercorridos.add(bairro);
            }
        }
    }

    public Roteiro(Bairro bairroOrigem, Bairro bairroDestino,Collection<Bairro> todosBairros) {
        this.bairroOrigem = bairroOrigem;
        this.bairroDestino = bairroDestino;
        bairrosPercorridos = new LinkedList<>(); // ERA UM BUG: linha omissa
        Ponto pOrig = bairroOrigem.getArea().pontoCentral();
        //Ponto pDest = bairroOrigem.getArea().pontoCentral(); // BUG estava pegando o mesmo ponto para origem e destino
        Ponto pDest = bairroDestino.getArea().pontoCentral();
        rota =  new Reta(pOrig,pDest);
        determinaBairrosPercorridos(rota,todosBairros);
    }

    public Reta getRota(){
        return rota;
    }
    
    public Bairro getBairroOrigem() {
        return bairroOrigem;
    }

    public Bairro getBairroDestino() {
        return bairroDestino;
    }

    public Collection<Bairro> bairrosPercoridos(){
        return bairrosPercorridos;
    }
    
    @Override
    public String toString() {
        return "Roteiro [bairroDestino=" + bairroDestino + ", bairroOrigem=" + bairroOrigem + "]";
    }

    @Override
    public boolean equals(Object outro){
        if (outro instanceof Roteiro){
            Roteiro outroRoteiro = (Roteiro)outro;
            return this.getBairroOrigem().equals(outroRoteiro.getBairroOrigem()) &&
                   this.getBairroDestino().equals(outroRoteiro.getBairroDestino());
        }else{
            return false;
        }
    }
}