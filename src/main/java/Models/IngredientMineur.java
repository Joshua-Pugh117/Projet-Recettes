package Models;

public class IngredientMineur extends Ingredient{

    String amount; // Montant de la quantité
    String unit; // Unité de meusure

    // Constructeur
    public IngredientMineur(String name, String amount) {
        super(name);
        this.amount = amount;
        this.unit = "";
    }

    // Constructeur avec unité de mesure
    public IngredientMineur(String name, String amount, String unit) {
        super(name);
        this.amount = amount;
        this.unit = unit;
    }

    // Get le montant
    public Double getAmount() {
        return Double.parseDouble(this.amount);
    }

    // Get l'unité de mesure
    public String getUnit() {
        return this.unit;
    }

    // Méthode to String
    public String toString() {
        return this.name + " " + this.amount + " " + this.unit;
    }
}
