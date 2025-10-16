package pobj.motx.tme2;

import pobj.motx.tme1.Case;
import pobj.motx.tme1.Grille;
import pobj.motx.tme1.Emplacement;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui explore une grille pour détecter tous les emplacements de mots
 * (horizontaux et verticaux). Un emplacement est une séquence contiguë
 * d'au moins deux cases non pleines ('*').
 * 
 * Cette classe permet également de "fixer" un mot dans un emplacement, 
 * en retournant une nouvelle GrillePlaces avec la modification, 
 * sans changer la grille originale.
 */
public class GrillePlaces {

    /** Grille originale sur laquelle la GrillePlaces est construite */
    private Grille grille;

    /** Liste des emplacements détectés dans la grille */
    private List<Emplacement> places;

    /** Nombre d'emplacements horizontaux (pour différencier des verticaux) */
    private int nbHorizontal;

    /**
     * Construit une GrillePlaces à partir d'une grille donnée.
     * Cherche et stocke tous les emplacements horizontaux puis verticaux.
     *
     * @param grille la grille à analyser
     */
    public GrillePlaces(Grille grille) {
        this.grille = grille;
        places = new ArrayList<>();

        // Recherche des emplacements horizontaux
        for (int lig = 0; lig < grille.nbLig(); lig++) {
            List<Case> ligne = getLig(grille, lig);
            cherchePlaces(ligne);
        }

        nbHorizontal = places.size(); // nombre d'emplacements horizontaux

        // Recherche des emplacements verticaux
        for (int col = 0; col < grille.nbCol(); col++) {
            List<Case> colonne = getCol(grille, col);
            cherchePlaces(colonne);
        }
    }

    /**
     * Retourne la liste complète des emplacements trouvés.
     *
     * @return liste des emplacements
     */
    public List<Emplacement> getPlaces() {
        return places;
    }

    /**
     * Retourne le nombre d'emplacements horizontaux trouvés.
     *
     * @return nombre d'emplacements horizontaux
     */
    public int getNbHorizontal() {
        return nbHorizontal;
    }

    /**
     * Retourne la grille originale utilisée pour cette GrillePlaces.
     *
     * @return la grille originale
     */
    public Grille getGrille() {
        return grille;
    }

    /**
     * Retourne une liste des cases formant la ligne d'indice lig.
     *
     * @param grille la grille
     * @param lig l'indice de ligne
     * @return la liste des cases de cette ligne
     */
    private List<Case> getLig(Grille grille, int lig) {
        List<Case> res = new ArrayList<>();
        for (int col = 0; col < grille.nbCol(); col++) {
            res.add(grille.getCase(lig, col));
        }
        return res;
    }

    /**
     * Retourne une liste des cases formant la colonne d'indice col.
     *
     * @param grille la grille
     * @param col l'indice de colonne
     * @return la liste des cases de cette colonne
     */
    private List<Case> getCol(Grille grille, int col) {
        List<Case> res = new ArrayList<>();
        for (int lig = 0; lig < grille.nbLig(); lig++) {
            res.add(grille.getCase(lig, col));
        }
        return res;
    }

    /**
     * Recherche les emplacements de mots dans une séquence de cases (ligne ou colonne)
     * et les ajoute à la liste des emplacements.
     *
     * @param cases la liste de cases à analyser
     */
    private void cherchePlaces(List<Case> cases) {
        Emplacement e = new Emplacement();

        for (Case c : cases) {
            if (!c.isPleine()) {
                e.add(c);
            } else {
                if (e.size() >= 2) {
                    places.add(e);
                }
                e = new Emplacement();
            }
        }

        // Ajout du dernier emplacement si valide
        if (e.size() >= 2) {
            places.add(e);
        }
    }

    /**
     * Retourne une nouvelle GrillePlaces où le mot de l'emplacement d'indice m
     * est fixé à la solution donnée. La grille originale reste inchangée.
     *
     * @param m l'indice de l'emplacement à remplir (dans la liste retournée par getPlaces())
     * @param soluce le mot à placer dans cet emplacement
     * @return une nouvelle GrillePlaces avec le mot placé
     */
    public GrillePlaces fixer(int m, String soluce) {
        // 1. Copie de la grille originale
        Grille copieGrille = this.grille.copy();

        // 2. Récupère l'emplacement correspondant
        Emplacement e = this.getPlaces().get(m);

        // 3. Place chaque lettre dans la copie
        for (int i = 0; i < e.size(); i++) {
            int lig = e.getCase(i).getLig();
            int col = e.getCase(i).getCol();
            copieGrille.getCase(lig, col).setChar(soluce.charAt(i));
        }

        // 4. Retourne une nouvelle GrillePlaces avec la grille modifiée
        return new GrillePlaces(copieGrille);
    }

    /**
     * Retourne une représentation textuelle des emplacements détectés.
     *
     * @return chaîne représentant les emplacements
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre total d'emplacements : ").append(places.size()).append("\n");
        sb.append("Nombre d'emplacements horizontaux : ").append(nbHorizontal).append("\n\n");

        int i = 0;
        for (Emplacement e : places) {
            sb.append(i++).append(" : ").append(e.toString()).append("\n");
        }
        return sb.toString();
    }
}

