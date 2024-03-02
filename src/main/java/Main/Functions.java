package Main;

import Models.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.function.Function;
import Repositories.*;
import java.util.Comparator;

public class Functions {
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
    
        // Méthode qui retourne les recettes ayant des ingrédients en communs avec la recette Zuppa Inglese
    public static List<String> getRecepiesCommonWithZuppaInglese(RecepieRepo recepieRepo) {
        Recepie ZuppaInglese = recepieRepo.getRecepies().stream() // Récupère les recettes
                .filter(recepie -> recepie.getTitle().equals("Zuppa Inglese")) // Récupère Zuppa Inglese
                .findFirst() // Retourne un Optional<Recepie>
                .orElse(null); // Extrait du type Optional

        return recepieRepo.getRecepies().stream()// Récupère les recettes
                .filter(recepie -> !recepie.getTitle().equals("Zuppa Inglese")) // Récupère les recettes qui ne sont pas Zuppa Inglese
                .filter(recepie -> recepie.getAllIngredients().stream() // Récupères tout les ingrédients
                        .anyMatch(ingredient -> ZuppaInglese.getIngredients().stream() // Récupères tout les ingrédients de Zuppa Inglese
                                // Vérification si l'ingrédiant de Zuppa Inglese = l'ingrédient testé
                                .anyMatch(ZuppaIngr -> ZuppaIngr.getName().equals(ingredient.getName())))) 
                .map(Recepie::getTitle) // Récupère le nom de la recette
                .collect(Collectors.toList()); // Met les résulats dans une liste
    }   

    // Méthode qui affiche la recette la plus calorique
    public static void printMostCaloricRecepie(RecepieRepo recepieRepo) {
        recepieRepo.getRecepies().stream() // Récupère les recettes
                .max(Comparator.comparingDouble(Recepie::getCalories)) // Trouve celle qui a le plus de calories
                .stream().forEach((recepie)-> System.out.println("La recette la plus calorique est : " + recepie.getTitle()
                        + "\n Nombre de calories : " + recepie.getCalories())); // Affiche les informations
    }

    // Méthode qui retourne l’unité la plus fréquente
    public static String getMostFrequentUnit(RecepieRepo recepieRepo) {
        return recepieRepo.getRecepies().stream() // Récupère les recettes
                .flatMap(recepie -> recepie.getAllIngredients().stream()) // Récupère toute les ingrédients
                .map(Ingredient::getUnit)
                .filter(unit -> !unit.isEmpty()) 
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())) // Fait un groupement par unité
                        .entrySet().stream() // Pour toutes les unitées
                        .max(Map.Entry.comparingByValue()) // On les compare à leur nombre et on prends le max
                        .map(Map.Entry::getKey) // Récupère l'unité
                        .orElse(null); // Permet d'extraire du type Optional<String>
    }

    // Méthode qui calcule le nombre d’ingrédients par recette 
    public static Map<String, String> numberIngredientsPerRecipe(RecepieRepo recepieRepo) {
        return recepieRepo.getRecepies().stream() // Récupère les recettes
                .collect(Collectors.toMap( // Fait un map
                        Recepie::getTitle, // Le titre de la recette
                        recepie -> String.valueOf(recepie.getAllIngredients().size()))); // Le nombre d'ingrédients
    }

    // Méthode qui retourne la recette comportant le plus de fat
    public static Recepie getRecepieWithMostFat(RecepieRepo recepieRepo) {
        return recepieRepo.getRecepies().stream() // Récupère les recettes
                .max(Comparator.comparing(Recepie::getFat)) // Compare les fat entre eux et récupère le plus grand
                .orElse(null); // Extrait de l'optional <Recepie>
    }

    // Méthode qui calcule l’ingrédient le plus utilisé
    public static String calculateMostUsedIngredient(RecepieRepo recepieRepo) {
        return recepieRepo.getRecepies().stream() // Récupère les recettes
                .flatMap(recepie -> recepie.getAllIngredients().stream()) // Récupère tout les ingrédients
                .collect(Collectors.groupingBy(Ingredient::getName, Collectors.counting())) // Les regroupes par nom
                    .entrySet().stream() // Pour tous les ingrédients
                    .max(Map.Entry.comparingByValue()) // récupère l'ingrédient qui à été appeler le plus de fois
                    .map(Map.Entry::getKey) // Récupère le nom de l'ingrédient
                    .orElse(null); // Extrait de l'optional <String>
    }

    // Méthode qui affiche les recettes triées par nombre d’ingrédients
    public static void printRecipesSortedByIngredientCount(RecepieRepo recepieRepo) {
        recepieRepo.getRecepies().stream() // Récupère les recettes
                .sorted(Comparator.comparingInt(recipe -> recipe.getAllIngredients().size())) // Trie par le nombre d'ingrédients
                .forEach(recipe ->  // Affichage
                    System.out.println("La recette : " + recipe.getTitle() + " a " 
                    + recipe.getAllIngredients().size() + " ingredients" ));
    }

    // Méthode qui affiche pour chaque ingrédient, les recettes qui l’utilisent
    public static void printRecipesByIngredient(RecepieRepo recepieRepo) {
        Map<String, List<String>> recipesByIngredient = recepieRepo.getRecepies().stream() // Récupère les recettes
                .flatMap(recepie -> recepie.getAllIngredients().stream() // récupère tout les ingrédients
                        .map(ingredient -> Map.entry(ingredient.getName(), recepie.getTitle()))) // Fait un map de nom de l'ingrédient et du titre de la recette
                .collect(Collectors.groupingBy( // On regroupe
                        Map.Entry::getKey, // Par nom d'ingrédient
                        Collectors.mapping( // Et par 
                                Map.Entry::getValue, // Les noms des recettes
                                Collectors.toList()))); // en liste     

        // Afficher les recettes par ingrédient
        recipesByIngredient.forEach((ingredient, recipes) -> {
                System.out.println("l'ingrédient "+'"' + ingredient + '"' + " a comme recette associé :");
                System.out.println(recipes + "\n");
                });
    }

    // Méthode qui calcule la répartition des recettes par étape de réalisation
    /*
        Ici nous n'étions pas sur de savoir ce que l'on devais "calculer"
        Donc c'est un map qui associe le nombre d'étapes et les recettes qui ont ce nombre d'étape
     */

    public static Map<Integer, List<String>> calculateStepDistribution(RecepieRepo recepieRepo) {
        return recepieRepo.getRecepies().stream() // Récupère les recettes 
                .collect(Collectors.groupingBy( // Renvois un map regrouper par
                        recepie -> recepie.getSteps().size(), // Le nombre d'étapes 
                        Collectors.mapping(Recepie::getTitle, Collectors.toList())) // Liste des recettes qui ont ce nombre 
                        );
    }

    // Méthode qui calcule la recette la plus facile (avec le moins d’étape)
    public static String getEasiestRecipe(RecepieRepo recepieRepo) {
        return calculateStepDistribution(recepieRepo).entrySet().stream() // Récupère les ensembles nombre étape et recettes
                .min(Map.Entry.comparingByKey()) // Prends le minimum en comparant les clés ensemble
                .flatMap(entry -> entry.getValue().stream().findFirst()) // Prends la première la valeur
                .orElse(null); // Extraction du Optional<String>
    }

}



