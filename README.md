# UCE Génie Logiciel Avancé : Techniques de tests

## Hugo Le Hen

## Badges

### Circle badges
[![CircleCI](https://dl.circleci.com/status-badge/img/gh/hugolh/ceri-m1-techniques-de-test/tree/master.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/hugolh/ceri-m1-techniques-de-test/tree/master)

### Code cov
[![codecov](https://codecov.io/github/hugolh/ceri-m1-techniques-de-test/graph/badge.svg?token=P1IZQQA8N0)](https://codecov.io/github/hugolh/ceri-m1-techniques-de-test)


### Check style 
[![Checkstyle](https://img.shields.io/badge/Checkstyle-Passing-brightgreen)](https://www.example.com)





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



## TP6 Rocket Pokemon Factory

### Tests trigger : 

[ERROR]   IPokemonFactoryTest.testCreatePokemonWithDifferentValuesRealFactory:88 expected: <PokemonName> but was: <MISSINGNO>
[ERROR]   IPokemonFactoryTest.testCreatePokemonWithRealFactory:71 expected: <PokemonName> but was: <Bulbasaur>



### Tests ajouté : testCreatePokemonWithOverIndex, testCreatePokemonWithNegativeIndex, testNegativeAttack, testNegativeDefense, testNegativeStamina

### Problèmes de Performances :
Liste codée en dur (index2name) pour les noms des Pokémon.
Boucle inefficace sur 1 000 000 itérations pour générer une statistique aléatoire.
### Problèmes de Cohérence :
Les métadonnées peuvent être négatives pour certains indices.
La classe IPokemonMetadataProvider n'est pas utilisée pour récupérer les métadonnées des Pokémon.
Valeurs d'IV fixes (0 ou 1) non calculées correctement.
Les statistiques d'attaque, défense et stamina ne respectent pas les limites définies (0 à 15).
Le nom par défaut "MISSINGNO" est attribué pour des indices invalides, ce qui ne respecte pas le comportement attendu.
Les Pokémon avec un index négatif ont des statistiques irréalistes (1000).
La méthode generateRandomStat n'est pas utilisée de manière optimale, et son comportement est incohérent.
### Problèmes de Robustesse :
Pas de gestion des exceptions pour les cas où les métadonnées sont introuvables.
Dépendance implicite à index2name qui ne couvre pas tous les indices valides (de 0 à 150).
### Problèmes de Lisibilité :
Structure du code peu maintenable et non extensible (ex., la gestion des noms Pokémon est fixée dans un static block).
Utilisation de la méthode get avec un index par défaut ambigu (0) pour les Pokémon invalides.