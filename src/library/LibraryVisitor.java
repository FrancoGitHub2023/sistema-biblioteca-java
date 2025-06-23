// ====================
//  LibraryVisitor.java
// ====================
package library;

/**
  Pattern Visitor per operazioni su elementi della biblioteca
 **/
public interface LibraryVisitor {
    void visit(Book book);
    void visit(Magazine magazine); 
    void visit(DVD dvd);
    void visit(Collection collection);
}
