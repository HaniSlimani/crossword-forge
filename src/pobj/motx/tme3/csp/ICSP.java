package pobj.motx.tme3.csp;

import java.util.List;

/**
 * Représente un problème de satisfaction de contraintes (CSP).
 * Un CSP contient un ensemble de variables et peut produire
 * de nouveaux CSP avec des variables fixées.
 */
public interface ICSP {

    /**
     * Retourne la liste des variables du problème.
     *
     * @return liste des variables
     */
    List<IVariable> getVars();

    /**
     * Vérifie si le problème est encore satisfiable, c'est-à-dire
     * si aucun domaine de variable n'est vide.
     *
     * @return true si le problème est cohérent, false sinon
     */
    boolean isConsistent();

    /**
     * Retourne un nouveau problème CSP correspondant à l'affectation
     * d'une variable à une valeur spécifique.
     * La variable vi est considérée comme fixée à val dans le nouveau problème.
     *
     * @param vi la variable à affecter
     * @param val la valeur assignée à la variable
     * @return un nouveau CSP avec la variable fixée
     */
    ICSP assign(IVariable vi, String val);
}
