package com.opensanca.trilharest.filmes.filmes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.UUID;

public interface FilmesRepository extends CrudRepository<Filme, UUID> {

    @Query("select new com.opensanca.trilharest.filmes.filmes.FilmeResumidoDTO(f.id, f.nome, f.duracao) from Filme f where ?1 between f.inicioExibicao and f.fimExibicao")
    Page<FilmeResumidoDTO> buscarPaginaEmExibicao(
        Pageable parametrosDePaginacao,
        LocalDate referencia);
}
