package Repositories;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Models.*;

public class RecepieRepo {

    ArrayList<Recepie> recepies;

    public RecepieRepo(String path)  throws Exception {

        this.recepies = new ArrayList<Recepie>();

        File xmlFile = new File(path); // Récupère le fichier xml avec le chemin
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); // Fait une nouvelle factory
        DocumentBuilder builder = factory.newDocumentBuilder(); // Fait un builder avec le factory
        Document doc = builder.parse(xmlFile); // Parse le document xml avec le builder

        doc.getDocumentElement(); // Récupère les éléments parser

        NodeList recipeNodes = doc.getElementsByTagName("rcp:recipe"); // Récupère les éléments appeler "rcp:recipe"
        for (int i = 0; i < recipeNodes.getLength(); i++) { // Pour toutes les recettes trouvé

            Node recipeNode = recipeNodes.item(i); // Récupère l'éléments
            Element recipeElement = (Element) recipeNode; // Fait une conversion
            String id = recipeElement.getAttribute("id"); // Récupère l'id
            String title = recipeElement.getElementsByTagName("rcp:title").item(0).getTextContent(); // Récupère le titre
            String date = recipeElement.getElementsByTagName("rcp:date").item(0).getTextContent(); // Récupère la date

            Recepie recepie = new Recepie(); // Crée une nouvelle recette avec :
            recepie.setId(id); // Un Id
            recepie.setTitle(title); // Un titre
            recepie.setDate(date); // Une date
// ------------------------------------- Ingredient -------------------------------------

            NodeList ingredientNodes = recipeElement.getElementsByTagName("rcp:ingredient"); // Récupère tout les ingrédients
            for (int j = 0; j < ingredientNodes.getLength(); j++) { // Boucle sur le nombre d'ingredient
                Node ingredientNode = ingredientNodes.item(j); // Récupère l'élément

                if (ingredientNode.getParentNode().equals(recipeElement)) { // Vérifie si le nœud parent de l'élément d'ingrédient correspond au nœud de recette actuel
                    Element ingredientElement = (Element) ingredientNode; // Convertis l'élément
                    if (ingredientElement.getAttribute("amount").equals("")) { // Si il est vide
                        IngredientMajeur ingredient = Insert_Ingredients(ingredientElement); // l'enregistrer comme un ingrédient majeur
                        recepie.addIngredient(ingredient); // Ajoute l'ingredient à la recette
                    }
                    else{ // Sinon c'est un ingrédient mineur
                        IngredientMineur ingredient = new IngredientMineur( // Création de l'ingrédient :
                                ingredientElement.getAttribute("name"), // Récupère le nom
                                ingredientElement.getAttribute("amount"), // Récupère le montant
                                ingredientElement.getAttribute("unit")); // Et l'unité
                        recepie.addIngredient(ingredient); // Ajoute l'ingredient à la recette
                    }
                }
            }
            // ------------------------------------- Step -------------------------------------
            
            NodeList stepsNodes = recipeElement.getElementsByTagName("rcp:step"); // Récupère toutes les étapes
            for (int j = 0; j < stepsNodes.getLength(); j++) { // Boucle sur le nombre d'étapes
                Node stepNode = stepsNodes.item(j); // Récupère l'éléments

                if (stepNode.getParentNode().getParentNode().equals(recipeElement)) { // Vérifie si le nœud parent de l'élément d'ingrédient correspond au nœud de recette actuel
                    Element stepElement = (Element) stepNode; // Convertis l'élément
                    recepie.addPreparation(stepElement.getTextContent()); // Ajoute à la préparation de la recette
                }
            }

    // ------------------------------------- Nutrition -------------------------------------

            NodeList nutritionNodes = recipeElement.getElementsByTagName("rcp:nutrition"); // Récupère l'élément "rcp:nutrition" 
            Element nutritionNode = (Element) nutritionNodes.item(0); // Le convertis

            String calories = nutritionNode.getAttribute("calories"); // récupère les calories
            String fat = nutritionNode.getAttribute("fat"); // Récupère le fat
            String carbohydrates = nutritionNode.getAttribute("carbohydrates"); // Récupère les carbohydrates
            String protein = nutritionNode.getAttribute("protein"); // Récupère les protéïnes
            String alcohol = nutritionNode.getAttribute("alcohol"); // Récupère l'alcool

            Nutrition nutrition = new Nutrition(calories, fat, carbohydrates, protein, alcohol); // Créer l'élément nutrition 
            recepie.setNutrition(nutrition); // et l'ajoute à la recette
// ------------------------------------- Comment -------------------------------------

            if (recipeElement.getElementsByTagName("rcp:comment").item(0) != null) { // Récupère les commentaires si il y en a

                String comment = recipeElement.getElementsByTagName("rcp:comment").item(0).getTextContent(); // Récupère le contenu
                recepie.setComment(comment); // Ajoute le commentaire à la recette
            }

    // ------------------------------------- Related -------------------------------------

            NodeList relatedNodes = recipeElement.getElementsByTagName("rcp:related"); // Récupère les éléments en rapport
            Element relatedNode = (Element) relatedNodes.item(0); // Convertis

            if (relatedNode != null) { // Si il n'est pas null
                String ref = relatedNode.getAttribute("ref"); // Récupère la ref
                String related = relatedNode.getTextContent(); // Récupère le texte

                Related relatedObj = new Related(ref, related); // Crée un élément Related
                recepie.setRelated(relatedObj); // L'ajoute dans la recette
            }

            this.recepies.add(recepie); // Ajoute la recette au livre de recette
        }
    
    }
    
    // ------------------------------------- Fonctions -------------------------------------

    // Fonction pour récupérer les recettes
    public ArrayList<Recepie> getRecepies() {
        return recepies;
    }
    // Fonction permettant de traiter les ingrédient majeur 
    private IngredientMajeur Insert_Ingredients(Element ingredientElement) {

        IngredientMajeur ingredient = new IngredientMajeur(ingredientElement.getAttribute("name")); // Crée un Indrédient majeur avec son nom

        // ------------------------------------- ingredient -------------------------------------

        NodeList subIngredientNodes = ingredientElement.getElementsByTagName("rcp:ingredient"); // Récupère les sous ingrédients
        
        for (int y = 0; y < subIngredientNodes.getLength(); y++) { // Boucle sur le nombre de sous ingrédient
            Node subIngredientNode = subIngredientNodes.item(y); // Récupère l'élement
            Element subIngredientElement = (Element) subIngredientNode; // Le convertis

            if (subIngredientElement.getParentNode().equals(ingredientElement)){ // Vérifie si le nœud parent de l'élément d'ingrédient correspond au nœud de l'ingrédient actuel
                if (subIngredientElement.getAttribute("amount").equals("")) { // Si il n'as pas de montant
                    IngredientMajeur subingredient = Insert_Ingredients(subIngredientElement); // Créer un ingrédient majeur
                    ingredient.addIngredient(subingredient); // Ajouter le sous ingrédient à l'ingrédient 
                }
                else{ // Sinon
                    IngredientMineur subIngredient = new IngredientMineur(// Création de l'ingrédient :
                            subIngredientElement.getAttribute("name"), // Récupère le nom
                            subIngredientElement.getAttribute("amount"), // Récupère le montant
                            subIngredientElement.getAttribute("unit"));// Et l'unité
                    ingredient.addIngredient(subIngredient); // Ajoute le sous ingrédient à l'ingrédient
                }
            }
        }
        // ------------------------------------- Step -------------------------------------

        NodeList preparationNodes = ingredientElement.getElementsByTagName("rcp:step"); // Récupère la liste des étapes des sous ingrédients
        for (int z = 0; z < preparationNodes.getLength(); z++) { // Boucle sur le nombre d'étapes

            if (preparationNodes.item(z).getParentNode().getParentNode().equals(ingredientElement)) { // Vérifie si le nœud parent de l'élément d'étape correspond au nœud de l'ingrédient actuel
                Node preparationNode = preparationNodes.item(z); // Récupère l'élément
                Element preparationElement = (Element) preparationNode; // Le convertis
                ingredient.addPreparation(preparationElement.getTextContent()); // L'ajoute à la préparation de l'ingrédient
            }
        }
        return ingredient; // Renvois l'ingrédient 
    }

}
