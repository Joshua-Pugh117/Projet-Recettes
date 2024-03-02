package Models;

import java.util.ArrayList;

public class Recepie {
    String id;
    String title;
    String date;
    ArrayList<Ingredient> ingredients;
    ArrayList<String> preparation;
    String comment;
    Nutrition nutrition;
    Related related;

    public Recepie() {
        this.ingredients = new ArrayList<Ingredient>();
        this.preparation = new ArrayList<String>();
    }

    // set id
    public void setId(String id) {
        this.id = id;
    }

    // set title
    public void setTitle(String title) {
        this.title = title;
    }

    // set date
    public void setDate(String date) {
        this.date = date;
    }

    //add ingredient
    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    

    //add preparation
    public void addPreparation(String preparation) {
        this.preparation.add(preparation);
    }

    //set comment
    public void setComment(String comment) {
        this.comment = comment;
    }

    //set nutrition
    public void setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
    }

    //set related
    public void setRelated(Related related) {
        this.related = related;
    }

    // get ingredients
    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    // get steps
    public ArrayList<String> getSteps() {
        return preparation;
    }

    public String getId() {
        return id;
    }
    
    public String getDate() {
        return date;
    }

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

    // get title
    public String getTitle() {
        return title;
    }

    // get calories
    public double getCalories() {
        return nutrition.getCalories();
    }

    public String getComment() {
        return comment;
    }

    public Nutrition getNutrition() {
        return nutrition;
    }
    
    public String getFat() {
        return nutrition.getFat();
    }

    public Related getRelated() {
        return related;
    }
    
    

    // toString
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
