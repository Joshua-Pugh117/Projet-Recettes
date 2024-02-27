package models;

public class IngredientMineur extends Ingredient{
    String amount;
    String unit;

    public IngredientMineur(String name, String amount) {
        super(name);
        this.amount = amount;
        this.unit = "";
    }

    public IngredientMineur(String name, String amount, String unit) {
        super(name);
        this.amount = amount;
        this.unit = unit;
    }

    // toString
    public String toString() {
        return this.name + " " + this.amount + " " + this.unit;
    }
}
