package com.pokemon;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test for IPokemonTrainerFactory implementation.
 */
public class IPokemonTrainerFactoryTest {

    private IPokedexFactory pokedexFactoryMock;
    private IPokedex pokedexMock;
    private IPokemonMetadataProvider metadataProviderMock;
    private IPokemonFactory pokemonFactoryMock;
    private IPokemonTrainerFactory pokemonTrainerFactoryMock;

    @BeforeEach
    public void setUp() {
        pokedexFactoryMock = mock(IPokedexFactory.class);
        pokedexMock = mock(IPokedex.class);
        pokemonFactoryMock = mock(IPokemonFactory.class);
        metadataProviderMock = mock(IPokemonMetadataProvider.class);

        pokemonTrainerFactoryMock = mock(IPokemonTrainerFactory.class);

        when(pokedexFactoryMock.createPokedex(any(), any())).thenReturn(pokedexMock);

        PokemonTrainer mockTrainer = new PokemonTrainer("Ash", Team.MYSTIC, pokedexMock);

        when(pokemonTrainerFactoryMock.createTrainer("Ash", Team.MYSTIC, pokedexFactoryMock)).thenReturn(mockTrainer);
    }

    @Test
    public void testCreateTrainer() {
        String trainerName = "Ash";
        Team trainerTeam = Team.MYSTIC;

        PokemonTrainer trainer = pokemonTrainerFactoryMock.createTrainer(trainerName, trainerTeam, pokedexFactoryMock);

        assertNotNull(trainer, "Trainer should not be null");
        assertEquals(trainerName, trainer.getName(), "Trainer's name should match the input name");
        assertEquals(trainerTeam, trainer.getTeam(), "Trainer's team should match the input team");
        assertEquals(pokedexMock, trainer.getPokedex(), "Trainer's pokedex should be the one returned by the factory");

        verify(pokemonTrainerFactoryMock).createTrainer(trainerName, trainerTeam, pokedexFactoryMock);
    }

    @Test
    public void testCreateTrainerWithNullName() {
        doThrow(new NullPointerException()).when(pokemonTrainerFactoryMock).createTrainer(null, Team.MYSTIC, pokedexFactoryMock);

        assertThrows(NullPointerException.class, () -> {
            pokemonTrainerFactoryMock.createTrainer(null, Team.MYSTIC, pokedexFactoryMock);
        }, "Expected createTrainer to throw, but it didn't");

        verify(pokemonTrainerFactoryMock).createTrainer(null, Team.MYSTIC, pokedexFactoryMock);
    }

    @Test
    public void testCreateTrainerWithNullTeam() {
        doThrow(new NullPointerException()).when(pokemonTrainerFactoryMock).createTrainer("Ash", null, pokedexFactoryMock);

        assertThrows(NullPointerException.class, () -> {
            pokemonTrainerFactoryMock.createTrainer("Ash", null, pokedexFactoryMock);
        }, "Expected createTrainer to throw, but it didn't");

        verify(pokemonTrainerFactoryMock).createTrainer("Ash", null, pokedexFactoryMock);
    }

    @Test
    public void testCreateTrainerWithNullPokedexFactory() {
        doThrow(new NullPointerException()).when(pokemonTrainerFactoryMock).createTrainer("Ash", Team.MYSTIC, null);

        assertThrows(NullPointerException.class, () -> {
            pokemonTrainerFactoryMock.createTrainer("Ash", Team.MYSTIC, null);
        }, "Expected createTrainer to throw, but it didn't");

        verify(pokemonTrainerFactoryMock).createTrainer("Ash", Team.MYSTIC, null);
    }
}