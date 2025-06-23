
// ====================
//  LibraryIterator.java
// ====================
package library;

/**
 * Interfaccia per Iterator pattern
 */
public interface LibraryIterator {
    boolean hasNext();
    LibraryItem next();
}
