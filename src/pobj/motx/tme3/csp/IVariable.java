package pobj.motx.tme3.csp;

import java.util.List;

/**
 * Représente une variable dans un problème CSP.
 * Chaque variable a un domaine possible de valeurs.
 */
public interface IVariable {

    /**
     * Retourne le domaine de la variable, c'est-à-dire l'ensemble
     * des valeurs possibles qu'elle peut prendre.
     *
     * @return liste de valeurs possibles
     */
    List<String> getDomain();
}
