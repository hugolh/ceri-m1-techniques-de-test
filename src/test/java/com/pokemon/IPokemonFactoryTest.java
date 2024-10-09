package com.pokemon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokemonFactoryTest {

    private IPokemonFactory pokemonFactory;
    private Pokemon mockPokemon;

    @BeforeEach
    public void setUp() {
        pokemonFactory = mock(IPokemonFactory.class);
        pokemonFactory = mock(IPokemonFactory.class);
        mockPokemon = new Pokemon(1, "Bulbasaur", 126, 126, 90, 613, 64, 4000, 4, 56);

        when(pokemonFactory.createPokemon(1, 613, 64, 4000, 4)).thenReturn(mockPokemon);
    }

    @Test
    public void testCreatePokemon() {
        Pokemon createdPokemon = pokemonFactory.createPokemon(1, 613, 64, 4000, 4);

        assertNotNull(createdPokemon);
        assertEquals(1, createdPokemon.getIndex());
        assertEquals("Bulbasaur", createdPokemon.getName());
        assertEquals(126, createdPokemon.getAttack());
        assertEquals(126, createdPokemon.getDefense());
        assertEquals(90, createdPokemon.getStamina());
        assertEquals(613, createdPokemon.getCp());
        assertEquals(64, createdPokemon.getHp());
        assertEquals(4000, createdPokemon.getDust());
        assertEquals(4, createdPokemon.getCandy());

        verify(pokemonFactory).createPokemon(1, 613, 64, 4000, 4);
    }

    @Test
    public void testCreatePokemonWithDifferentValues() {
        when(pokemonFactory.createPokemon(4, 831, 78, 5000, 5)).thenReturn(new Pokemon(4, "Charmander", 128, 108, 78, 831, 78, 5000, 5, 58));

        Pokemon createdPokemon = pokemonFactory.createPokemon(4, 831, 78, 5000, 5);

        assertNotNull(createdPokemon);
        assertEquals(4, createdPokemon.getIndex());
        assertEquals("Charmander", createdPokemon.getName());
        assertEquals(128, createdPokemon.getAttack());
        assertEquals(108, createdPokemon.getDefense());
        assertEquals(78, createdPokemon.getStamina());
        assertEquals(831, createdPokemon.getCp());
        assertEquals(78, createdPokemon.getHp());
        assertEquals(5000, createdPokemon.getDust());
        assertEquals(5, createdPokemon.getCandy());

        verify(pokemonFactory).createPokemon(4, 831, 78, 5000, 5);
    }
}
