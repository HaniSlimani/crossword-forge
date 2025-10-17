package pobj.motx.tme3.adapt;

import java.util.ArrayList;
import java.util.List;
import pobj.motx.tme3.csp.ICSP;
import pobj.motx.tme3.csp.IVariable;
import pobj.motx.tme3.csp.GrilleContrainte;

public class MotX implements ICSP {
    private List<DicoVariable> variables;

    public MotX(GrilleContrainte gc) {
        variables = new ArrayList<>();
        for (int i = 0; i < gc.getMotsPot().size(); i++) {
            // créer une variable seulement si il y a une case vide
            if (gc.getGrillePlaces().getPlaces().get(i).hasCaseVide()) {
                variables.add(new DicoVariable(i, gc));
            }
        }
    }

    @Override
    public List<IVariable> getVars() {
        return new ArrayList<>(variables);
    }

    @Override
    public boolean isConsistent() {
        return !variables.isEmpty() && !variables.get(0).getGrille().isDead();
    }

    @Override
    public ICSP assign(IVariable vi, String val) {
        if (vi instanceof DicoVariable) {
            DicoVariable dv = (DicoVariable) vi;
            GrilleContrainte gc = dv.getGrille().fixer(dv.getIndex(), val);
            return new MotX(gc);
        }
        throw new IllegalArgumentException("Variable inconnue");
    }

    @Override
    public String toString() {
        return variables.toString();
    }
}
