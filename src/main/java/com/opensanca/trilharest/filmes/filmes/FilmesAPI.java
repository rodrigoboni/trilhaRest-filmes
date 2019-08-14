package com.opensanca.trilharest.filmes.filmes;

import com.opensanca.trilharest.filmes.comum.EntidadeNaoEncontradaException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/filmes")
@Api(description = "API para acesso e manipulação dos filmes em cartaz")
public class FilmesAPI {

    @Autowired
    private FilmesRepository filmesRepository;

    // http://localhost:8080/filmes/em-exibicao?pagina=1&tamanhoDaPagina=3
    @RequestMapping(path="/em-exibicao", method= RequestMethod.GET)
    @ApiOperation(value="Buscar página de filmes em exibição",
                  notes="Permite a busca paginada de filmes em exibição," +
                        "ou seja, filmes que possuam data de início e término " +
                        "de exibição e cujo período engloba a data atual")
    public Page<FilmeResumidoDTO> getEmExibicao(
            Pageable parametrosDePaginacao) {

        if (parametrosDePaginacao == null) {
            parametrosDePaginacao = new PageRequest(0, 3);
        }

        LocalDate hoje = LocalDate.now();

        return this.filmesRepository.buscarPaginaEmExibicao(parametrosDePaginacao, hoje);
    }

    @GetMapping("/{id}")
    public FilmeDetalhadoDTO getPorId(@PathVariable UUID id) {
        Filme filme = this.filmesRepository.findOne(id);

        if(filme == null) {
            throw new EntidadeNaoEncontradaException();
        }

        return new FilmeDetalhadoDTO(filme);
    }

    @PostMapping
    public UUID cadastrar(@RequestBody FilmeFormDTO dados) {
        Filme entidade = dados.construir();
        filmesRepository.save(entidade);
        return entidade.getId();
    }

    @PutMapping("/{id}")
    public void alterar(@RequestBody FilmeFormDTO dados, @PathVariable UUID id) {
        Filme entidade = filmesRepository.findOne(id);
        dados.preencher(entidade);
        filmesRepository.save(entidade);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable UUID id) {
        filmesRepository.delete(id);
    }

}
