package com.pokemon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokemonMetadataProviderTest {

    private IPokemonMetadataProvider metadataProvider;
    private PokemonMetadata mockMetadata;

    @BeforeEach
    public void setUp() {
        metadataProvider = mock(IPokemonMetadataProvider.class);

        mockMetadata = new PokemonMetadata(1, "Bulbasaur", 126, 126, 90);

        try {
            when(metadataProvider.getPokemonMetadata(1)).thenReturn(mockMetadata);
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
}
