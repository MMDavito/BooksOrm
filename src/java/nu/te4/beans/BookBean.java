
package nu.te4.beans;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import nu.te4.entities.Author;
import nu.te4.entities.Book;
import nu.te4.sessionbeans.AuthorFacade;
import nu.te4.sessionbeans.BookFacade;


@Named
@SessionScoped
public class BookBean implements Serializable{
    
    private int price,auth_id;
    private String name;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAuth_id() {
        return auth_id;
    }

    public void setAuth_id(int auth_id) {
        this.auth_id = auth_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    @EJB
    BookFacade bookFacade;
    
    @EJB
    AuthorFacade authorFacade;
    
    public List<Book> getBooks(){
    return bookFacade.findAll();    
    }
    public String saveBook(){
        Book book = new Book(null, name, price);
        
        Author author = authorFacade.find(getAuth_id());
        book.setAuthor(author);
        bookFacade.create(book);
        
        return "index";
    }
    
}
