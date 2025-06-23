
// ====================
//  Magazine.java
// ====================
package library;

/**
 * Rappresenta una rivista nella biblioteca
 */
public class Magazine implements LibraryItem {
    private static final long serialVersionUID = 1L;
    
    private String title;
    private int issue;
    private String publisher;
    private int year;
    
    public Magazine(String title, int issue, String publisher, int year) {
        // Input sanitization
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Il titolo non può essere nullo o vuoto");
        }
        if (publisher == null || publisher.trim().isEmpty()) {
            throw new IllegalArgumentException("L'editore non può essere nullo o vuoto");
        }
        if (issue < 1) {
            throw new IllegalArgumentException("Il numero del magazine deve essere positivo");
        }
        if (year < 1900 || year > 2030) {
            throw new IllegalArgumentException("L'anno deve essere tra 1900 e 2030");
        }
        
        this.title = title.trim();
        this.issue = issue;
        this.publisher = publisher.trim();
        this.year = year;
    }
    
    public String getTitle() { return title; }
    public int getIssue() { return issue; }
    public String getPublisher() { return publisher; }
    public int getYear() { return year; }
    
    @Override
    public String getDescription() {
        return "Magazine: " + title + " #" + issue + " da " + publisher + " (" + year + ")";
    }
    
    @Override
    public void display() {
        System.out.println(getDescription());
    }
    
    @Override
    public void accept(LibraryVisitor visitor) {
        visitor.visit(this);
    }
}
