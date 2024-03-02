package Models;


public abstract class Ingredient {
    String name;
    
    public Ingredient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract Double getAmount();

    public abstract String getUnit();

}
