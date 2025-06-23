
// ====================
//  Collection.java 
// ====================
package library;

import java.util.ArrayList;
import java.util.List;

/**
  Implementazione del Composite pattern per gestire collezioni
 **/
public class Collection implements LibraryItem {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private List<LibraryItem> items;
    
    public Collection(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Il nome della collezione non può essere nullo o vuoto");
        }
        this.name = name.trim();
        this.items = new ArrayList<>();
    }
    
    public void addItem(LibraryItem item) {
        if (item != null) {
            items.add(item);
        }
    }
    
    public void removeItem(LibraryItem item) {
        items.remove(item);
    }
    
    public List<LibraryItem> getItems() {
        return new ArrayList<>(items); // Copia difensiva
    }
    
    @Override
    public String getTitle() {
        return name;
    }
    
    @Override
    public String getDescription() {
        return "Collezione: " + name + " (" + items.size() + " titoli)";
    }
    
    @Override
    public void display() {
        System.out.println(getDescription());
        for (LibraryItem item : items) {
            item.display();
        }
    }
    
    public LibraryIterator createIterator() {
        return new CollectionIterator(this);
    }
    
    @Override
    public void accept(LibraryVisitor visitor) {
        visitor.visit(this);
        // Visita ricorsiva degli elementi
        for (LibraryItem item : items) {
            item.accept(visitor);
        }
    }
}
