package car.tp4;
/**
 * Created by durand on 19/04/16.
 */

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Book implements Serializable{


	private static final long serialVersionUID = 1L;
	private String title;
	private Author author;
	private int year;

	public Book() {
		this.title = "";
	}

	public Book(String title) {
		this.title = title;
	}

	public Book(String t, Author a, int year){
		this.author = a;
		this.title = t;
		this.year = year;
	}

	@ManyToOne
	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	@Id
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}