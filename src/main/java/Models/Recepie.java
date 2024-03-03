package Models;

import java.util.ArrayList;

public class Recepie {
    String id;
    String title;
    String date;
    ArrayList<Ingredient> ingredients; // Liste d'ingrédients
    ArrayList<String> preparation; // Liste d'étapes
    String comment; 
    Nutrition nutrition;
    Related related;

    // Constructeur
    public Recepie() {
        this.ingredients = new ArrayList<Ingredient>();
        this.preparation = new ArrayList<String>();
    }

    // ---------------------- Getter ----------------------

    // Get le title
    public String getTitle() {
        return title;
    }
    // Get les calories
    public double getCalories() {
        return nutrition.getCalories();
    }
    // Get fat
    public String getFat() {
        return nutrition.getFat();
    }
    // Get les ingredients ( en prennant les ingredient mineur et les majeur )
    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }
    // Get les étapes de préparation
    public ArrayList<String> getSteps() {
        return preparation;
    }

     // Get ingredients ( en prennant les ingredient mineur et les ingredient mineur des majeur )
    public ArrayList<Ingredient> getAllIngredients() {
        ArrayList<Ingredient> allIngredients = new ArrayList<Ingredient>();
        for (Ingredient ingredient: this.ingredients) {
            if (ingredient instanceof IngredientMajeur) {
                allIngredients.addAll(((IngredientMajeur) ingredient).getAllIngredients());
            }
            else {
                allIngredients.add(ingredient);
            }
        }
        return allIngredients;
    }

    // ---------------------- Setter ----------------------

    // Set l'id
    public void setId(String id) {
        this.id = id;
    }
    // Set le title
    public void setTitle(String title) {
        this.title = title;
    }
    // Set la date
    public void setDate(String date) {
        this.date = date;
    }
    // Set un commentaire
    public void setComment(String comment) {
        this.comment = comment;
    }
    // Set la nutrition
    public void setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
    }
    // Set le related
    public void setRelated(Related related) {
        this.related = related;
    }

    // ---------------------- Methodes d'ajout ----------------------

    // Ajoute un ingredient
    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    // Ajoute une étape de preparation
    public void addPreparation(String preparation) {
        this.preparation.add(preparation);
    }

    // Méthode to String
    public String toString() {
        return "Recepie{" +
                "\n id=" + id +
                ",\n title='" + title + '\'' +
                ",\n date='" + date + '\'' +
                ",\n ingredients=" + ingredients +
                ",\n preparation=" + preparation +
                ",\n comment='" + comment + '\'' +
                ",\n nutrition=" + nutrition +
                ",\n related=" + related +
                '}';
    }

}
