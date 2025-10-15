package pobj.motx.tme1;

/**
 * Représente une grille de mots croisés composée d'un tableau de {@link Case}.
 * Chaque case contient un caractère (lettre, vide ou mur) et possède une position
 * donnée par ses coordonnées (ligne, colonne).
 */
public class Grille {

    /** Tableau 2D représentant les cases de la grille */
    private Case[][] cases;

    /**
     * Construit une nouvelle grille vide avec les dimensions spécifiées.
     * Chaque case est initialisée avec le caractère ' ' (espace).
     *
     * @param hauteur le nombre de lignes de la grille
     * @param largeur le nombre de colonnes de la grille
     */
    public Grille(int hauteur, int largeur) {
        cases = new Case[hauteur][largeur];
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                cases[i][j] = new Case(i, j, ' ');
            }
        }
    }

    /**
     * Renvoie la case située à la position donnée.
     *
     * @param lig la ligne de la case
     * @param col la colonne de la case
     * @return la case correspondante
     */
    public Case getCase(int lig, int col) {
        return cases[lig][col];
    }

    /**
     * Renvoie le nombre de lignes de la grille.
     *
     * @return le nombre de lignes
     */
    public int nbLig() {
        return cases.length;
    }

    /**
     * Renvoie le nombre de colonnes de la grille.
     *
     * @return le nombre de colonnes
     */
    public int nbCol() {
        return cases[0].length;
    }

    /**
     * Crée une copie profonde de cette grille.
     * Chaque case de la nouvelle grille contient le même caractère
     * que la case correspondante de la grille d’origine.
     *
     * @return une nouvelle instance de {@code Grille} identique à celle-ci
     */
    public Grille copy() {
        Grille copie = new Grille(nbLig(), nbCol());
        for (int i = 0; i < nbLig(); i++) {
            for (int j = 0; j < nbCol(); j++) {
                copie.getCase(i, j).setChar(this.getCase(i, j).getChar());
            }
        }
        return copie;
    }

    /**
     * Fournit une représentation textuelle de la grille.
     * Cette méthode utilise {@link GrilleLoader#serialize(Grille, boolean)}
     * avec le paramètre {@code false} pour un affichage lisible (non GRL).
     *
     * @return une représentation lisible de la grille sous forme de chaîne de caractères
     */
    @Override
    public String toString() {
        return GrilleLoader.serialize(this, false);
    }
}
