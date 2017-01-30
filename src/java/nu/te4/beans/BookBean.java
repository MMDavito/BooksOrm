/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.beans;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import nu.te4.entities.Book;
import nu.te4.sessionbeans.AuthorFacade;
import nu.te4.sessionbeans.BookFacade;

/**
 *
 * @author daca97002
 */
@Named
@SessionScoped
public class BookBean implements Serializable{
    @EJB
    BookFacade bookFacade;
    
    @EJB
    AuthorFacade authorFacade;
    
    public List<Book> getBooks(){
    return bookFacade.findAll();    
    }
    
}
