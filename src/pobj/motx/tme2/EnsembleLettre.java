package pobj.motx.tme2;

import java.util.HashSet;
import java.util.Set;

/**
 * Représente un ensemble de lettres sans doublons.
 * Permet les opérations ensemblistes classiques comme l'ajout,
 * la vérification de présence, la taille et l'intersection.
 */
public class EnsembleLettre {
    private Set<Character> lettres;

    /**
     * Crée un ensemble de lettres vide.
     */
    public EnsembleLettre() {
        lettres = new HashSet<>();
    }

    /**
     * Ajoute une lettre à l'ensemble si elle n'y est pas déjà.
     *
     * @param c la lettre à ajouter
     */
    public void add(char c) {
        lettres.add(c);
    }

    /**
     * Indique si une lettre appartient à l'ensemble.
     *
     * @param c la lettre à tester
     * @return true si la lettre est présente, false sinon
     */
    public boolean contains(char c) {
        return lettres.contains(c);
    }

    /**
     * Retourne le nombre de lettres dans l'ensemble.
     *
     * @return la taille de l'ensemble
     */
    public int size() {
        return lettres.size();
    }

    /**
     * Calcule l'intersection entre cet ensemble et un autre.
     *
     * @param other l'autre ensemble de lettres
     * @return un nouvel EnsembleLettre correspondant à l'intersection
     */
    public EnsembleLettre intersection(EnsembleLettre other) {
        EnsembleLettre inter = new EnsembleLettre();
        for (char c : lettres) {
            if (other.contains(c)) inter.add(c);
        }
        return inter;
    }

    /**
     * Retourne l'ensemble des lettres sous forme de Set.
     *
     * @return le Set de caractères internes
     */
    public Set<Character> getLettres() {
        return lettres;
    }

    @Override
    public String toString() {
        return lettres.toString();
    }
}

