package models;

public class Nutrition {
    int calories;
    int fat;
    int carbohydrates;
    int protein;
    int alchohol;


    public Nutrition(int calories, int fat, int carbohydrates, int protein) {
        this.calories = calories;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.protein = protein;
        this.alchohol = 0;
    }

    public Nutrition(int calories, int fat, int carbohydrates, int protein, int alchohol) {
        this.calories = calories;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.protein = protein;
        this.alchohol = alchohol;
    }
    
}
