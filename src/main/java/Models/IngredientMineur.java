package Models;

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

    // get amout
    public Double getAmount() {
        return Double.parseDouble(this.amount);
    }

    // get unit
    public String getUnit() {
        return this.unit;
    }

    // toString
    public String toString() {
        return this.name + " " + this.amount + " " + this.unit;
    }
}
