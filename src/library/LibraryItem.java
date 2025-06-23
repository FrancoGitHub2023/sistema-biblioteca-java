// ====================
// LibraryItem.java
// ====================
package library;
import java.io.Serializable;

/**
  Interfaccia base per tutti gli elementi della biblioteca.
  Implementa Serializable per supportare Java I/O
 **/
public interface LibraryItem extends Serializable {
    String getTitle();
    String getDescription();
    void display();
    void accept(LibraryVisitor visitor);
}
