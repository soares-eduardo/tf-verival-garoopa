package com.eduardojoao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.eduardojoao.entidades.Passageiro;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * PassageiroTest
 */
public class PassageiroTest {

    private static Passageiro passageiroTeste;

    private static String cpf;
    private static String name;

    @BeforeEach
    void setUp() {
        cpf = "60076180050";
        name = "Eduardo";
        passageiroTeste = Passageiro.novoPassageiro(cpf, name);
    }

    @Test
    public void criarERetornarUmPassageiro() {

        // give
        // when
        // then
        
        assertEquals(cpf, passageiroTeste.getCPF());
        assertEquals(name, passageiroTeste.getNome());
        assertEquals(8, passageiroTeste.getPontuacaoAcumulada());
        assertEquals(1, passageiroTeste.getQtdadeAvaliacoes());
    }

    @Test
    public void atualizarERetornarPassageiro() {

        // give

        int pontuacaoAcumulada = 9;
        int qtdadeAvaliacoes = 6;

        // when

        passageiroTeste = Passageiro.passageiroExistente(cpf, name, pontuacaoAcumulada, qtdadeAvaliacoes);

        // then

        assertEquals(cpf, passageiroTeste.getCPF());
        assertEquals(name, passageiroTeste.getNome());
        assertEquals(pontuacaoAcumulada, passageiroTeste.getPontuacaoAcumulada());
        assertEquals(qtdadeAvaliacoes, passageiroTeste.getQtdadeAvaliacoes());
    }

    @Test
    // retornar media da pontuacao e atualizar a qtd de avaliacoes
    public void retornarMediaDaPontuacao() {

        // give
        // when
        // then

        assertEquals(8, passageiroTeste.getPontuacaoMedia());
    }

    @Test
    public void atualizarPontuacaoAcumuladaEQtdAvaliacoes() {

        // give

        int pontuacao = 2;

        // when

        passageiroTeste.toString();
        passageiroTeste.infoPontuacao(pontuacao);

        // then

        assertEquals(10, passageiroTeste.getPontuacaoAcumulada());
        assertEquals(2, passageiroTeste.getQtdadeAvaliacoes());
    }

    @ParameterizedTest
    @CsvSource({ "-2", "0" })
    //
    public void retornarIllegalArgumentCasoPontuacaoMenorOuIgualAZero(int pontuacao) {

        // give
        // when

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            passageiroTeste.infoPontuacao(pontuacao);
        });

        String mensagemEsperada = "Pontucao invalida !";
        String mensagemAtual = exception.getMessage();

        // then

        assertTrue(mensagemAtual.contains(mensagemEsperada));
    }
}