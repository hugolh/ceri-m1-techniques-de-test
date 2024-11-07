package com.pokemon;

public class PokemonTrainerFactory implements IPokemonTrainerFactory {

    @Override
    public PokemonTrainer createTrainer(String name, Team team, IPokedexFactory pokedexFactory) {
        if (name == null || team == null || pokedexFactory == null) {
            throw new NullPointerException("Name, team, and pokedexFactory cannot be null");
        }
        
        IPokedex pokedex = pokedexFactory.createPokedex(new PokemonMetadataProvider(), new PokemonFactory());
        
        return new PokemonTrainer(name, team, pokedex);
    }
}
