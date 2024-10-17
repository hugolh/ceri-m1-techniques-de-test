package com.pokemon;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Unit test for IPokedex implementation.
 *
 * @author fv
 */
public class IPokedexTest {

    private IPokedex pokedexMock;
    private Pokemon mockPokemon1;
    private Pokemon mockPokemon2;

    @BeforeEach
    public void setUp() {
        pokedexMock = mock(IPokedex.class);

        mockPokemon1 = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 0.56);
        mockPokemon2 = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 0.1);

        when(pokedexMock.size()).thenReturn(2);

        when(pokedexMock.addPokemon(mockPokemon1)).thenReturn(0);
        when(pokedexMock.addPokemon(mockPokemon2)).thenReturn(1);

        try {
            when(pokedexMock.getPokemon(0)).thenReturn(mockPokemon1);
            when(pokedexMock.getPokemon(1)).thenReturn(mockPokemon2);
        } catch (PokedexException e) {
            e.printStackTrace();
        }
        List<Pokemon> pokemonList = new ArrayList<>();
        pokemonList.add(mockPokemon1);
        pokemonList.add(mockPokemon2);
        when(pokedexMock.getPokemons()).thenReturn(pokemonList);

        when(pokedexMock.getPokemons(any(Comparator.class))).thenAnswer(invocation -> {
            Comparator<Pokemon> comparator = invocation.getArgument(0);
            List<Pokemon> sortedList = new ArrayList<>(pokemonList);
            sortedList.sort(comparator);
            return sortedList;
        });
    }

    @Test
    public void testGetPokemonThrowsException() throws PokedexException {
        when(pokedexMock.getPokemon(99)).thenThrow(new PokedexException("Invalid ID"));

        assertThrows(PokedexException.class, () -> {
            pokedexMock.getPokemon(99);
        }, "PokedexException should be thrown for an invalid ID");
    }

    @Test
    public void testSize() {
        assertEquals(2, pokedexMock.size(), "Size should return the number of Pokemon in the Pokedex");
    }

    @Test
    public void testAddPokemon() {
        int index1 = pokedexMock.addPokemon(mockPokemon1);
        int index2 = pokedexMock.addPokemon(mockPokemon2);

        assertEquals(0, index1, "Index of the first Pokemon should be 0");
        assertEquals(1, index2, "Index of the second Pokemon should be 1");
    }

    @Test
    public void testGetPokemon() throws PokedexException {
        Pokemon pokemon = pokedexMock.getPokemon(0);
        assertNotNull(pokemon, "Pokemon should not be null");
        assertEquals("Bulbizarre", pokemon.getName(), "Expected Pokemon name should be Pikachu");

        pokemon = pokedexMock.getPokemon(1);
        assertNotNull(pokemon, "Pokemon should not be null");
        assertEquals("Aquali", pokemon.getName(), "Expected Pokemon name should be Bulbasaur");
    }

    @Test
    public void testGetPokemons() {
        List<Pokemon> pokemons = pokedexMock.getPokemons();
        assertNotNull(pokemons, "Pokemons list should not be null");
        assertEquals(2, pokemons.size(), "Pokedex should contain two Pokemon");
        assertEquals("Bulbizarre", pokemons.get(0).getName(), "First Pokemon should be Pikachu");
        assertEquals("Aquali", pokemons.get(1).getName(), "Second Pokemon should be Bulbasaur");
    }

    @Test
    public void testGetPokemonsSorted() {
        List<Pokemon> sortedPokemons = pokedexMock.getPokemons(Comparator.comparing(Pokemon::getName));
        assertNotNull(sortedPokemons, "Sorted Pokemons list should not be null");
        assertEquals(2, sortedPokemons.size(), "Pokedex should still contain two Pokemon when sorted");
        assertEquals("Aquali", sortedPokemons.get(0).getName(), "First Pokemon should be Bulbasaur when sorted by name");
        assertEquals("Bulbizarre", sortedPokemons.get(1).getName(), "Second Pokemon should be Pikachu when sorted by name");
    }
}
