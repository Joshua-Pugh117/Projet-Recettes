import models.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Repositories.*;

public class App {
    public static void main(String[] args) throws Exception {

        RecepieRepo recepieRepo = new RecepieRepo("src\\recipes.xml");
        // IngredientMajeur x = (IngredientMajeur)
        // recepieRepo.getRecepies().get(4).getIngredients().get(1);
        // System.out.println(x.getIngredients().get(0));

        // test listRecepieTitles
        // listRecepieTitles(recepieRepo);

        // test calculateTotalEggs
        // System.out.println(calculateTotalEggs(recepieRepo));

        // test getRecepiesWithOliveOil
        // System.out.println(getRecepiesWithOliveOil(recepieRepo));

        // test calculateEggsPerRecepie
        // System.out.println(calculateEggsPerRecepie(recepieRepo));

        // test getLowCalorieRecepies
        // System.out.println(getLowCalorieRecepies(recepieRepo));

        // test getSugarInZuppaInglese
        // System.out.println(getSugarInZuppaInglese(recepieRepo));

        // test printFirstTwoStepsOfZuppaInglese
        // printFirstTwoStepsOfZuppaInglese(recepieRepo);

        // test getComplexRecepies
        // System.out.println(getComplexRecepies(recepieRepo));

        // test getRecepiesWithoutButter
        System.out.println(getRecepiesWithoutButter(recepieRepo));

    }

    public static void listRecepieTitles(RecepieRepo recepieRepo) {
        recepieRepo.getRecepies().stream()
                .map(Recepie::getTitle)
                .forEach(System.out::println);
    }

    public static double calculateTotalEggs(RecepieRepo recepieRepo) {
        return recepieRepo.getRecepies().stream()
                .flatMap(recepie -> recepie.getAllIngredients().stream())
                .filter(ingredient -> ingredient.getName().contains("egg"))
                .mapToDouble(Ingredient::getAmount)
                .sum();
    }

    public static List<String> getRecepiesWithOliveOil(RecepieRepo recepieRepo) {
        return recepieRepo.getRecepies().stream()
                .filter(recepie -> recepie.getAllIngredients().stream()
                        .anyMatch(ingredient -> ingredient.getName().contains("olive oil")))
                .map(Recepie::getTitle)
                .collect(Collectors.toList());
    }

    public static Map<String, Object> calculateEggsPerRecepie(RecepieRepo recepieRepo) {
        return recepieRepo.getRecepies().stream()
                .collect(Collectors.toMap(
                        Recepie::getTitle,
                        recepie -> recepie.getAllIngredients().stream()
                                .filter(ingredient -> ingredient.getName().contains("egg"))
                                .mapToDouble(Ingredient::getAmount)
                                .sum()));
    }

    public static List<Recepie> getLowCalorieRecepies(RecepieRepo recepieRepo) {
        return recepieRepo.getRecepies().stream()
                .filter(recepie -> recepie.getCalories() < 500)
                .collect(Collectors.toList());
    }

    public static Object getSugarInZuppaInglese(RecepieRepo recepieRepo) {
        return recepieRepo.getRecepies().stream()
                .filter(recepie -> recepie.getTitle().equals("Zuppa Inglese"))
                .flatMap(recepie -> recepie.getAllIngredients().stream())
                .filter(ingredient -> ingredient.getName().contains("sugar"))
                .collect(Collectors.toMap(
                        Ingredient::getUnit,
                        Ingredient::getAmount));
    }

    public static void printFirstTwoStepsOfZuppaInglese(RecepieRepo recepieRepo) {
        recepieRepo.getRecepies().stream()
                .filter(recepie -> recepie.getTitle().equals("Zuppa Inglese"))
                .findFirst()
                .map(recepie -> recepie.getSteps())
                .stream().forEach(System.out::println);
    }

    public static List<Recepie> getComplexRecepies(RecepieRepo recepieRepo) { // a revoir !
        return recepieRepo.getRecepies().stream()
                       .filter(recepie -> recepie.getSteps().size() > 5)
                       .collect(Collectors.toList());
    }

    public static List<String> getRecepiesWithoutButter(RecepieRepo recepieRepo) {
        return recepieRepo.getRecepies().stream()
                       .filter(recepie -> recepie.getAllIngredients().stream()
                                                 .noneMatch(ingredient -> ingredient.getName().contains("butter")))
                       .map(Recepie::getTitle)                          
                       .collect(Collectors.toList());
    }



}
