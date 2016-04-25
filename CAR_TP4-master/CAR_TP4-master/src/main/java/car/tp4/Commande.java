package car.tp4;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Commande {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	/**
	 * Livre : livre, int : nombre de livre
	 */
	private Map<Livre, Integer> listeLivre;

	public Commande(Panier panier) {
		this.listeLivre = new HashMap<Livre, Integer>(panier.getListeLivre());
	}

	public int getId() {
		return id;
	}

	public Map<Livre, Integer> getListeLivre() {
		return listeLivre;
	}

	public void setListeLivre(Map<Livre, Integer> listeLivre) {
		this.listeLivre = listeLivre;
	}
}
