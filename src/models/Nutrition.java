package models;

public class Nutrition {
    String calories;
    String fat;
    String carbohydrates;
    String protein;
    String alchohol;

    public Nutrition(String calories, String fat, String carbohydrates, String protein, String alchohol) {
        this.calories = calories;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.protein = protein;
        if (alchohol == "") {
            this.alchohol = "0%";
        }
        else {
            this.alchohol = alchohol;
        }
    }

    // get calories

    public double getCalories() {
        return Double.parseDouble(calories);
    }
    
    // toString
    public String toString() {
        return "Calories: " + this.calories + "\n" + "Fat: " + this.fat + "\n" + "Carbohydrates: " + this.carbohydrates + "\n" + "Protein: " + this.protein + "\n" + "Alchohol: " + this.alchohol;
    }
}
