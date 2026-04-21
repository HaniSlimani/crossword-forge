package pobj.motx.tme3.csp;

import pobj.motx.tme2.*;

public class CroixContrainte implements IContrainte {
    private int m1, c1, m2, c2;

    public CroixContrainte(int m1, int c1, int m2, int c2) {
        this.m1 = m1;
        this.c1 = c1;
        this.m2 = m2;
        this.c2 = c2;
    }
    public int getM1() { return m1; }
    public int getM2() { return m2; }
    public int getC1() { return c1; }
    public int getC2() { return c2; }


    @Override
    public int reduce(GrillePotentiel grille) {
        int nbFiltre = 0;

        Dictionnaire d1 = grille.getMotsPot().get(m1);
        Dictionnaire d2 = grille.getMotsPot().get(m2);

        // Calculer l’ensemble des lettres possibles à chaque position
        EnsembleLettre l1 = d1.getEnsembleLettre(c1);
        EnsembleLettre l2 = d2.getEnsembleLettre(c2);

        // Intersection
        EnsembleLettre s = l1.intersection(l2);

        // Réduction du dictionnaire d1 si nécessaire
        if (l1.size() > s.size()) {
            int avant = d1.size();
            grille.getMotsPot().set(m1, d1.filtreParLettre(c1, s));
            nbFiltre += avant - grille.getMotsPot().get(m1).size();
        }

        // Réduction du dictionnaire d2 si nécessaire
        if (l2.size() > s.size()) {
            int avant = d2.size();
            grille.getMotsPot().set(m2, d2.filtreParLettre(c2, s));
            nbFiltre += avant - grille.getMotsPot().get(m2).size();
        }

        return nbFiltre;
    }

    @Override
    public String toString() {
        return "Croisement (m" + m1 + ", c" + c1 + ") <-> (m" + m2 + ", c" + c2 + ")";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CroixContrainte that = (CroixContrainte) o;
        return m1 == that.m1 && c1 == that.c1 && m2 == that.m2 && c2 == that.c2;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(m1, c1, m2, c2);
    }
}
