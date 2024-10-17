package com.pokemon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokemonMetadataProviderTest {

    private IPokemonMetadataProvider metadataProvider;
    private PokemonMetadata mockMetadata1;
    private PokemonMetadata mockMetadata2;

    @BeforeEach
    public void setUp() {
        metadataProvider = mock(IPokemonMetadataProvider.class);

        mockMetadata1 = new PokemonMetadata(1, "Bulbasaur", 126, 126, 90);
        mockMetadata2 = new PokemonMetadata(4, "Charmander", 128, 108, 78);

        try {
            when(metadataProvider.getPokemonMetadata(1)).thenReturn(mockMetadata1);
            when(metadataProvider.getPokemonMetadata(4)).thenReturn(mockMetadata2);
        } catch (PokedexException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetPokemonMetadataSuccess() throws PokedexException {
        PokemonMetadata metadata = metadataProvider.getPokemonMetadata(1);

        assertNotNull(metadata);
        assertEquals(1, metadata.getIndex());
        assertEquals("Bulbasaur", metadata.getName());
        assertEquals(126, metadata.getAttack());
        assertEquals(126, metadata.getDefense());
        assertEquals(90, metadata.getStamina());

        verify(metadataProvider).getPokemonMetadata(1);
    }

    @Test
    public void testGetPokemonMetadataInvalidIndex() throws PokedexException {
        when(metadataProvider.getPokemonMetadata(-1)).thenThrow(new PokedexException("Invalid Pokemon index"));

        PokedexException exception = assertThrows(PokedexException.class, () -> {
            metadataProvider.getPokemonMetadata(-1);
        });

        assertEquals("Invalid Pokemon index", exception.getMessage());

        verify(metadataProvider).getPokemonMetadata(-1);
    }

    @Test
    public void testGetPokemonMetadataMultipleValidIndices() throws PokedexException {
        PokemonMetadata metadata1 = metadataProvider.getPokemonMetadata(1);
        PokemonMetadata metadata2 = metadataProvider.getPokemonMetadata(4);

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

        verify(metadataProvider).getPokemonMetadata(1);
        verify(metadataProvider).getPokemonMetadata(4);
    }
}