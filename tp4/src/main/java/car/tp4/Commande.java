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
	private Map<Book, Integer> books;

	public Commande(Basket basket) {
		this.books = new HashMap<Book, Integer>(basket.getBooks());
	}

	public int getId() {
		return id;
	}

	public Map<Book, Integer> getListeLivre() {
		return books;
	}

	public void setBooks(Map<Book, Integer> books) {
		this.books = books;
	}
}