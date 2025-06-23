// ====================
//  LibraryException.java
// ====================
package library;

/**
  Eccezione personalizzata per il sistema biblioteca
  Implementa Exception Shielding pattern
 **/
public class LibraryException extends Exception {
    private static final long serialVersionUID = 1L;
    
    public LibraryException(String message) {
        super(message);
    }
    
    public LibraryException(String message, Throwable cause) {
        super(message, cause);
    }
}
