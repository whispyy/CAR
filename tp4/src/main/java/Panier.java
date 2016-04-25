

import java.util.HashMap;

import javax.persistence.Entity;

@Entity
public class Panier {

	/**
	 * Livre : livre, int : nombre de livre
	 */
	private HashMap<Book, Integer> books;

	public Panier() {
		this.books = new HashMap<Book, Integer>();
	}

	public HashMap<Book, Integer> getBooks() {
		return books;
	}

	public void clear() {
		this.books.clear();
	}

	public void add(String title, Library lib) {
		Book b = lib.getBook(title);

		if (b != null) {
			if (this.books.containsKey(b)) {
				this.books.put(b, this.books.get(b) + 1);
			} else {
				this.books.put(b, 1);
			}
		}
	}
}
