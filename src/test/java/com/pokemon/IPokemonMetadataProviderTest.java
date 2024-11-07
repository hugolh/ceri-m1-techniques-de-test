package com.pokemon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IPokemonMetadataProviderTest {

    private IPokemonMetadataProvider mockedMetadataProvider;
    private PokemonMetadataProvider realMetadataProvider;
    private PokemonMetadata mockMetadata1;
    private PokemonMetadata mockMetadata2;

    @BeforeEach
    public void setUp() {
        mockedMetadataProvider = mock(IPokemonMetadataProvider.class);

        mockMetadata1 = new PokemonMetadata(1, "Bulbasaur", 126, 126, 90);
        mockMetadata2 = new PokemonMetadata(4, "Charmander", 128, 108, 78);

        try {
            when(mockedMetadataProvider.getPokemonMetadata(1)).thenReturn(mockMetadata1);
            when(mockedMetadataProvider.getPokemonMetadata(4)).thenReturn(mockMetadata2);
        } catch (PokedexException e) {
            e.printStackTrace();
        }

        realMetadataProvider = new PokemonMetadataProvider();
        realMetadataProvider.metadataMap.put(1, mockMetadata1);
        realMetadataProvider.metadataMap.put(4, mockMetadata2);
    }

    @Test
    public void testGetPokemonMetadataSuccessWithMock() throws PokedexException {
        PokemonMetadata metadata = mockedMetadataProvider.getPokemonMetadata(1);

        assertNotNull(metadata);
        assertEquals(1, metadata.getIndex());
        assertEquals("Bulbasaur", metadata.getName());
        assertEquals(126, metadata.getAttack());
        assertEquals(126, metadata.getDefense());
        assertEquals(90, metadata.getStamina());

        verify(mockedMetadataProvider).getPokemonMetadata(1);
    }

    @Test
    public void testGetPokemonMetadataInvalidIndexWithMock() throws PokedexException {
        when(mockedMetadataProvider.getPokemonMetadata(-1)).thenThrow(new PokedexException("Invalid Pokemon index"));

        PokedexException exception = assertThrows(PokedexException.class, () -> {
            mockedMetadataProvider.getPokemonMetadata(-1);
        });

        assertEquals("Invalid Pokemon index", exception.getMessage());

        verify(mockedMetadataProvider).getPokemonMetadata(-1);
    }

    @Test
    public void testGetPokemonMetadataMultipleValidIndicesWithMock() throws PokedexException {
        PokemonMetadata metadata1 = mockedMetadataProvider.getPokemonMetadata(1);
        PokemonMetadata metadata2 = mockedMetadataProvider.getPokemonMetadata(4);

        assertNotNull(metadata1);
        assertEquals(1, metadata1.getIndex());
        assertEquals("Bulbasaur", metadata1.getName());
        assertEquals(126, metadata1.getAttack());
        assertEquals(126, metadata1.getDefense());
        assertEquals(90, metadata1.getStamina());

        assertNotNull(metadata2);
        assertEquals(4, metadata2.getIndex());
        assertEquals("Charmander", metadata2.getName());
        assertEquals(128, metadata2.getAttack());
        assertEquals(108, metadata2.getDefense());
        assertEquals(78, metadata2.getStamina());

        verify(mockedMetadataProvider).getPokemonMetadata(1);
        verify(mockedMetadataProvider).getPokemonMetadata(4);
    }

    @Test
    public void testGetPokemonMetadataSuccessWithRealProvider() throws PokedexException {
        PokemonMetadata metadata = realMetadataProvider.getPokemonMetadata(1);

        assertNotNull(metadata);
        assertEquals(1, metadata.getIndex());
        assertEquals("Bulbasaur", metadata.getName());
        assertEquals(126, metadata.getAttack());
        assertEquals(126, metadata.getDefense());
        assertEquals(90, metadata.getStamina());
    }

    @Test
    public void testGetPokemonMetadataInvalidIndexWithRealProvider() {
        PokedexException exception = assertThrows(PokedexException.class, () -> {
            realMetadataProvider.getPokemonMetadata(-1);
        });

        assertEquals("Invalid Pokemon index: -1", exception.getMessage());
    }

    @Test
    public void testGetPokemonMetadataMultipleValidIndicesWithRealProvider() throws PokedexException {
        PokemonMetadata metadata1 = realMetadataProvider.getPokemonMetadata(1);
        PokemonMetadata metadata2 = realMetadataProvider.getPokemonMetadata(4);

        assertNotNull(metadata1);
        assertEquals(1, metadata1.getIndex());
        assertEquals("Bulbasaur", metadata1.getName());
        assertEquals(126, metadata1.getAttack());
        assertEquals(126, metadata1.getDefense());
        assertEquals(90, metadata1.getStamina());

        assertNotNull(metadata2);
        assertEquals(4, metadata2.getIndex());
        assertEquals("Charmander", metadata2.getName());
        assertEquals(128, metadata2.getAttack());
        assertEquals(108, metadata2.getDefense());
        assertEquals(78, metadata2.getStamina());
    }
}
