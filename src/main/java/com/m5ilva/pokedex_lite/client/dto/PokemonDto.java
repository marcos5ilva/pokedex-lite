package com.m5ilva.pokedex_lite.client.dto;

import java.util.List;

public record PokemonDto(
        int id,
        String name,
        int height,
        int weight,
        List<String> types
) {
}
