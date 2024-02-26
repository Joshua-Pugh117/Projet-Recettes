package models;

import java.util.ArrayList;

public class IngredientMajeur extends Ingredient{
    ArrayList<IngredientMineur> ingredients;
    ArrayList<String> preparation;

    public IngredientMajeur(String name) {
        super(name);
        this.preparation = new ArrayList<String>();
        this.ingredients = new ArrayList<IngredientMineur>();
    }


    public void addIngredient(IngredientMineur ingredient){
        this.ingredients.add(ingredient);
    }

    public void addPreparation(String preparation){
        this.preparation.add(preparation);
    }

    public void removeIngredient(IngredientMineur ingredient){
        this.ingredients.remove(ingredient);
    }

    public void removePreparation(String preparation){
        this.preparation.remove(preparation);
    }

    public ArrayList<IngredientMineur> getIngredients() {
        return ingredients;
    }

    public ArrayList<String> getPreparation() {
        return preparation;
    }

    public String getName() {
        return name;
    }


    
}
