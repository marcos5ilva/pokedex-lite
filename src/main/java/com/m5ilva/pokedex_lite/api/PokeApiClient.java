package com.m5ilva.pokedex_lite.api;

import com.m5ilva.pokedex_lite.client.dto.PokeApiPokemonResponse;
import com.m5ilva.pokedex_lite.domain.PokemonNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class PokeApiClient {
    private final WebClient webClient;

    public PokeApiClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://pokeapi.co").build();
    }

    public Mono<PokeApiPokemonResponse> getPokemonByName(String name) {
        return webClient.get()
                .uri(uri -> uri.path("/api/v2/pokemon/{name}")
                        .build(name.toLowerCase()))
                .retrieve()
                .onStatus(s -> s.value() == 404, resp -> Mono.error(new PokemonNotFoundException(name)))
                .bodyToMono(PokeApiPokemonResponse.class);
    }

    public Mono<PokeApiPokemonResponse> getPokemonById(int id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/v2/pokemon/{id}")
                        .build(id))
                .retrieve()
                .bodyToMono(PokeApiPokemonResponse.class);
    }
}
