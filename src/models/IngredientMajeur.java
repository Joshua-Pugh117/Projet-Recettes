package models;

import java.util.ArrayList;

public class IngredientMajeur extends Ingredient{
    ArrayList<IngredientMineur> ingredients;
    String preparation;

    public IngredientMajeur(String name) {
        super(name);
        this.preparation = "";
        this.ingredients = new ArrayList<IngredientMineur>();
    }

    public IngredientMajeur(String name, String preparation) {
        super(name);
        this.preparation = preparation;
        this.ingredients = new ArrayList<IngredientMineur>();
    }


    public void addIngredient(IngredientMineur ingredient){
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(IngredientMineur ingredient){
        this.ingredients.remove(ingredient);
    }

    public ArrayList<IngredientMineur> getIngredients() {
        return ingredients;
    }

    public String getName() {
        return name;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }


    
}
