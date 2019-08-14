package com.opensanca.trilharest.filmes.filmes;

import java.time.Duration;
import java.time.LocalDate;
import java.util.UUID;

public class FilmeFormDTO {
    private String nome;
    private String sinopse;
    private Duration duracao;
    private LocalDate inicioExibicao;
    private LocalDate fimExibicao;

    public Filme construir() {
        Filme entidade = new Filme();
        entidade.setId(UUID.randomUUID());
        entidade.setNome(this.nome);
        entidade.setSinopse(this.sinopse);
        entidade.setDuracao(this.duracao);
        entidade.setInicioExibicao(this.inicioExibicao);
        entidade.setFimExibicao(this.fimExibicao);

        return entidade;
    }

    public void preencher(Filme entidade) {
        entidade.setNome(this.nome);
        entidade.setSinopse(this.sinopse);
        entidade.setDuracao(this.duracao);
        entidade.setInicioExibicao(this.inicioExibicao);
        entidade.setFimExibicao(this.fimExibicao);
    }

    public String getNome() {
        return nome;
    }

    public String getSinopse() {
        return sinopse;
    }

    public Duration getDuracao() {
        return duracao;
    }

    public LocalDate getInicioExibicao() {
        return inicioExibicao;
    }

    public LocalDate getFimExibicao() {
        return fimExibicao;
    }
}
