package pobj.motx.tme1;

/**
 * Représente une case d'une grille de mots croisés.
 * Une case est définie par sa position (ligne, colonne)
 * et le caractère qu'elle contient (lettre, vide ou mur).
 */
public class Case {

    /** Ligne de la case dans la grille */
    private int lig;

    /** Colonne de la case dans la grille */
    private int col;

    /** Caractère contenu dans la case (' ', '*', ou une lettre) */
    private char c;

    /**
     * Construit une case avec sa position et son caractère initial.
     * @param lig la ligne de la case
     * @param col la colonne de la case
     * @param c le caractère contenu dans la case
     */
    public Case(int lig, int col, char c) {
        this.lig = lig;
        this.col = col;
        this.c = c;
    }

    /**
     * Renvoie la ligne de cette case.
     * @return la ligne de la case
     */
    public int getLig() {
        return lig;
    }

    /**
     * Renvoie la colonne de cette case.
     * @return la colonne de la case
     */
    public int getCol() {
        return col;
    }

    /**
     * Renvoie le caractère contenu dans cette case.
     * @return le caractère de la case
     */
    public char getChar() {
        return c;
    }

    /**
     * Modifie le caractère contenu dans cette case.
     * @param c le nouveau caractère à placer dans la case
     */
    public void setChar(char c) {
        this.c = c;
    }

    /**
     * Indique si la case est vide.
     * Une case vide contient le caractère ' ' (espace).
     * @return true si la case est vide, false sinon
     */
    public boolean isVide() {
        return c == ' ';
    }

    /**
     * Indique si la case est pleine (mur).
     * Une case pleine contient le caractère '*'.
     * @return true si la case est pleine, false sinon
     */
    public boolean isPleine() {
        return c == '*';
    }

    /**
     * Fournit une représentation textuelle lisible de la case,
     * utile pour le débogage et l’affichage.
     * @return une chaîne de la forme "Case(lig, col, 'c')"
     */
    @Override
    public String toString() {
        return "Case(" + lig + ", " + col + ", '" + c + "')";
    }
}

