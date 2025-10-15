package pobj.motx.tme1;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente un emplacement dans une grille de mots croisés,
 * c’est-à-dire une suite contiguë de {@link Case} non pleines
 * (horizontalement ou verticalement).
 */
public class Emplacement {

    /** Liste des cases qui composent l’emplacement */
    private List<Case> cases;

    /**
     * Construit un emplacement vide (sans aucune case).
     */
    public Emplacement() {
        cases = new ArrayList<>();
    }

    /**
     * Ajoute une case à la fin de l’emplacement.
     *
     * @param e la case à ajouter
     */
    public void add(Case e) {
        cases.add(e);
    }

    /**
     * Renvoie le nombre de cases constituant cet emplacement.
     *
     * @return le nombre de cases
     */
    public int size() {
        return cases.size();
    }

    /**
     * Renvoie la case située à l’indice donné.
     *
     * @param i l’indice de la case à récupérer (0 ≤ i < size)
     * @return la case à l’indice spécifié
     */
    public Case getCase(int i) {
        return cases.get(i);
    }

    /**
     * Renvoie la chaîne de caractères correspondant aux
     * caractères des cases de l’emplacement (le "mot" de la grille).
     *
     * @return la représentation textuelle du mot formé par cet emplacement
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Case c : cases) {
            sb.append(c.getChar());
        }
        return sb.toString();
    }
}
