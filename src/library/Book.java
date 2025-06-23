// ====================
//  Book.java
// ====================
package library;

/**
  Rappresenta un libro nella biblioteca
  Implementa LibraryItem per polimorfismo
 **/

public class Book implements LibraryItem {
    private static final long serialVersionUID = 1L; //controllo versione se modifico la classe
    
    private String title;
    private String author;
    private int year;
    private String isbn;
    
    /**
      Costruttore con validazione input
     **/
    public Book(String title, String author, int year, String isbn) {
        // Input sanitization
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Non è ammesso un titolo nullo o vuoto.");
        }
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("L'autore non può essere nullo o vuoto.");
        }
        if (year < 1000 || year > 2030) {
            throw new IllegalArgumentException("L'anno deve essere compreso tra 1000 e il 2030.");
        }
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("ISBN non può essere nullo o vuoto.");
        }
        
        this.title = title.trim();
        this.author = author.trim();
        this.year = year;
        this.isbn = isbn.trim();
    }
    
    // Getters
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYear() { return year; }
    public String getIsbn() { return isbn; }
    
    @Override
    public String getDescription() {
        return "Book: " + title + " da " + author + " (" + year + ")";
    }
    
    @Override
    public void display() {
        System.out.println(getDescription());
    }
    
    @Override
    public void accept(LibraryVisitor visitor) {
        visitor.visit(this); //Double Dispatch il visitor riceve esattamente il tipo Book
    }
    
    //Override dei metodi Object 
    
    @Override
    public String toString() {
        return getDescription();
    }
    
    @Override //Confronto tra Book con stesso ISBN
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return isbn.equals(book.isbn);
    }
    
    @Override // Per collezioni implicato da a.equals(b). Controllo stesso elemento in collezioni
    public int hashCode() {
        return isbn.hashCode();
    }
}
