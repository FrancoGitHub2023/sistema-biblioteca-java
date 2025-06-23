
// ====================
//  MediaFactory.java (Factory Pattern)
// ====================
package library;

import java.util.logging.Logger;
import java.util.logging.Level;

/**
  Factory pattern per creare elementi della biblioteca
 **/
public class MediaFactory {
    private static final Logger logger = Logger.getLogger(MediaFactory.class.getName());
    
    public Book createBook(String title, String author, int year, String isbn) {
        try {
            Book book = new Book(title, author, year, isbn);
            logger.log(Level.INFO, "Creato libro: {0}", title);
            return book;
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARNING, "Creazione libro non riuscito: {0}", e.getMessage());
            throw e;
        }
    }
    
    public Magazine createMagazine(String title, int issue, String publisher, int year) {
        try {
            Magazine magazine = new Magazine(title, issue, publisher, year);
            logger.log(Level.INFO, "Creato magazine: {0}", title);
            return magazine;
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARNING, "Creazione del magazine non riuscito: {0}", e.getMessage());
            throw e;
        }
    }
    
    public DVD createDVD(String title, String director, int duration, int year) {
        try {
            DVD dvd = new DVD(title, director, duration, year);
            logger.log(Level.INFO, "Creato DVD: {0}", title);
            return dvd;
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARNING, "Creazione del DVD non riuscita: {0}", e.getMessage());
            throw e;
        }
    }
    
    public Collection createCollection(String name) {
        try {
            Collection collection = new Collection(name);
            logger.log(Level.INFO, "Collezione creata: {0}", name);
            return collection;
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARNING, "Creazione della Collezione non riuscita: {0}", e.getMessage());
            throw e;
        }
    }
}
