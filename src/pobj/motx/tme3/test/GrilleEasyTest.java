package pobj.motx.tme3.test;

import org.junit.jupiter.api.Test;

import pobj.motx.tme1.Grille;
import pobj.motx.tme1.GrilleLoader;
import pobj.motx.tme2.Dictionnaire;
import pobj.motx.tme2.GrillePlaces;
import pobj.motx.tme3.csp.GrilleContrainte;
import pobj.motx.tme3.adapt.MotX;
import pobj.motx.tme3.csp.CSPSolver;
import pobj.motx.tme3.csp.ICSP;

public class GrilleEasyTest {

    @Test
    public void testEasyGrille() {
        // On essaie sur un dictionnaire restreint pour vérifier que le CSPSolver marche correctement
        Dictionnaire mini = new Dictionnaire();
        mini.add("belle");
        mini.add("aieul");
        mini.add("benin");
        mini.add("apion");
        mini.add("album");
        mini.add("aimer");

        Grille gr = GrilleLoader.loadGrille("data/easy.grl");
        System.out.println("Grille initiale :");
        System.out.println(gr);

        GrillePlaces grillePlaces = new GrillePlaces(gr);
        GrilleContrainte gp = new GrilleContrainte(grillePlaces, mini);
        ICSP problem = new MotX(gp);
        CSPSolver solver = new CSPSolver();

        long start = System.currentTimeMillis();
        ICSP solution = solver.solve(problem);
        long duration = System.currentTimeMillis() - start;

        System.out.println("Solution :");
        System.out.println(solution);
        System.out.println("Temps écoulé : " + duration + " ms");
    }
}
