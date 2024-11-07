package com.pokemon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IPokemonFactoryTest {

    private IPokemonFactory mockedPokemonFactory;
    private Pokemon mockPokemon;
    private PokemonFactory realPokemonFactory;

    @BeforeEach
    public void setUp() {
        mockedPokemonFactory = mock(IPokemonFactory.class);
        mockPokemon = new Pokemon(1, "Bulbasaur", 126, 126, 90, 613, 64, 4000, 4, 56);
        
        when(mockedPokemonFactory.createPokemon(1, 613, 64, 4000, 4)).thenReturn(mockPokemon);

        realPokemonFactory = new PokemonFactory();
    }

    @Test
    public void testCreatePokemonWithMock() {
        Pokemon createdPokemon = mockedPokemonFactory.createPokemon(1, 613, 64, 4000, 4);

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

        verify(mockedPokemonFactory).createPokemon(1, 613, 64, 4000, 4);
    }

    @Test
    public void testCreatePokemonWithDifferentValuesMock() {
        when(mockedPokemonFactory.createPokemon(4, 831, 78, 5000, 5)).thenReturn(new Pokemon(4, "Charmander", 128, 108, 78, 831, 78, 5000, 5, 58));

        Pokemon createdPokemon = mockedPokemonFactory.createPokemon(4, 831, 78, 5000, 5);

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

        verify(mockedPokemonFactory).createPokemon(4, 831, 78, 5000, 5);
    }

    @Test
    public void testCreatePokemonWithRealFactory() {
        Pokemon createdPokemon = realPokemonFactory.createPokemon(1, 613, 64, 4000, 4);

        assertNotNull(createdPokemon);
        assertEquals(1, createdPokemon.getIndex());
        assertEquals("PokemonName", createdPokemon.getName());
        assertEquals(100, createdPokemon.getAttack());
        assertEquals(100, createdPokemon.getDefense());
        assertEquals(100, createdPokemon.getStamina());
        assertEquals(613, createdPokemon.getCp());
        assertEquals(64, createdPokemon.getHp());
        assertEquals(4000, createdPokemon.getDust());
        assertEquals(4, createdPokemon.getCandy());
        assertEquals(50, createdPokemon.getIv(), "IV should match the default set in PokemonFactory");
    }

    @Test
    public void testCreatePokemonWithDifferentValuesRealFactory() {
        Pokemon createdPokemon = realPokemonFactory.createPokemon(4, 831, 78, 5000, 5);

        assertNotNull(createdPokemon);
        assertEquals(4, createdPokemon.getIndex());
        assertEquals("PokemonName", createdPokemon.getName());
        assertEquals(100, createdPokemon.getAttack());
        assertEquals(100, createdPokemon.getDefense());
        assertEquals(100, createdPokemon.getStamina());
        assertEquals(831, createdPokemon.getCp());
        assertEquals(78, createdPokemon.getHp());
        assertEquals(5000, createdPokemon.getDust());
        assertEquals(5, createdPokemon.getCandy());
        assertEquals(50, createdPokemon.getIv(), "IV should match the default set in PokemonFactory");
    }
}
