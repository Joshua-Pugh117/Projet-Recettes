package models;

import java.util.ArrayList;

public class IngredientMajeur extends Ingredient{
    ArrayList<Ingredient> ingredients;
    ArrayList<String> preparation;

    public IngredientMajeur(String name) {
        super(name);
        this.preparation = new ArrayList<String>();
        this.ingredients = new ArrayList<Ingredient>();
    }


    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
    }

    public void addPreparation(String preparation){
        this.preparation.add(preparation);
    }

    public void removeIngredient(Ingredient ingredient){
        this.ingredients.remove(ingredient);
    }

    public void removePreparation(String preparation){
        this.preparation.remove(preparation);
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public ArrayList<Ingredient> getAllIngredients() {
        ArrayList<Ingredient> result = new ArrayList<Ingredient>();
        for (Ingredient ingredient: this.ingredients) {
            if (ingredient instanceof IngredientMajeur) {
                result.addAll(((IngredientMajeur) ingredient).getAllIngredients());
            }
            else{

                result.add(ingredient);
            }

        }
        return result;
    }

    public ArrayList<String> getPreparation() {
        return preparation;
    }

    public String getName() {
        return name;
    }

    public Double getAmount() {
        return 0.0;
    }

    public String getUnit() {
        return "";
    }

    public String toString() {
        String result = "-M; " + this.name + "\n";
        for (Ingredient ingredient: this.ingredients) {
            result +="      -" + ingredient + "\n";
        }
        for (String preparation: this.preparation) {
            result += preparation + "\n";
        }
        return result;
    }
    
}
