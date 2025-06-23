
// ====================
//  DVD.java
// ====================
package library;

/**
 * Rappresenta un DVD nella biblioteca
 */
public class DVD implements LibraryItem {
    private static final long serialVersionUID = 1L;
    
    private String title;
    private String director;
    private int duration;
    private int year;
    
    public DVD(String title, String director, int duration, int year) {
        // Input sanitization
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Il titolo non può essere nullo o vuoto");
        }
        if (director == null || director.trim().isEmpty()) {
            throw new IllegalArgumentException("Il registra non può essere nullo o vuoto");
        }
        if (duration < 1) {
            throw new IllegalArgumentException("La durata deve essere positiva");
        }
        if (year < 1900 || year > 2030) {
            throw new IllegalArgumentException("L'anno deve essere compreso tra 1900 e 2030");
        }
        
        this.title = title.trim();
        this.director = director.trim();
        this.duration = duration;
        this.year = year;
    }
    
    public String getTitle() { return title; }
    public String getDirector() { return director; }
    public int getDuration() { return duration; }
    public int getYear() { return year; }
    
    @Override
    public String getDescription() {
        return "DVD: " + title + " diretto da " + director + " (" + year + ")";
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
