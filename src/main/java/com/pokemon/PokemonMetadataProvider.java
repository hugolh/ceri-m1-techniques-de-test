package com.pokemon;

import java.util.HashMap;
import java.util.Map;

public class PokemonMetadataProvider implements IPokemonMetadataProvider {

    final Map<Integer, PokemonMetadata> metadataMap;

    public PokemonMetadataProvider() {
        metadataMap = new HashMap<>();
       }

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        PokemonMetadata metadata = metadataMap.get(index);
        if (metadata == null) {
            throw new PokedexException("Invalid Pokemon index: " + index);
        }
        return metadata;
    }
}