package Models;

public class Related {
    
    String ref;
    String text;

    public Related(String ref, String text) {
        this.ref = ref;
        this.text = text;
    }
    
    // toString
    public String toString() {
        return this.ref + " " + this.text;
    }
}
