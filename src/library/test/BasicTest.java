// File: src/library/test/BasicTest.java
package library.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import library.Book;
import library.MediaFactory;

public class BasicTest {
    
    @Test
    public void testBookCreation() {
        MediaFactory factory = new MediaFactory();
        Book book = factory.createBook("Test Book", "Test Autore", 2020, "123456789");
        
        assertEquals("Test Book", book.getTitle());
        assertEquals("Test Autore", book.getAuthor());
        assertEquals(2020, book.getYear());
        assertEquals("123456789", book.getIsbn());
    }
    
    @Test
    public void testInputValidation() {
        MediaFactory factory = new MediaFactory();
        
        // Test che titolo vuoto lanci eccezione
        assertThrows(IllegalArgumentException.class, () -> {
            factory.createBook("", "Autore", 2020, "123456789");
        });
        
        // Test che anno invalido lanci eccezione  
        assertThrows(IllegalArgumentException.class, () -> {
            factory.createBook("Titolo", "Autore", 1800, "123456789");
        });
    }
}
