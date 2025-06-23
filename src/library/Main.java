package library;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Applicazione Main del sistema biblioteca
 * Disponibili dimostrazione di tutti i design patterns e tecnologie richieste dal progetto
 */
public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static MediaFactory factory = new MediaFactory();
    private static Collection mainCatalog = factory.createCollection("Main Catalog");
    private static LibraryStorage storage = new LibraryStorage();
    private static Scanner scanner = new Scanner(System.in); //Necessario per input utente 
    
    public static void main(String[] args) {
        logger.info("Starting Library Management System");
        
        // Aggiunge elementi demo
        addDemoItems();
        
        boolean running = true;
        while (running) {
            printMenu();
            
            try {
                int choice = Integer.parseInt(scanner.nextLine()); //Parsing esplicito da number text a int (Best practice)
                
                switch (choice) {
                    case 1: displayCatalog(); break;
                    case 2: searchItems(); break;
                    case 3: addNewBook(); break;
                    case 4: addNewMagazine(); break;
                    case 5: addNewDVD(); break;
                    case 6: createNewCollection(); break;
                    case 7: saveCatalog(); break;
                    case 8: loadCatalog(); break;
                    case 9: demonstrateIterator(); break;
                    case 0: 
                        running = false;
                        logger.info("Esci dall'applicazione");
                        break;
                    default:
                        System.out.println("Opzione scelta non valida. Ritenta di nuovo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Attenzione! inserisci un numero valido. ");
            } catch (Exception e) {
                System.out.println("Si è verificato un errore: " + e.getMessage());
                logger.log(Level.SEVERE, "ERRORE INASPETTATO", e); //Registriamo nel log l'evento per debugging
            }
        }
        scanner.close(); //Libera memoria (memory leak) e chiude il collegamento con System.in
    }
    
    private static void printMenu() {
        System.out.println("\n===== BIBLIOTECA SYSTEM =====");
        System.out.println("1. Vedi il Catalogo (Composite Pattern)");
        System.out.println("2. Cerca un titolo (Visitor Pattern)");
        System.out.println("3. Aggiungi un nuovo libro (Factory Pattern)");
        System.out.println("4. Aggiungi un nuovo Magazine (Factory Pattern)");
        System.out.println("5. Aggiungi un nuovo DVD (Factory Pattern)");
        System.out.println("6. Crea una nuova Collezione (Composite)");
        System.out.println("7. Salva il Catalogo (Java I/O + Exception Shielding)");
        System.out.println("8. Carica il Catalogo (Java I/O + Exception Shielding)");
        System.out.println("9. Dimostra Iterator Pattern");
        System.out.println("0. Esci");
        System.out.print("Seleziona un opzione: ");
    }
    
    /**
      Dimostra Factory Pattern e Logging
     **/
    private static void addDemoItems() {
        try {
            //Factory Pattern a lavoro
            Book book1 = factory.createBook("The Lord of the Rings (ENG)", "J.R.R. Tolkien", 1954, "978-0618640157");
            Book book2 = factory.createBook("Clean Code", "Robert C. Martin", 2008, "978-0132350884");
            Book book3 = factory.createBook("Design Patterns", "Gang of Four", 1994, "978-0201633610");
            
            Magazine mag1 = factory.createMagazine("National Geographic Magazine", 256, "National Geographic Society", 2023);
            Magazine mag2 = factory.createMagazine("Scientific American Magazine", 124, "Springer Nature", 2023);
            
            DVD dvd1 = factory.createDVD("Inception", "Christopher Nolan", 148, 2010);
            DVD dvd2 = factory.createDVD("The Matrix", "Andy and Larry Wachowski", 136, 1999);
            
            //Composite Pattern: collezioni dentro collezioni
            Collection fantasyCollection = factory.createCollection("Fantasy Now Collection");
            fantasyCollection.addItem(book1);
            
            Collection techCollection = factory.createCollection("Digital Tech Collection");
            techCollection.addItem(book2);
            techCollection.addItem(book3);
            
            //Aggiungo tutto al catalogo principale (Composite Pattern)
            mainCatalog.addItem(fantasyCollection);
            mainCatalog.addItem(techCollection);
            mainCatalog.addItem(mag1);
            mainCatalog.addItem(mag2);
            mainCatalog.addItem(dvd1);
            mainCatalog.addItem(dvd2);
            
            logger.info("Titoli Demo aggiunti al catalogo riuscita");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Errore nell'aggiungere i titoli demo", e);
        }
    }
    
    /**
      Dimostra Composite Pattern
     **/
    private static void displayCatalog() {
        System.out.println("\n======= CATALOGO LIBRERIA =======");
        mainCatalog.display(); // Composite Pattern: display ricorsivo
        System.out.println("===============================");
    }
    
    /**
      Dimostra Visitor Pattern (principio Open/Closed)
     **/
    private static void searchItems() {
        System.out.print("Cerca un termine: ");
        String searchTerm = scanner.nextLine();
        
        SearchVisitor visitor = new SearchVisitor(searchTerm);
        mainCatalog.accept(visitor); // Visitor Pattern
        
        List<LibraryItem> results = visitor.getResults();
        
        System.out.println("\n===== RISULTATI DELLA RICERCA =====");
        if (results.isEmpty()) {
            System.out.println("No! titolo non trovato: " + searchTerm);
        } else {
            System.out.println("Trovato " + results.size() + " titoli:");
            for (LibraryItem item : results) {
                item.display(); //polimorfismo
            }
        }
    }
    
    /**
      Dimostra Iterator Pattern (Incapsulamento)
     **/
    private static void demonstrateIterator() {
        System.out.println("\n===== ITERATOR PATTERN DEMO =====");
        LibraryIterator iterator = mainCatalog.createIterator();
        
        System.out.println("Iterazione attraverso il catalogo principale: ");
        while (iterator.hasNext()) {
            LibraryItem item = iterator.next();
            System.out.println("- " + item.getDescription()); //Descrizione specifica per ogni tipo di elemento (polimorfismo)
        }
        System.out.println("=================================");
    }
    
    /**
      Dimostra Exception Shielding e Java I/O (salvataggio su file)
     **/
    private static void saveCatalog() {
        try {
            System.out.print("Inserisci il nome del file da salvare: ");
            String filename = scanner.nextLine();
            
            // Input sanitization è gestita dentro LibraryStorage
            storage.saveToFile(mainCatalog, filename);
            
            System.out.println("Catalogo salvato con successo!");
        } catch (LibraryException e) {
            // Exception Shielding: dettagli nascosti
            System.out.println("Errore durante il salvataggio del catalogo: " + e.getMessage());
            logger.log(Level.SEVERE, "Errore durante il salvataggio del catalogo", e);
        }
    }
    
    /**
      Dimostra Exception Shielding e Java I/O (caricamento catalogo da file)
     **/
    private static void loadCatalog() {
        try {
            System.out.print("Inserisci il nome del file da caricare: ");
            String filename = scanner.nextLine();
            
            mainCatalog = storage.loadFromFile(filename);
            
            System.out.println("Catalogo caricato con successo!");
        } catch (LibraryException e) {
            // Exception Shielding
            System.out.println("Errore durante il caricamento del catalogo: " + e.getMessage());
            logger.log(Level.SEVERE, "Errore durante il caricamento del catalogo", e);
        }
    }
    
    private static void addNewBook() {
        try {
            System.out.println("\n=== AGGIUNGI UN NUOVO LIBRO ===");
            System.out.print("Titolo: ");
            String title = scanner.nextLine();
            
            System.out.print("Autore: ");
            String author = scanner.nextLine();
            
            System.out.print("Anno: ");
            int year = Integer.parseInt(scanner.nextLine());
            
            System.out.print("ISBN: ");
            String isbn = scanner.nextLine();
            
            // Factory Pattern + Input Sanitization
            Book book = factory.createBook(title, author, year, isbn);
            mainCatalog.addItem(book);
            
            System.out.println("Libro aggiunto con successo!");
        } catch (NumberFormatException e) {
            System.out.println("Formato anno non valido. Inserisci un numero valido.");
        } catch (IllegalArgumentException e) {
            System.out.println("Input invalido: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Errore durante l'aggiunta del libro: " + e.getMessage());
            logger.log(Level.SEVERE, "Errore durante l'aggiunta del libro", e);
        }
    }
    
    private static void addNewMagazine() {
        try {
            System.out.println("\n=== AGGIUNGI UN NUOVO MAGAZINE ===");
            System.out.print("Titolo: ");
            String title = scanner.nextLine();
            
            System.out.print("Numero Magazine: ");
            int issue = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Publisher/Editore: ");
            String publisher = scanner.nextLine();
            
            System.out.print("Anno: ");
            int year = Integer.parseInt(scanner.nextLine());
            
            Magazine magazine = factory.createMagazine(title, issue, publisher, year);
            mainCatalog.addItem(magazine);
            
            System.out.println("Magazine aggiunto con successo!");
        } catch (NumberFormatException e) {
            System.out.println("Formato anno non valido. Inserisci un numero valido.");
        } catch (IllegalArgumentException e) {
            System.out.println("Input non valido: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Errore durante l'aggiunta del magazine: " + e.getMessage());
            logger.log(Level.SEVERE, "Errore durante l'aggiunta del magazine", e);
        }
    }
    
    private static void addNewDVD() {
        try {
            System.out.println("\n=== AGGIUNGI UN NUOVO DVD ===");
            System.out.print("Titolo: ");
            String title = scanner.nextLine();
            
            System.out.print("Regista: ");
            String director = scanner.nextLine();
            
            System.out.print("Durata (minuti): ");
            int duration = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Anno: ");
            int year = Integer.parseInt(scanner.nextLine());
            
            DVD dvd = factory.createDVD(title, director, duration, year);
            mainCatalog.addItem(dvd);
            
            System.out.println("DVD aggiunto con successo!");
        } catch (NumberFormatException e) {
            System.out.println("Formato anno non valido. Inserisci un numero valido.");
        } catch (IllegalArgumentException e) {
            System.out.println("Input non valido: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Errore durante l'aggiunta del DVD: " + e.getMessage());
            logger.log(Level.SEVERE, "Errore durante l'aggiunta del DVD", e);
        }
    }
    
    private static void createNewCollection() {
        try {
            System.out.println("\n=== CREA UNA NUOVA COLLEZZIONE ===");
            System.out.print("Nome della collezzione: ");
            String name = scanner.nextLine();
            
            Collection collection = factory.createCollection(name);
            mainCatalog.addItem(collection);
            
            System.out.println("Collezione creata con successo!");
        } catch (IllegalArgumentException e) {
            System.out.println("Input non valido: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Errore nel creare la collezione: " + e.getMessage());
            logger.log(Level.SEVERE, "Errore nel creare la collezione", e);
        }
    }
}
