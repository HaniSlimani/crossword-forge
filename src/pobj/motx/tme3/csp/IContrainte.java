package pobj.motx.tme3.csp;

import pobj.motx.tme2.GrillePotentiel;

/**
 * Interface représentant une contrainte sur une grille de mots croisés.
 * Une contrainte peut restreindre le domaine potentiel des mots dans la grille.
 */
public interface IContrainte {

    /**
     * Applique la contrainte sur la grille donnée en modifiant ses domaines potentiels.
     * 
     * @param grille la grille potentielle à laquelle la contrainte s'applique
     * @return le nombre total de mots filtrés par cette action (0 si aucun mot n'est supprimé)
     */
    int reduce(GrillePotentiel grille);
}
