package car.tp4;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;

@Entity
public class Panier {

	/**
	 * Livre : livre, int : nombre de livre
	 */
	private HashMap<Livre, Integer> listeLivre;

	public Panier() {
		this.listeLivre = new HashMap<Livre, Integer>();
	}

	public HashMap<Livre, Integer> getListeLivre() {
		return listeLivre;
	}

	public void clear() {
		this.listeLivre.clear();
	}

	public void add(int id, Bibliotheque bibliotheque) {
		Livre livre = bibliotheque.getLivre(id);

		if (livre != null) {
			if (this.listeLivre.containsKey(livre)) {
				this.listeLivre.put(livre, this.listeLivre.get(livre) + 1);
			} else {
				this.listeLivre.put(livre, 1);
			}
		}
	}
}
