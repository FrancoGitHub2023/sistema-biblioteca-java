
// ====================
//  CollectionIterator.java  
// ====================
package library;

import java.util.List;

/**
 * Implementazione concreta dell'Iterator pattern
 */
public class CollectionIterator implements LibraryIterator {
    private List<LibraryItem> items;
    private int position = 0;
    
    public CollectionIterator(Collection collection) {
        this.items = collection.getItems();
    }
    
    @Override
    public boolean hasNext() {
        return position < items.size();
    }
    
    @Override
    public LibraryItem next() {
        if (hasNext()) {
            LibraryItem item = items.get(position);
            position++;
            return item;
        }
        return null;
    }
}
