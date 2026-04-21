package pobj.motx.tme3.csp;

import java.util.ArrayList;
import java.util.List;

import pobj.motx.tme2.Dictionnaire;
import pobj.motx.tme2.EnsembleLettre;
import pobj.motx.tme2.GrillePotentiel;
import pobj.motx.tme2.GrillePlaces;
import pobj.motx.tme1.Case;

/**
 * Classe représentant une grille de mots croisés enrichie de contraintes de croisement.
 * Chaque emplacement de mot est associé à un dictionnaire de mots potentiels.
 * Les contraintes sont propagées jusqu'à stabilité pour réduire les domaines.
 */
public class GrilleContrainte extends GrillePotentiel {

    /** Liste des contraintes de croisement détectées */
    private List<IContrainte> contraintes;

    /**
     * Constructeur : initialise la grille, détecte les contraintes et applique la propagation.
     *
     * @param grille GrillePlaces représentant les emplacements à remplir
     * @param dicoComplet Dictionnaire complet utilisé pour initialiser les domaines potentiels
     */
    public GrilleContrainte(GrillePlaces grille, Dictionnaire dicoComplet) {
        super(grille, dicoComplet);
        contraintes = new ArrayList<>();
        detecterContraintes(grille);
        appliquerContraintes();
        propage();
    }

    /**
     * Retourne la liste des contraintes de croisement détectées.
     *
     * @return liste des contraintes
     */
    public List<IContrainte> getContraintes() {
        return contraintes;
    }

    /**
     * Fixe un mot à un emplacement donné et retourne une nouvelle grille contrainte.
     * Les contraintes sont réappliquées sur la nouvelle grille.
     *
     * @param m indice de l'emplacement
     * @param soluce mot à placer
     * @return nouvelle GrilleContrainte avec le mot fixé
     */
    @Override
    public GrilleContrainte fixer(int m, String soluce) {
        GrillePotentiel gp = super.fixer(m, soluce);
        GrilleContrainte gc = new GrilleContrainte(gp.getGrillePlaces(), gp.getDicoComplet());
        gc.setMotsPot(gp.getMotsPot());
        gc.appliquerContraintes();
        propage();
        return gc;
    }

    /**
     * Détecte tous les croisements horizontaux/verticaux et crée les contraintes correspondantes.
     *
     * @param grille GrillePlaces à analyser
     */
    private void detecterContraintes(GrillePlaces grille) {
        int nbH = grille.getNbHorizontal();
        int nbTotal = grille.getPlaces().size();

        for (int i = 0; i < nbH; i++) {
            for (int j = nbH; j < nbTotal; j++) {
                for (int ch = 0; ch < grille.getPlaces().get(i).size(); ch++) {
                    for (int cv = 0; cv < grille.getPlaces().get(j).size(); cv++) {
                        Case caseH = grille.getPlaces().get(i).getCase(ch);
                        Case caseV = grille.getPlaces().get(j).getCase(cv);
                        if (caseH.equals(caseV) && caseH.getChar() == ' ' && caseV.getChar() == ' ') {
                            contraintes.add(new CroixContrainte(i, ch, j, cv));
                        }
                    }
                }
            }
        }
    }

    /**
     * Applique une réduction simple initiale sur tous les dictionnaires via les contraintes détectées.
     */
    private void appliquerContraintes() {
        boolean changed;
        do {
            changed = false;
            for (IContrainte c : contraintes) {
                CroixContrainte croix = (CroixContrainte) c;
                Dictionnaire d1 = getMotsPot().get(croix.getM1());
                Dictionnaire d2 = getMotsPot().get(croix.getM2());

                EnsembleLettre lettres1 = d1.getEnsembleLettre(croix.getC1());
                EnsembleLettre lettres2 = d2.getEnsembleLettre(croix.getC2());

                EnsembleLettre intersection = lettres1.intersection(lettres2);

                int sizeBefore1 = d1.size();
                int sizeBefore2 = d2.size();

                getMotsPot().set(croix.getM1(), d1.filtreParLettre(croix.getC1(), intersection));
                getMotsPot().set(croix.getM2(), d2.filtreParLettre(croix.getC2(), intersection));

                if (d1.size() != sizeBefore1 || d2.size() != sizeBefore2) {
                    changed = true;
                }
            }
        } while (changed);
    }

    /**
     * Propage toutes les contraintes jusqu'à stabilité.
     * Itère jusqu'à ce qu'aucun mot ne soit supprimé ou que la grille devienne irréalisable.
     *
     * @return true si la propagation atteint un point fixe, false si la grille est irréalisable
     */
    private boolean propage() {
        boolean changed;
        do {
            if (isDead()) {
                return false;
            }
            changed = false;
            int totalFiltre = 0;

            for (IContrainte c : contraintes) {
                totalFiltre += c.reduce(this);
            }

            if (totalFiltre > 0) {
                changed = true;
            }
        } while (changed);

        return true;
    }

}


