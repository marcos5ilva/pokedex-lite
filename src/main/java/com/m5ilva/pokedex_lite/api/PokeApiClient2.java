package com.m5ilva.pokedex_lite.api;

import com.m5ilva.pokedex_lite.client.dto.PokeApiPokemonResponse;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PokeApiClient2 {
    private WebClient webClient;
    private RestTemplate restTemplate;

    public PokeApiClient2(WebClient.Builder builder, RestTemplateBuilder restTemplateBuilder) {
        webClient = builder.baseUrl("https://pokeapi.co")
                .build();
        restTemplate = restTemplateBuilder
                .rootUri("https://pokeapi.co")
                .build();
    }

    public Mono<PokeApiPokemonResponse> getByIdAsync(int id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/v2/pokemon/{id}")
                        .build(id))
                .retrieve()
                .bodyToMono(PokeApiPokemonResponse.class);
    }

    public PokeApiPokemonResponse getById(int id){
        return restTemplate.getForObject("/api/v2/pokemon/{id}", PokeApiPokemonResponse.class, id);
    }
}
