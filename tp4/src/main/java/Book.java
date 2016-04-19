/**
 * Created by durand on 19/04/16.
 */

import javax.persistence.Entity;
import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Book implements Serializable{
        private static final long serialVersionUID = 1L;


        private String title;
        private Author author;
        private Date date;

        public Book() {
            this.title = "";
        }

        public Book(String title) {
            this.title = title;
        }

        public Book(Author a, String t, Date date){
            this.author = a;
            this.title = t;
            this.date = date;
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
}
