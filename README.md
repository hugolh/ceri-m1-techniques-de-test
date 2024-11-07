# UCE Génie Logiciel Avancé : Techniques de tests

## Hugo Le Hen

## Badges

Circle badges
[![CircleCI](https://dl.circleci.com/status-badge/img/gh/hugolh/ceri-m1-techniques-de-test/tree/master.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/hugolh/ceri-m1-techniques-de-test/tree/master)

Code cov
[![codecov](https://codecov.io/github/hugolh/ceri-m1-techniques-de-test/graph/badge.svg?token=P1IZQQA8N0)](https://codecov.io/github/hugolh/ceri-m1-techniques-de-test)






## Implémentation 

### PokedexFactory 
J'ai implémenté la classe `PokedexFactory` pour respecter l'interface `IPokedexFactory`, en créant une nouvelle instance de `Pokedex` avec les `IPokemonMetadataProvider` et `IPokemonFactory` fournis, permettant ainsi une gestion flexible des métadonnées et de la création des pokémons.


### Pokedex
J'ai implémenté la classe `Pokedex` en respectant l'interface `IPokedex` afin de centraliser la gestion des pokémons, y compris l'ajout, la récupération, et l'accès aux métadonnées. J'ai également intégré la possibilité de créer des pokémons via un `IPokemonFactory` et de trier les pokémons avec un comparateur, tout en assurant que la liste des pokémons soit immutable pour éviter toute modification externe.

### PokemonFactory
J'ai implémenté `PokemonFactory` en suivant l'interface `IPokemonFactory` pour créer un `Pokemon` avec les paramètres nécessaires, en calculant les IVs et en retournant une instance de `Pokemon`.


### PokemonMetadataProvider
J'ai implémenté PokemonMetadataProvider en utilisant une HashMap pour stocker les métadonnées des Pokémon et en respectant la méthode getPokemonMetadata de l'interface, en lançant une exception PokedexException si l'index est invalide.

### PokemonTrainerFactory
J'ai implémenté `PokemonTrainerFactory` en vérifiant que les paramètres ne soient pas nuls et en utilisant le `pokedexFactory` pour créer un `Pokedex`, respectant ainsi l'interface `IPokemonTrainerFactory`.