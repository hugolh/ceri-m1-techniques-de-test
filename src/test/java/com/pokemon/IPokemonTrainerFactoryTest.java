package com.pokemon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test for IPokemonTrainerFactory implementation.
 */
public class IPokemonTrainerFactoryTest {

    private IPokedexFactory pokedexFactoryMock;
    private IPokedex pokedexMock;
    private IPokemonTrainerFactory pokemonTrainerFactoryMock;
    private PokemonTrainerFactory realPokemonTrainerFactory; 

    @BeforeEach
    public void setUp() {
        pokedexFactoryMock = mock(IPokedexFactory.class);
        pokedexMock = mock(IPokedex.class);

        pokemonTrainerFactoryMock = mock(IPokemonTrainerFactory.class);
        realPokemonTrainerFactory = new PokemonTrainerFactory();

        when(pokedexFactoryMock.createPokedex(any(), any())).thenReturn(pokedexMock);

        PokemonTrainer mockTrainer = new PokemonTrainer("Ash", Team.MYSTIC, pokedexMock);
        when(pokemonTrainerFactoryMock.createTrainer("Ash", Team.MYSTIC, pokedexFactoryMock)).thenReturn(mockTrainer);
    }

    @Test
    public void testCreateTrainerWithMockFactory() {
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
    public void testCreateTrainerWithNullNameInMockFactory() {
        doThrow(new NullPointerException()).when(pokemonTrainerFactoryMock).createTrainer(null, Team.MYSTIC, pokedexFactoryMock);

        assertThrows(NullPointerException.class, () -> {
            pokemonTrainerFactoryMock.createTrainer(null, Team.MYSTIC, pokedexFactoryMock);
        }, "Expected createTrainer to throw, but it didn't");

        verify(pokemonTrainerFactoryMock).createTrainer(null, Team.MYSTIC, pokedexFactoryMock);
    }

    @Test
    public void testCreateTrainerWithNullTeamInMockFactory() {
        doThrow(new NullPointerException()).when(pokemonTrainerFactoryMock).createTrainer("Ash", null, pokedexFactoryMock);

        assertThrows(NullPointerException.class, () -> {
            pokemonTrainerFactoryMock.createTrainer("Ash", null, pokedexFactoryMock);
        }, "Expected createTrainer to throw, but it didn't");

        verify(pokemonTrainerFactoryMock).createTrainer("Ash", null, pokedexFactoryMock);
    }

    @Test
    public void testCreateTrainerWithNullPokedexFactoryInMockFactory() {
        doThrow(new NullPointerException()).when(pokemonTrainerFactoryMock).createTrainer("Ash", Team.MYSTIC, null);

        assertThrows(NullPointerException.class, () -> {
            pokemonTrainerFactoryMock.createTrainer("Ash", Team.MYSTIC, null);
        }, "Expected createTrainer to throw, but it didn't");

        verify(pokemonTrainerFactoryMock).createTrainer("Ash", Team.MYSTIC, null);
    }

    @Test
    public void testCreateTrainerWithRealFactory() {
        String trainerName = "Ash";
        Team trainerTeam = Team.MYSTIC;

        PokemonTrainer trainer = realPokemonTrainerFactory.createTrainer(trainerName, trainerTeam, pokedexFactoryMock);

        assertNotNull(trainer, "Trainer should not be null");
        assertEquals(trainerName, trainer.getName(), "Trainer's name should match the input name");
        assertEquals(trainerTeam, trainer.getTeam(), "Trainer's team should match the input team");
        assertEquals(pokedexMock, trainer.getPokedex(), "Trainer's pokedex should be the one returned by the factory");
    }

    @Test
    public void testCreateTrainerWithNullNameInRealFactory() {
        assertThrows(NullPointerException.class, () -> {
            realPokemonTrainerFactory.createTrainer(null, Team.MYSTIC, pokedexFactoryMock);
        }, "Expected createTrainer to throw NullPointerException when name is null");
    }

    @Test
    public void testCreateTrainerWithNullTeamInRealFactory() {
        assertThrows(NullPointerException.class, () -> {
            realPokemonTrainerFactory.createTrainer("Ash", null, pokedexFactoryMock);
        }, "Expected createTrainer to throw NullPointerException when team is null");
    }

    @Test
    public void testCreateTrainerWithNullPokedexFactoryInRealFactory() {
        assertThrows(NullPointerException.class, () -> {
            realPokemonTrainerFactory.createTrainer("Ash", Team.MYSTIC, null);
        }, "Expected createTrainer to throw NullPointerException when pokedexFactory is null");
    }
}
