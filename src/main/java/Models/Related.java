package Models;

public class Related {
    
    String ref;
    String text;

    // Constructeur
    public Related(String ref, String text) {
        this.ref = ref;
        this.text = text;
    }
    
    // Méthode to String
    public String toString() {
        return this.ref + " " + this.text;
    }
}
