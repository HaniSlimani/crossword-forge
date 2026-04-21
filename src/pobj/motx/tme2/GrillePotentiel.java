package pobj.motx.tme2;

import java.util.ArrayList;
import java.util.List;
import pobj.motx.tme1.Emplacement;

public class GrillePotentiel {

    private GrillePlaces grille;
    private Dictionnaire dicoComplet;
    private List<Dictionnaire> motsPot;

    public GrillePotentiel(GrillePlaces grille, Dictionnaire dicoComplet) {
        this.grille = grille;
        this.dicoComplet = dicoComplet;
        this.motsPot = new ArrayList<>();

        for (Emplacement e : grille.getPlaces()) {
            Dictionnaire d = dicoComplet.copy();
            d.filtreLongueur(e.size());
            for (int i = 0; i < e.size(); i++) {
                char lettre = e.getCase(i).getChar();
                if (lettre != ' ') d.filtreParLettre(lettre, i);
            }
            motsPot.add(d);
        }
    }

    public GrillePlaces getGrillePlaces() { return grille; }
    public Dictionnaire getDicoComplet() { return dicoComplet; }
    public List<Dictionnaire> getMotsPot() { return motsPot; }
    protected void setMotsPot(List<Dictionnaire> motsPot) { this.motsPot = motsPot; }

    public boolean isDead() {
        for (Dictionnaire d : motsPot)
            if (d.size() == 0) return true;
        return false;
    }

    public GrillePotentiel fixer(int m, String soluce) {
        GrillePlaces nouvelleGrillePlaces = this.grille.fixer(m, soluce);
        return new GrillePotentiel(nouvelleGrillePlaces, this.dicoComplet);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("GrillePotentiel :\n");
        for (int i = 0; i < motsPot.size(); i++)
            sb.append("Emplacement ").append(i).append(" -> ").append(motsPot.get(i)).append("\n");
        return sb.toString();
    }
}
