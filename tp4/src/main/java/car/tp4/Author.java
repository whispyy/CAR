package car.tp4;
/**
 * Created by durand on 29/03/16.
 */

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Author {
	
    @Id
    private long id;
    
    private String name;
    
    @OneToMany
    private Collection<Book> books;
    
    public Author(String name){
    	this.name = name;
    }

    public Author() {
        this.books = new ArrayList<Book>();
    }

    public Author(String name, ArrayList<Book> books ) {
        this.name = name;
        this.books = books;
    }

    public Collection<Book> getBooks() {
        return this.books;
    }

    public void setBooks( Collection<Book> books ) {
        this.books = books;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
