package Models;

import java.util.ArrayList;

public class IngredientMajeur extends Ingredient{

    ArrayList<Ingredient> ingredients; // Liste de sous ingredients
    ArrayList<String> preparation; // Liste d'étapes

    // Constructeur
    public IngredientMajeur(String name) {
        super(name);
        this.preparation = new ArrayList<String>();
        this.ingredients = new ArrayList<Ingredient>();
    }

    // Get le nom
    public String getName() {
        return name;
    }

    // Get le montant
    public Double getAmount() {
        return 0.0;
    }

    // Get l'unité
    public String getUnit() {
        return "";
    }

    // Get ingredients ( en prennant les ingredient mineur et les majeur )
    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    // Get ingredients ( en prennant les ingredient mineur et les ingredient mineur des majeur )
    public ArrayList<Ingredient> getAllIngredients() {
        ArrayList<Ingredient> result = new ArrayList<Ingredient>(); // Fait une liste d'ingrédients
        for (Ingredient ingredient: this.ingredients) { // Pour tout les ingrédients dans cette ingrédient majeur :
            if (ingredient instanceof IngredientMajeur) { // Si il y a encore un ingrédient majeur
                result.addAll(((IngredientMajeur) ingredient).getAllIngredients()); // Ajoute tout les ingrédients à la liste
            }
            else{ // Sinon
                result.add(ingredient); // Ajoute l'ingrédient à la liste
            }
        }
        return result; // Retourne la liste d'ingredients
    }

    // Get la préparation
    public ArrayList<String> getPreparation() {
        return preparation;
    }

    // Ajoute un ingrédient
    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
    }

    // Ajoute une étape de préparation
    public void addPreparation(String preparation){
        this.preparation.add(preparation);
    }

    // Supprime un Ingredient si besoin
    public void removeIngredient(Ingredient ingredient){
        this.ingredients.remove(ingredient);
    }

    // Supprime une étape si besoin 
    public void removePreparation(String preparation){
        this.preparation.remove(preparation);
    }

    // Méthode to String
    public String toString() {
        String result = "-M; " + this.name + "\n";
        for (Ingredient ingredient: this.ingredients) { // Pour tout les ingredients
            result +="      -" + ingredient + "\n";
        }
        for (String preparation: this.preparation) { // Pour toute les étapes
            result += preparation + "\n";
        }
        return result;
    }
    
}
