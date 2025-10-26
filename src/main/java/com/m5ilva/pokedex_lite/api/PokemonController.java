package com.m5ilva.pokedex_lite.api;

import com.m5ilva.pokedex_lite.client.dto.PokemonDto;
import com.m5ilva.pokedex_lite.service.PokemonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/pokemon")
public class PokemonController {
    private final PokemonService pokemonService;


    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/name/{name}")
    public Mono<ResponseEntity<PokemonDto>> getByName(@PathVariable("name") String name) {
        return pokemonService.getByName(name).map(result -> ResponseEntity.ok(result));
    }

    @GetMapping("/id/{id}")
    public Mono<ResponseEntity<PokemonDto>>getById(@PathVariable("id") int id){
        return pokemonService.getById(id).map(result -> ResponseEntity.ok(result));
    }
}
