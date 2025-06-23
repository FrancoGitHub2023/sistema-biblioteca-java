
// ====================
//  SearchVisitor.java
// ====================
package library;

import java.util.ArrayList;
import java.util.List;

/**
  Implementazione del Visitor pattern per la ricerca
 **/
public class SearchVisitor implements LibraryVisitor {
    private String searchTerm;
    private List<LibraryItem> results;
    
    public SearchVisitor(String searchTerm) {
        if (searchTerm == null) {
            throw new IllegalArgumentException("Il termine di ricerca non può essere nulla");
        }
        this.searchTerm = searchTerm.toLowerCase().trim();
        this.results = new ArrayList<>();
    }
    
    @Override
    public void visit(Book book) {
        if (book.getTitle().toLowerCase().contains(searchTerm) || 
            book.getAuthor().toLowerCase().contains(searchTerm)) {
            results.add(book);
        }
    }
    
    @Override
    public void visit(Magazine magazine) {
        if (magazine.getTitle().toLowerCase().contains(searchTerm) || 
            magazine.getPublisher().toLowerCase().contains(searchTerm)) {
            results.add(magazine);
        }
    }
    
    @Override
    public void visit(DVD dvd) {
        if (dvd.getTitle().toLowerCase().contains(searchTerm) || 
            dvd.getDirector().toLowerCase().contains(searchTerm)) {
            results.add(dvd);
        }
    }
    
    @Override
    public void visit(Collection collection) {
        if (collection.getTitle().toLowerCase().contains(searchTerm)) {
            results.add(collection);
        }
    }
    
    public List<LibraryItem> getResults() {
        return new ArrayList<>(results); // Copia difensiva
    }
}
