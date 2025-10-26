package com.m5ilva.pokedex_lite.service;

import com.m5ilva.pokedex_lite.api.PokeApiClient;
import com.m5ilva.pokedex_lite.client.dto.PokeTypeSlot;
import com.m5ilva.pokedex_lite.client.dto.PokemonDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Comparator;

@Service
public class PokemonService {
    private final PokeApiClient client;

    public PokemonService(PokeApiClient client) {
        this.client = client;
    }

    public Mono<PokemonDto> getByName(String name) {
        return client.getPokemonByName(name)
                .map(pokemon -> new PokemonDto(pokemon.id(),
                        pokemon.name(),
                        pokemon.height(),
                        pokemon.weight(),
                        pokemon.types().stream().sorted(Comparator.comparingInt(PokeTypeSlot::slot))
                                .map(s -> s.type().name())
                                .toList()
                ));
    }

    public Mono<PokemonDto> getById(int id) {
        return client.getPokemonById(id)
                .map(pokemon -> new PokemonDto(pokemon.id(), pokemon.name(),
                        pokemon.height(),
                        pokemon.weight(),
                        pokemon.types().stream().sorted(Comparator.comparingInt(PokeTypeSlot::slot))
                                .map(s -> s.type().name())
                                .toList()
                ));
    }
}
