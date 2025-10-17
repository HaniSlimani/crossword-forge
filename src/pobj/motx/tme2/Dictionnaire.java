package pobj.motx.tme2;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * Un ensemble de mots.
 *
 */
public class Dictionnaire {

	// stockage des mots
	private List<String> mots = new ArrayList<>();

	/**
	 * Ajoute un mot au Dictionnaire, en dernière position.
	 * @param mot à ajouter, il sera stocké en minuscules (lowerCase)
	 */
	public void add(String mot) {
		mots.add(mot.toLowerCase());
	}

	/**
	 * Taille du dictionnaire, c'est à dire nombre de mots qu'il contient.
	 * @return la taille
	 */
	public int size() {
		return mots.size();
	}
	
	/**
	 * Accès au i-eme mot du dictionnaire.
	 * @param i l'index du mot recherché, compris entre 0 et size-1.
	 * @return le mot à cet index
	 */
	public String get(int i) {
		return mots.get(i);
	}
	
	public List<String> getMots() {
	    return new ArrayList<>(mots);
	}


	/**
	 * Rend une copie de ce Dictionnaire.
	 * @return une copie identique de ce Dictionnaire
	 */
	public Dictionnaire copy () {
		Dictionnaire copy = new Dictionnaire();
		copy.mots.addAll(mots);
		return copy;
	}

	/**
	 * Retire les mots qui ne font pas exactement "len" caractères de long.
	 * Attention cette opération modifie le Dictionnaire, utiliser copy() avant de filtrer pour ne pas perdre d'information.
	 * @param len la longueur voulue 
	 * @return le nombre de mots supprimés
	 */
	public int filtreLongueur(int len) {
		List<String> cible = new ArrayList<>();
		int cpt=0;
		for (String mot : mots) {
			if (mot.length() == len)
				cible.add(mot);
			else
				cpt++;
		}
		mots = cible;
		return cpt;
	}
	
	/**
	 * Charge un dictionnaire depuis un fichier texte.
	 * Chaque ligne du fichier doit contenir un mot.
	 * 
	 * @param path chemin du fichier à lire
	 * @return un nouveau Dictionnaire contenant tous les mots lus
	 */
	public static Dictionnaire loadDictionnaire(String path) {
	    Dictionnaire dico = new Dictionnaire();
	    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
	        for (String line = br.readLine(); line != null; line = br.readLine()) {
	            if (!line.trim().isEmpty()) {
	                dico.add(line.trim());
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return dico;
	}

	/**
	 * Retire les mots dont la i-eme lettre n'est pas égale à c.
	 * @param c le caractère à vérifier
	 * @param i l'indice dans le mot (0-based)
	 * @return le nombre de mots supprimés
	 */
	public int filtreParLettre(char c, int i) {
	    List<String> cible = new ArrayList<>();
	    int supprimés = 0;
	    for (String mot : mots) {
	        if (i < mot.length() && mot.charAt(i) == c) {
	            cible.add(mot);
	        } else {
	            supprimés++;
	        }
	    }
	    mots = cible;
	    return supprimés;
	}
	/**
	 * Calcule l'ensemble des lettres possibles à une position donnée
	 * dans les mots du dictionnaire.
	 *
	 * @param index position de la lettre dans le mot
	 * @return un EnsembleLettre représentant toutes les lettres possibles à cet index
	 */
	public EnsembleLettre getEnsembleLettre(int index) {
	    EnsembleLettre e = new EnsembleLettre();
	    for (String mot : mots) {
	        if (index < mot.length()) {
	            e.add(mot.charAt(index));
	        }
	    }
	    return e;
	}
	/**
	 * Filtre le dictionnaire pour ne garder que les mots dont la lettre
	 * à la position donnée appartient à un ensemble de lettres possibles.
	 *
	 * @param index   la position dans le mot
	 * @param lettres l'ensemble de lettres autorisées
	 * @return un nouveau Dictionnaire filtré
	 */
	public Dictionnaire filtreParLettre(int index, EnsembleLettre lettres) {
	    Dictionnaire d = new Dictionnaire();
	    for (String mot : mots) {
	        if (lettres.contains(mot.charAt(index))) {
	            d.add(mot); // Utilise la méthode existante add(String)
	        }
	    }
	    return d;
	}





	
	@Override
	public String toString() {
		if (size() == 1) {
			return mots.get(0);
		} else {
			return "Dico size =" + size();
		}
	}
	
}
