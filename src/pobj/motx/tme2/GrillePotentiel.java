package pobj.motx.tme2;

import java.util.ArrayList;
import java.util.List;
import pobj.motx.tme1.Emplacement;

/**
 * Représente une grille enrichie de domaines potentiels pour chaque emplacement.
 * Chaque emplacement de mot est associé à un dictionnaire contenant les mots
 * compatibles (ici uniquement par longueur).
 */
public class GrillePotentiel {

    /** Grille de départ (partiellement remplie). */
    private GrillePlaces grille;

    /** Dictionnaire français complet. */
    private Dictionnaire dicoComplet;

    /** Liste des domaines potentiels, un par emplacement. */
    private List<Dictionnaire> motsPot;

    /**
     * Constructeur : initialise les domaines potentiels à partir du dictionnaire complet.
     *
     * @param grille la grille actuelle
     * @param dicoComplet le dictionnaire complet à utiliser
     */
    public GrillePotentiel(GrillePlaces grille, Dictionnaire dicoComplet) {
        this.grille = grille;
        this.dicoComplet = dicoComplet;
        this.motsPot = new ArrayList<>();

        for (Emplacement e : grille.getPlaces()) {
            Dictionnaire d = dicoComplet.copy();
            d.filtreLongueur(e.size());

            for (int i = 0; i < e.size(); i++) {
                char lettre = e.getCase(i).getChar();
                if (lettre != ' ') {
                    d.filtreParLettre(lettre, i);
                }
            }

            motsPot.add(d);
        }
    }

    /** Retourne la grille associée à ce GrillePotentiel. */
    public GrillePlaces getGrillePlaces() {
        return grille;
    }

    /** Retourne le dictionnaire complet utilisé. */
    public Dictionnaire getDicoComplet() {
        return dicoComplet;
    }

    /** Retourne les domaines potentiels pour chaque emplacement. */
    public List<Dictionnaire> getMotsPot() {
        return motsPot;
    }

    /**
     * Permet aux classes filles de mettre à jour la liste des dictionnaires potentiels.
     *
     * @param motsPot nouvelle liste de dictionnaires
     */
    protected void setMotsPot(List<Dictionnaire> motsPot) {
        this.motsPot = motsPot;
    }

    /** Vérifie si au moins un domaine est vide. */
    public boolean isDead() {
        for (Dictionnaire d : motsPot) {
            if (d.size() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retourne une nouvelle instance de GrillePotentiel où le mot de l'emplacement
     * d'indice m est fixé.
     */
    public GrillePotentiel fixer(int m, String soluce) {
        GrillePlaces nouvelleGrillePlaces = this.grille.fixer(m, soluce);
        return new GrillePotentiel(nouvelleGrillePlaces, this.dicoComplet);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GrillePotentiel :\n");
        for (int i = 0; i < motsPot.size(); i++) {
            sb.append("Emplacement ").append(i)
              .append(" -> ").append(motsPot.get(i)).append("\n");
        }
        return sb.toString();
    }
}

