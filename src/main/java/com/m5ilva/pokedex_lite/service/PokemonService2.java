package com.m5ilva.pokedex_lite.service;

import com.m5ilva.pokedex_lite.api.PokeApiClient;
import com.m5ilva.pokedex_lite.client.dto.PokeTypeSlot;
import com.m5ilva.pokedex_lite.client.dto.PokemonDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Comparator;

@Service
public class PokemonService2 {
    private final PokeApiClient client;

    public PokemonService2(PokeApiClient client) {
        this.client = client;
    }

    public Mono<PokemonDto> getById(int id) {
        return client.getPokemonById(id).map(pokemon ->
                new PokemonDto(pokemon.id(),
                        pokemon.name(),
                        pokemon.height(),
                        pokemon.weight(),
                        pokemon.types().stream()
                                .sorted(Comparator.comparingInt(PokeTypeSlot::slot))
                                .map(slot -> slot.type().name())
                                .toList()
                ));
    }
}
