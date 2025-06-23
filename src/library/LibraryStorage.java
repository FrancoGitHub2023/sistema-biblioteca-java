
// ====================
//  LibraryStorage.java (Java I/O + Exception Shielding)
// ====================
package library;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
  Gestisce il salvataggio e caricamento con Exception Shielding
 **/
public class LibraryStorage {
    private static final Logger logger = Logger.getLogger(LibraryStorage.class.getName());
    
    public void saveToFile(Collection collection, String filename) throws LibraryException {
        // Input sanitization per sicurezza
        String sanitizedFilename = sanitizeFilename(filename);
        
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(sanitizedFilename))) {
            out.writeObject(collection);
            logger.log(Level.INFO, "Collezioni salvata su file: {0}", sanitizedFilename);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Errore durante il salvataggio della raccolta su file", e);
            // Exception Shielding: nasconde dettagli interni
            throw new LibraryException("Impossibile salvare la raccolta su file", e);
        }
    }
    
    public Collection loadFromFile(String filename) throws LibraryException {
        String sanitizedFilename = sanitizeFilename(filename);
        
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(sanitizedFilename))) {
            Collection collection = (Collection) in.readObject();
            logger.log(Level.INFO, "Collezione caricata da file: {0}", sanitizedFilename);
            return collection;
        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Errore durante il caricamento della raccolta da file", e);
            // Exception Shielding
            throw new LibraryException("Impossibile caricare la raccolta da file", e);
        }
    }
    
    /**
      Input sanitization per prevenire path traversal attacks
     **/
    private String sanitizeFilename(String filename) {
        if (filename == null || filename.trim().isEmpty()) {
            throw new IllegalArgumentException("Il nome del file non può essere nullo o vuoto");
        }
        
        // Rimuove caratteri pericolosi e path traversal
        return filename.replaceAll("[^a-zA-Z0-9._-]", "")
                      .replaceAll("\\.\\.", "")
                      .trim();
    }
}
