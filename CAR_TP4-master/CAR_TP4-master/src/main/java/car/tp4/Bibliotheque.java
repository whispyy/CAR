package car.tp4;

import java.util.ArrayList;
import java.util.HashSet;

import javax.ejb.Stateful;

@Stateful
public class Bibliotheque {

	private ArrayList<Livre> livres;

	public void init() {
		this.livres = new ArrayList<Livre>();

		Livre l1 = new Livre("Dark Plagueis", "James Luceneau", "2003");
		Livre l2 = new Livre("White Fang", "Jack London", "1999");
		Livre l3 = new Livre("Trop vite", "Nabilla", "2016");
		Livre l4 = new Livre("Tu n'as rien à craindre de moi", "Joann Sfar", "2010");

		this.livres.add(l1);
		this.livres.add(l2);
		this.livres.add(l3);
		this.livres.add(l4);
	}
	
	public void add(String titre, String auteur, String date) {
		this.livres.add(new Livre(titre, auteur, date));
	}

	public ArrayList<Livre> listeLivres() {
		return this.livres;
	}

	public ArrayList<String> listeAuteurs() {
		HashSet<String> auteurs = new HashSet<String>();

		for (Livre livre : livres) {
			auteurs.add(livre.getAuteur());
		}

		ArrayList<String> liste = new ArrayList<String>(auteurs);

		return liste;
	}
	
	public Livre getLivre(int id) {
		for (Livre livre : livres) {
			if(livre.getId() == id) {
				return livre;
			}
		}
		return null;
	}

}
