# Crossword Forge

Solveur de mots croisés en Java — prend en entrée un dictionnaire et une grille vierge, renvoie une grille résolue où tous les mots horizontaux et verticaux sont des mots valides.

Développé autour d'un algorithme de **satisfaction de contraintes** appliqué aux intersections entre emplacements de mots.

## Problème résolu

Étant donné :
- une grille rectangulaire avec des cases **pleines** (noires) et **vides** (blanches)
- un **dictionnaire** de mots valides

Trouver un remplissage de toutes les cases vides tel que :
- chaque séquence horizontale ou verticale d'au moins 2 cases forme un mot du dictionnaire
- les contraintes d'intersection entre mots horizontaux et verticaux sont respectées

## Approche

### 1. Représentation de la grille
Modélisation orientée objet avec trois classes principales :
- **`Case`** — une cellule de la grille (coordonnées + caractère)
- **`Grille`** — matrice de cases avec copie profonde
- **`Emplacement`** — séquence contiguë de cases formant un mot (horizontal ou vertical)

### 2. Détection des emplacements
Parcours de la grille pour identifier tous les emplacements horizontaux et verticaux de longueur ≥ 2.

### 3. Dictionnaires filtrés par emplacement
À chaque emplacement, on associe initialement l'ensemble des mots du dictionnaire ayant la bonne longueur. Ce sous-ensemble est ensuite affiné par élimination.

### 4. Résolution par satisfaction de contraintes
- Filtrage des candidats selon les lettres déjà posées et les intersections avec d'autres mots
- Backtracking sur les choix de mots
- Test de vraisemblance à chaque étape (il reste au moins un candidat pour chaque emplacement incomplet)

## Structure

```
src/pobj/motx/
├── tme1/              # Structure de données
│   ├── Case.java
│   ├── Grille.java
│   ├── Emplacement.java
│   └── GrilleLoader.java         # Chargement / sauvegarde format .grl
├── tme2/              # Détection des emplacements + dictionnaires
├── tme3/              # Résolution par satisfaction de contraintes
└── test/              # Tests JUnit 5

data/
├── frgut.txt                      # Dictionnaire français (sans accents)
└── *.grl                          # Grilles de test (vides ou partielles)
```

## Format des grilles (`.grl`)

Fichier texte où chaque ligne représente une ligne de la grille :
- `*` — case pleine (noire)
- (espace) — case vide
- lettre minuscule — case préremplie

## Exécution

### Prérequis
- Java 17+
- JUnit 5

### Compilation et lancement
Importer le projet dans Eclipse puis lancer la classe principale avec *Run As > Java Application*.

Les tests se lancent avec *Run As > JUnit Test* sur la classe `TME*Tests` correspondante.

## Auteurs

- Hani Slimani
- [@akkaboutaina](https://github.com/akkaboutaina)
