package com.pokemon;

public class PokemonFactory implements IPokemonFactory {
    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        return new Pokemon(index, "PokemonName", 100, 100, 100, cp, hp, dust, candy, 50);
    }
}
