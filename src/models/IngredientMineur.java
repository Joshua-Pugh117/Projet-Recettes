package models;

public class IngredientMineur extends Ingredient{
    float amount;
    String unit;

    public IngredientMineur(String name, float amount) {
        super(name);
        this.amount = amount;
        this.unit = "";
    }

    public IngredientMineur(String name, float amount, String unit) {
        super(name);
        this.amount = amount;
        this.unit = unit;
    }
}
