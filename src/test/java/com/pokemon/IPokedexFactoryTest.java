package com.pokemon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test for IPokedexFactory implementation.
 */
public class IPokedexFactoryTest {

    private IPokedexFactory pokedexFactory; 
    private IPokemonMetadataProvider metadataProviderMock;
    private IPokemonFactory pokemonFactoryMock;

    @BeforeEach
    public void setUp() {
        pokedexFactory = new PokedexFactory();
        metadataProviderMock = mock(IPokemonMetadataProvider.class);
        pokemonFactoryMock = mock(IPokemonFactory.class);
    }

    @Test
    public void testCreatePokedex() {
        IPokedex createdPokedex = pokedexFactory.createPokedex(metadataProviderMock, pokemonFactoryMock);

        assertNotNull(createdPokedex, "Pokedex should not be null");
        assertTrue(createdPokedex instanceof Pokedex, "Created Pokedex should be an instance of Pokedex");

        Pokedex pokedex = (Pokedex) createdPokedex;
        assertEquals(metadataProviderMock, pokedex.getMetadataProvider(), "Pokedex should have the correct metadata provider");
        assertEquals(pokemonFactoryMock, pokedex.getPokemonFactory(), "Pokedex should have the correct Pokemon factory");
    }

    @Test
    public void testCreatePokedexWithMockedReturn() {
        IPokedexFactory mockedPokedexFactory = mock(IPokedexFactory.class);
        IPokedex pokedexMock = mock(IPokedex.class);

        when(mockedPokedexFactory.createPokedex(any(IPokemonMetadataProvider.class), any(IPokemonFactory.class)))
                .thenReturn(pokedexMock);

        IPokedex createdPokedex = mockedPokedexFactory.createPokedex(metadataProviderMock, pokemonFactoryMock);

        assertEquals(pokedexMock, createdPokedex, "Created Pokedex should match the mocked Pokedex");

        verify(mockedPokedexFactory).createPokedex(metadataProviderMock, pokemonFactoryMock);
    }

    @Test
    public void testPokedexFactoryCreatesCorrectPokedex() {
        IPokedex createdPokedex = pokedexFactory.createPokedex(metadataProviderMock, pokemonFactoryMock);

        assertNotNull(createdPokedex, "Created Pokedex should not be null");

        Pokedex pokedex = (Pokedex) createdPokedex;
        assertEquals(metadataProviderMock, pokedex.getMetadataProvider(), "Pokedex should have the correct metadata provider");
        assertEquals(pokemonFactoryMock, pokedex.getPokemonFactory(), "Pokedex should have the correct Pokemon factory");
    }
}
