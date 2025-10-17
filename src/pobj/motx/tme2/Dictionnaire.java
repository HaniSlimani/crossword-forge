package pobj.motx.tme2;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Un ensemble de mots.
 */
public class Dictionnaire {

    // stockage des mots
    private List<String> mots = new ArrayList<>();
    private EnsembleLettre[] cache = null;

    /** Ajoute un mot au dictionnaire */
    public void add(String mot) {
        mots.add(mot.toLowerCase());
    }

    /** Taille du dictionnaire */
    public int size() {
        return mots.size();
    }

    /** Accès au ième mot */
    public String get(int i) {
        return mots.get(i);
    }

    public List<String> getMots() {
        return new ArrayList<>(mots);
    }

    /** Rend une copie de ce dictionnaire */
    public Dictionnaire copy() {
        Dictionnaire copy = new Dictionnaire();
        copy.mots.addAll(this.mots);
        copy.cache = this.cache; // copie la référence du cache
        return copy;
    }

    /** Filtre par longueur */
    public int filtreLongueur(int len) {
        List<String> cible = new ArrayList<>();
        int cpt = 0;
        for (String mot : mots) {
            if (mot.length() == len)
                cible.add(mot);
            else
                cpt++;
        }
        if (cpt > 0) cache = null; // invalide le cache si modifié
        mots = cible;
        return cpt;
    }

    /** Filtre par lettre à l'indice i */
    public int filtreParLettre(char c, int i) {
        List<String> cible = new ArrayList<>();
        int suppr = 0;
        for (String mot : mots) {
            if (i < mot.length() && mot.charAt(i) == c)
                cible.add(mot);
            else
                suppr++;
        }
        if (suppr > 0) cache = null;
        mots = cible;
        return suppr;
    }

    /** Retourne un EnsembleLettre à une position (avec cache) */
    public EnsembleLettre getEnsembleLettre(int index) {
        if (mots.isEmpty()) return new EnsembleLettre();
        if (cache == null) cache = new EnsembleLettre[mots.get(0).length()];
        if (cache[index] == null) {
            EnsembleLettre e = new EnsembleLettre();
            for (String mot : mots) {
                e.add(mot.charAt(index));
            }
            cache[index] = e;
        }
        return cache[index];
    }

    /** Filtre par un EnsembleLettre à un index */
    public Dictionnaire filtreParLettre(int index, EnsembleLettre lettres) {
        Dictionnaire d = new Dictionnaire();
        for (String mot : mots) {
            if (lettres.contains(mot.charAt(index)))
                d.add(mot);
        }
        return d;
    }

    /** Charge un dictionnaire depuis un fichier texte */
    public static Dictionnaire loadDictionnaire(String path) {
        Dictionnaire dico = new Dictionnaire();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                if (!line.trim().isEmpty()) dico.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dico;
    }

    @Override
    public String toString() {
        if (size() == 1) return mots.get(0);
        return "Dico size=" + size();
    }
}
