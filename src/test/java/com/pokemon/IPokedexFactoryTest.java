package com.pokemon;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test for IPokedexFactory implementation.
 */
public class IPokedexFactoryTest {

    private IPokedexFactory pokedexFactoryMock;
    private IPokedex pokedexMock;
    private IPokemonMetadataProvider metadataProviderMock;
    private IPokemonFactory pokemonFactoryMock;

    @BeforeEach
    public void setUp() {
        pokedexFactoryMock = mock(IPokedexFactory.class);
        pokedexMock = mock(IPokedex.class);
        metadataProviderMock = mock(IPokemonMetadataProvider.class);
        pokemonFactoryMock = mock(IPokemonFactory.class);
        when(pokedexFactoryMock.createPokedex(any(IPokemonMetadataProvider.class), any(IPokemonFactory.class)))
                .thenReturn(pokedexMock);
    }

    @Test
    public void testCreatePokedex() {
        IPokedex createdPokedex = pokedexFactoryMock.createPokedex(metadataProviderMock, pokemonFactoryMock);

        assertNotNull(createdPokedex, "Pokedex should not be null");
        assertEquals(pokedexMock, createdPokedex, "Created Pokedex should match the mocked Pokedex");

        verify(pokedexFactoryMock).createPokedex(metadataProviderMock, pokemonFactoryMock);
    }
}