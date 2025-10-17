package pobj.motx.tme3.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import pobj.motx.tme1.Grille;
import pobj.motx.tme1.GrilleLoader;
import pobj.motx.tme2.Dictionnaire;
import pobj.motx.tme2.GrillePlaces;
import pobj.motx.tme3.csp.GrilleContrainte;
import pobj.motx.tme3.adapt.MotX;
import pobj.motx.tme3.csp.CSPSolver;
import pobj.motx.tme3.csp.ICSP;

public class TestGrilleAllSafe {

    private void testGrid(String dictPath, String gridPath) {
        Dictionnaire dico = Dictionnaire.loadDictionnaire(dictPath);
        Grille grilleBrute = GrilleLoader.loadGrille(gridPath);

        assertNotNull(grilleBrute, "Impossible de charger la grille : " + gridPath);

        // Limite simple : si la grille est trop grande, on l'ignore
        if (grilleBrute.nbLig() * grilleBrute.nbCol() > 100) {
            System.out.println("Grille trop grande, test ignoré : " + gridPath);
            return;
        }


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
    public void testAllGrids() {
        String dictPath = "data/frgut.txt";
        File folder = new File("data");
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".grl"));

        assertNotNull(files, "Aucun fichier .grl trouvé dans data/");

        for (File f : files) {
            System.out.println("\n=== Test de la grille : " + f.getName() + " ===");
            testGrid(dictPath, f.getPath());
        }
    }
}
