package Models;

public class Nutrition {

    String calories;
    String fat;
    String carbohydrates;
    String protein;
    String alchohol;

    // Constructeur
    public Nutrition(String calories, String fat, String carbohydrates, String protein, String alchohol) {

        this.calories = calories;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.protein = protein;

        if (alchohol == "") { // Si ce n'es pas preciser
            this.alchohol = "0%"; // Mettre à 0%
        }else { 
            this.alchohol = alchohol;
        }
    }

    // Get les calories
    public double getCalories() {
        return Double.parseDouble(calories);
    }

    // Get fat
    public String getFat() {
        return fat;
    }
    
    // Méthode to String
    public String toString() {
        return "Calories: " + this.calories + "\n" + "Fat: " + this.fat + "\n" + "Carbohydrates: " + this.carbohydrates + "\n" + "Protein: " + this.protein + "\n" + "Alchohol: " + this.alchohol;
    }
}
