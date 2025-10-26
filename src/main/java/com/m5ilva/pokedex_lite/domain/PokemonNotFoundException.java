package com.m5ilva.pokedex_lite.domain;

public class PokemonNotFoundException extends RuntimeException{
    public PokemonNotFoundException(String name){
        super("pokemon '%s' not found".formatted(name));
    }
}
