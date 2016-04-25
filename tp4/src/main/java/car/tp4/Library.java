package car.tp4;

import java.util.ArrayList;

import javax.ejb.Stateful;

@Stateful
public class Library {

	private ArrayList<Book> books;

	public void initBooks() {
		this.books = new ArrayList<Book>();
		
		Author a1 = new Author("Nabilla");
		Author a2 = new Author("J.K. Rowling");
		Author a3 = new Author("Malik B");

		Book l1 = new Book("Trop vite", a1, 2016);
		Book l2 = new Book("Harry Potter 5", a2, 2009);
		Book l3 = new Book("Comment avoir un 20/20 en CAR", a3, 2017);
		this.books.add(l1);
		this.books.add(l2);
		this.books.add(l3);
	}
	
	public void add(String t, Author a, int date) {
		this.books.add(new Book(t, a, date));
	}

	public ArrayList<Book> getBooks() {
		return this.books;
	}

	public ArrayList<Author> getAuthors() {
		ArrayList<Author> authors = new ArrayList<Author>();

		for (Book b : books) {
			authors.add(b.getAuthor());
		}

		return authors;
	}
	
	public Book getBook(String title) {
		for (Book b : books) {
			if(b.getTitle().equals(title)) {
				return b;
			}
		}
		return null;
	}

}
