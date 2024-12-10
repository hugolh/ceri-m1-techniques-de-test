package com.pokemon;

public class PokemonFactory implements IPokemonFactory {

    private final IPokemonMetadataProvider metadataProvider;

    /**
     * Constructor that takes a metadata provider.
     *
     * @param metadataProvider Provider of Pokemon metadata.
     */
    public PokemonFactory(IPokemonMetadataProvider metadataProvider) {
        this.metadataProvider = metadataProvider;
    }

    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        try {
            PokemonMetadata metadata = metadataProvider.getPokemonMetadata(index);

            int maxStats = metadata.getAttack() + metadata.getDefense() + metadata.getStamina();
            double ivPercentage = ((15.0 + 15.0 + 15.0) / maxStats) * 100;

            return new Pokemon(
                index,
                metadata.getName(),
                metadata.getAttack(),
                metadata.getDefense(),
                metadata.getStamina(),
                cp,
                hp,
                dust,
                candy,
                ivPercentage
            );
        } catch (PokedexException e) {
            throw new IllegalArgumentException("Invalid Pokemon index: " + index, e);
        }
    }
}
