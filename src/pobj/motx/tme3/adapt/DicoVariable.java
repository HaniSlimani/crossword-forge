package pobj.motx.tme3.adapt;

import java.util.List;
import pobj.motx.tme3.csp.IVariable;
import pobj.motx.tme3.csp.GrilleContrainte;
import pobj.motx.tme2.Dictionnaire;

public class DicoVariable implements IVariable {
    private int index;
    private GrilleContrainte grille;

    public DicoVariable(int index, GrilleContrainte grille) {
        this.index = index;
        this.grille = grille;
    }

    @Override
    public List<String> getDomain() {
        Dictionnaire d = grille.getMotsPot().get(index);
        return d.getMots(); // il faudra que Dictionnaire ait une méthode getMots() qui renvoie List<String>
    }

    @Override
    public String toString() {
        return "Variable[" + index + "]";
    }

    public int getIndex() { return index; }
    public GrilleContrainte getGrille() { return grille; }
}
