package Models;

public abstract class Ingredient {
    
    String name;
    
    // Constructeur
    public Ingredient(String name) {
        this.name = name;
    }

    // Get name
    public String getName() {
        return name;
    }

    // Fonction à redéfinir dans les class héritières
    public abstract Double getAmount();
    public abstract String getUnit();

}
