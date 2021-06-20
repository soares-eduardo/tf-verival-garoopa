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

    private static Passageiro passageiroUnderTest;

    private static String cpf;
    private static String name;

    @BeforeEach
    void setUp() {
        cpf = "60076180050";
        name = "Eduardo";
        passageiroUnderTest = Passageiro.novoPassageiro(cpf, name);
    }

    @Test
    public void itShouldCreateAndReturnANewPassageiro() {

        // give
        // when
        // then
        
        assertEquals(cpf, passageiroUnderTest.getCPF());
        assertEquals(name, passageiroUnderTest.getNome());
        assertEquals(8, passageiroUnderTest.getPontuacaoAcumulada());
        assertEquals(1, passageiroUnderTest.getQtdadeAvaliacoes());
    }

    @Test
    public void itShouldUpdateAndReturnANewPassageiro() {

        // give

        int pontuacaoAcumulada = 9;
        int qtdadeAvaliacoes = 6;

        // when

        passageiroUnderTest = Passageiro.passageiroExistente(cpf, name, pontuacaoAcumulada, qtdadeAvaliacoes);

        // then

        assertEquals(cpf, passageiroUnderTest.getCPF());
        assertEquals(name, passageiroUnderTest.getNome());
        assertEquals(pontuacaoAcumulada, passageiroUnderTest.getPontuacaoAcumulada());
        assertEquals(qtdadeAvaliacoes, passageiroUnderTest.getQtdadeAvaliacoes());
    }

    @Test
    public void itShoudReturnAvgOfPontuacaoAcumuladaAndQtdadeAvaliacoes() {

        // give
        // when
        // then

        assertEquals(8, passageiroUnderTest.getPontuacaoMedia());
    }

    @Test
    public void itShouldIncreasePontuacaoAcumuladaAndQtdadeAvaliacoes() {

        // give

        int pontuacao = 2;

        // when

        passageiroUnderTest.infoPontuacao(pontuacao);

        // then

        assertEquals(10, passageiroUnderTest.getPontuacaoAcumulada());
        assertEquals(2, passageiroUnderTest.getQtdadeAvaliacoes());
    }

    @ParameterizedTest
    @CsvSource({ "-2", "0" })
    public void itShouldThrowIllegalArgumentCasePontuacaoLessThenOrEqualsToZero(int pontuacao) {

        // give
        // when

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            passageiroUnderTest.infoPontuacao(pontuacao);
        });

        String expectedMessage = "Pontucao invalida !";
        String actualMessage = exception.getMessage();

        // then

        assertTrue(actualMessage.contains(expectedMessage));
    }
}