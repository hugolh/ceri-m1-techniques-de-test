package com.pokemon;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit test for IPokedex implementation.
 */
public class IPokedexTest {

    private IPokedex pokedexMock;
    private Pokedex pokedex;
    private Pokemon mockPokemon1;
    private Pokemon mockPokemon2;
    private IPokemonMetadataProvider metadataProviderMock;
    private IPokemonFactory pokemonFactoryMock;

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

        metadataProviderMock = mock(IPokemonMetadataProvider.class);
        pokemonFactoryMock = mock(IPokemonFactory.class);
        pokedex = new Pokedex(metadataProviderMock, pokemonFactoryMock);
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
        assertEquals("Bulbizarre", pokemon.getName(), "Expected Pokemon name should be Bulbizarre");

        pokemon = pokedexMock.getPokemon(1);
        assertNotNull(pokemon, "Pokemon should not be null");
        assertEquals("Aquali", pokemon.getName(), "Expected Pokemon name should be Aquali");
    }

    @Test
    public void testGetPokemons() {
        List<Pokemon> pokemons = pokedexMock.getPokemons();
        assertNotNull(pokemons, "Pokemons list should not be null");
        assertEquals(2, pokemons.size(), "Pokedex should contain two Pokemon");
        assertEquals("Bulbizarre", pokemons.get(0).getName(), "First Pokemon should be Bulbizarre");
        assertEquals("Aquali", pokemons.get(1).getName(), "Second Pokemon should be Aquali");
    }

    @Test
    public void testGetPokemonsSorted() {
        List<Pokemon> sortedPokemons = pokedexMock.getPokemons(Comparator.comparing(Pokemon::getName));
        assertNotNull(sortedPokemons, "Sorted Pokemons list should not be null");
        assertEquals(2, sortedPokemons.size(), "Pokedex should still contain two Pokemon when sorted");
        assertEquals("Aquali", sortedPokemons.get(0).getName(), "First Pokemon should be Aquali when sorted by name");
        assertEquals("Bulbizarre", sortedPokemons.get(1).getName(), "Second Pokemon should be Bulbizarre when sorted by name");
    }

    @Test
    public void testGetPokemonsEmpty() {
        when(pokedexMock.getPokemons()).thenReturn(new ArrayList<>());

        List<Pokemon> pokemons = pokedexMock.getPokemons();
        assertNotNull(pokemons, "Pokemons list should not be null");
        assertTrue(pokemons.isEmpty(), "Pokemons list should be empty");
    }

    @Test
    public void testAddMultiplePokemon() {
        try {
            List<Pokemon> pokemonList = new ArrayList<>();
            pokemonList.add(mockPokemon1);
            pokemonList.add(mockPokemon2);

            when(pokedexMock.getPokemons()).thenReturn(pokemonList);

            assertEquals(2, pokedexMock.size(), "Pokedex should contain two Pokemon");
            assertEquals(mockPokemon1, pokedexMock.getPokemon(0), "First Pokemon should be Bulbizarre");
            assertEquals(mockPokemon2, pokedexMock.getPokemon(1), "Second Pokemon should be Aquali");
        } catch (PokedexException e) {
            e.printStackTrace();
            fail("PokedexException should not be thrown during this test.");
        }
    }

    @Test
    public void testGetPokemonsSortedByIndex() {
        List<Pokemon> sortedPokemons = pokedexMock.getPokemons(Comparator.comparingInt(Pokemon::getIndex));
        assertNotNull(sortedPokemons, "Sorted Pokemons list should not be null");
        assertEquals(2, sortedPokemons.size(), "Pokedex should still contain two Pokemon when sorted by index");
        assertEquals(0, sortedPokemons.get(0).getIndex(), "First Pokemon should have index 0 when sorted by index");
        assertEquals(133, sortedPokemons.get(1).getIndex(), "Second Pokemon should have index 133 when sorted by index");
    }

    @Test
    public void testGetPokemonsSortedByAttack() {
        List<Pokemon> sortedPokemons = pokedexMock.getPokemons(Comparator.comparingInt(Pokemon::getAttack));
        assertNotNull(sortedPokemons, "Sorted Pokemons list should not be null");
        assertEquals(2, sortedPokemons.size(), "Pokedex should still contain two Pokemon when sorted by attack");
        assertEquals(126, sortedPokemons.get(0).getAttack(), "First Pokemon should have attack 126 when sorted by attack");
        assertEquals(186, sortedPokemons.get(1).getAttack(), "Second Pokemon should have attack 186 when sorted by attack");
    }

    @Test
    public void testPokedexSize() {
        assertEquals(0, pokedex.size(), "New Pokedex should be empty");
        pokedex.addPokemon(mockPokemon1);
        assertEquals(1, pokedex.size(), "Pokedex size should be 1 after adding a Pokemon");
        pokedex.addPokemon(mockPokemon2);
        assertEquals(2, pokedex.size(), "Pokedex size should be 2 after adding another Pokemon");
    }

    @Test
    public void testPokedexAddPokemon() throws PokedexException {
        pokedex.addPokemon(mockPokemon1);
        assertEquals(mockPokemon1, pokedex.getPokemon(0), "First Pokemon should be Bulbizarre");
        pokedex.addPokemon(mockPokemon2);
        assertEquals(mockPokemon2, pokedex.getPokemon(1), "Second Pokemon should be Aquali");
    }


    @Test
    public void testPokedexGetPokemon() throws PokedexException {
        pokedex.addPokemon(mockPokemon1);
        pokedex.addPokemon(mockPokemon2);

        Pokemon pokemon = pokedex.getPokemon(0);
        assertNotNull(pokemon, "Pokemon should not be null");
        assertEquals("Bulbizarre", pokemon.getName(), "Expected Pokemon name should be Bulbizarre");

        pokemon = pokedex.getPokemon(1);
        assertNotNull(pokemon, "Pokemon should not be null");
        assertEquals("Aquali", pokemon.getName(), "Expected Pokemon name should be Aquali");
    }

    @Test
    public void testPokedexGetPokemons() {
        pokedex.addPokemon(mockPokemon1);
        pokedex.addPokemon(mockPokemon2);

        List<Pokemon> pokemons = pokedex.getPokemons();
        assertNotNull(pokemons, "Pokemons list should not be null");
        assertEquals(2, pokemons.size(), "Pokedex should contain two Pokemon");
        assertEquals("Bulbizarre", pokemons.get(0).getName(), "First Pokemon should be Bulbizarre");
        assertEquals("Aquali", pokemons.get(1).getName(), "Second Pokemon should be Aquali");
    }

    @Test
    public void testPokedexGetPokemonsSortedByName() {
        pokedex.addPokemon(mockPokemon1);
        pokedex.addPokemon(mockPokemon2);

        List<Pokemon> sortedPokemons = pokedex.getPokemons(Comparator.comparing(Pokemon::getName));
        assertNotNull(sortedPokemons, "Sorted Pokemons list should not be null");
        assertEquals(2, sortedPokemons.size(), "Pokedex should still contain two Pokemon when sorted");
        assertEquals("Aquali", sortedPokemons.get(0).getName(), "First Pokemon should be Aquali when sorted by name");
        assertEquals("Bulbizarre", sortedPokemons.get(1).getName(), "Second Pokemon should be Bulbizarre when sorted by name");
    }
}
