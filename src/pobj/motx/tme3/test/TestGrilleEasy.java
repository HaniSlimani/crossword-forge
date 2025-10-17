package pobj.motx.tme3.test;

import org.junit.jupiter.api.Test;

import pobj.motx.tme1.Grille;
import pobj.motx.tme1.GrilleLoader;
import pobj.motx.tme2.Dictionnaire;
import pobj.motx.tme2.GrillePlaces;
import pobj.motx.tme3.csp.CSPSolver;
import pobj.motx.tme3.csp.ICSP;
import pobj.motx.tme3.adapt.MotX;
import pobj.motx.tme3.csp.GrilleContrainte;

import static org.junit.jupiter.api.Assertions.*;

public class TestGrilleEasy {

    @Test
    public void testEasy5x5() {
        // Charger dictionnaire et grille
        Dictionnaire dico = Dictionnaire.loadDictionnaire("data/frgut.txt");
        Grille grilleBrute = GrilleLoader.loadGrille("data/easy.grl");

        // Vérification simple
        assertNotNull(grilleBrute);
        assertEquals(5, grilleBrute.nbLig());
        assertEquals(5, grilleBrute.nbCol());

        // Créer les emplacements
        GrillePlaces grillePlaces = new GrillePlaces(grilleBrute);
        GrilleContrainte gp = new GrilleContrainte(grillePlaces, dico);

        assertFalse(gp.isDead(), "La grille ne devrait pas être morte avant résolution");

        // Adapter au CSP
        ICSP problem = new MotX(gp);

        // Résolution
        CSPSolver solver = new CSPSolver();
        long startTime = System.currentTimeMillis();
        ICSP solution = solver.solve(problem);
        long elapsed = System.currentTimeMillis() - startTime;

        System.out.println("Solution trouvée en " + elapsed + " ms :\n" + solution);

        // Vérifications
        assertNotNull(solution);
        assertTrue(solution.isConsistent(), "La solution doit être cohérente");
    }
}
