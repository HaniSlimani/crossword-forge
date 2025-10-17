package pobj.motx.tme3.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pobj.motx.tme1.Grille;
import pobj.motx.tme1.GrilleLoader;
import pobj.motx.tme2.Dictionnaire;
import pobj.motx.tme2.GrillePlaces;
import pobj.motx.tme3.csp.GrilleContrainte;
import pobj.motx.tme3.adapt.MotX;
import pobj.motx.tme3.csp.CSPSolver;
import pobj.motx.tme3.csp.ICSP;

public class TestGrille {

    private void testGrid(String dictPath, String gridPath) {
        Dictionnaire dico = Dictionnaire.loadDictionnaire(dictPath);
        Grille grilleBrute = GrilleLoader.loadGrille(gridPath);

        assertNotNull(grilleBrute, "La grille n'a pas pu être chargée : " + gridPath);

        GrillePlaces grillePlaces = new GrillePlaces(grilleBrute);
        GrilleContrainte gp = new GrilleContrainte(grillePlaces, dico);

        assertFalse(gp.isDead(), "Grille morte avant résolution : " + gridPath);

        ICSP problem = new MotX(gp);
        CSPSolver solver = new CSPSolver();

        long startTime = System.currentTimeMillis();
        ICSP solution = solver.solve(problem);
        long elapsed = System.currentTimeMillis() - startTime;

        System.out.println("Solution pour " + gridPath + " trouvée en " + elapsed + " ms :\n" + solution);

        assertNotNull(solution, "Aucune solution trouvée pour : " + gridPath);
        assertTrue(solution.isConsistent(), "Solution incohérente pour : " + gridPath);
    }

    @Test
    public void testEasy() {
        testGrid("data/frgut.txt", "data/easy.grl");
    }

    @Test
    public void testEnonce() {
        testGrid("data/frgut.txt", "data/enonce.grl");
    }

    @Test
    public void testMedium() {
        testGrid("data/frgut.txt", "data/medium.grl");
    }

    // Ajouter d'autres grilles si besoin
}
