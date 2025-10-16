package pobj.motx.tme2;

import java.util.ArrayList;
import java.util.List;

import pobj.motx.tme1.Grille;
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
     * Chaque domaine est filtré selon la longueur de l'emplacement correspondant.
     *
     * @param grille la grille actuelle
     * @param dicoComplet le dictionnaire complet à utiliser
     */
    public GrillePotentiel(GrillePlaces grille, Dictionnaire dicoComplet) {
        this.grille = grille;
        this.dicoComplet = dicoComplet;
        this.motsPot = new ArrayList<>();

        // Pour chaque emplacement de la grille
        for (Emplacement e : grille.getPlaces()) {
            // Crée une copie du dictionnaire complet
            Dictionnaire d = dicoComplet.copy();
            // Filtre selon la longueur du mot attendu
            d.filtreLongueur(e.size());

            // Filtre selon les lettres déjà placées
            for (int i = 0; i < e.size(); i++) {
                char lettre = e.getCase(i).getChar();
                if (lettre != ' ') { // ou e.getCase(i).isVide() si tu as une méthode isVide()
                    d.filtreParLettre(lettre, i);
                }
            }

            // Ajoute le dictionnaire filtré à la liste des domaines
            motsPot.add(d);
        }
    }

    /**
     * Retourne les domaines potentiels pour chaque emplacement.
     * @return la liste des dictionnaires correspondant aux domaines
     */
    public List<Dictionnaire> getMotsPot() {
        return motsPot;
    }

    /**
     * Indique si la grille est "morte" : c’est-à-dire qu’au moins un emplacement
     * n’a aucun mot possible (domaine vide).
     *
     * @return true si un des domaines est vide, false sinon
     */
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
     * d'indice m est fixé à la solution donnée. La grille originale et ses domaines
     * potentiels restent inchangés.
     *
     * @param m l'indice de l'emplacement à remplir (dans la liste retournée par getPlaces())
     * @param soluce le mot à placer dans cet emplacement
     * @return une nouvelle GrillePotentiel avec le mot placé et les domaines mis à jour
     */
    public GrillePotentiel fixer(int m, String soluce) {
        // Crée une nouvelle GrillePlaces avec le mot fixé
        GrillePlaces nouvelleGrillePlaces = this.grille.fixer(m, soluce);

        // Crée un nouveau GrillePotentiel à partir de cette nouvelle grille
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
