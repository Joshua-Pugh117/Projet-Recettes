package Repositories;

import java.io.File;
import java.util.ArrayList;

import javax.management.MalformedObjectNameException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import models.*;

public class RecepieRepo {
    ArrayList<Recepie> recepies;

    public RecepieRepo(String path)  throws Exception {
        this.recepies = new ArrayList<Recepie>();

        File xmlFile = new File(path);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        doc.getDocumentElement();

        NodeList recipeNodes = doc.getElementsByTagName("rcp:recipe");
        for (int i = 0; i < recipeNodes.getLength(); i++) {

            Node recipeNode = recipeNodes.item(i);
            Element recipeElement = (Element) recipeNode;
            String id = recipeElement.getAttribute("id");
            String title = recipeElement.getElementsByTagName("rcp:title").item(0).getTextContent();
            String date = recipeElement.getElementsByTagName("rcp:date").item(0).getTextContent();

            Recepie recepie = new Recepie();
            recepie.setId(id);
            recepie.setTitle(title);
            recepie.setDate(date);

            NodeList ingredientNodes = recipeElement.getElementsByTagName("rcp:ingredient");
            for (int j = 0; j < ingredientNodes.getLength(); j++) {
                Node ingredientNode = ingredientNodes.item(j);
                if (ingredientNode.getParentNode().equals(recipeElement)) {
                    Element ingredientElement = (Element) ingredientNode;
                    if (ingredientElement.getAttribute("amount").equals("")) {
                        IngredientMajeur ingredient = Insert_Ingredients(ingredientElement);
                        recepie.addIngredient(ingredient);
                    }
                    else{
                        IngredientMineur ingredient = new IngredientMineur(ingredientElement.getAttribute("name"), ingredientElement.getAttribute("amount"), ingredientElement.getAttribute("unit"));
                        recepie.addIngredient(ingredient);
                    }
                }
            }
            
            NodeList stepsNodes = recipeElement.getElementsByTagName("rcp:step");
            for (int j = 0; j < stepsNodes.getLength(); j++) {
                Node stepNode = stepsNodes.item(j);
                if (stepNode.getParentNode().getParentNode().equals(recipeElement)) {
                    Element stepElement = (Element) stepNode;
                    // System.out.println("--" + stepElement.getTextContent());
                    recepie.addPreparation(stepElement.getTextContent());
                }
            }

            NodeList nutritionNodes = recipeElement.getElementsByTagName("rcp:nutrition");
            Element nutritionNode = (Element) nutritionNodes.item(0);
            String calories = nutritionNode.getAttribute("calories");
            String fat = nutritionNode.getAttribute("fat");
            String carbohydrates = nutritionNode.getAttribute("carbohydrates");
            String protein = nutritionNode.getAttribute("protein");
            String alcohol = nutritionNode.getAttribute("alcohol");

            Nutrition nutrition = new Nutrition(calories, fat, carbohydrates, protein, alcohol);
            recepie.setNutrition(nutrition); 


            // System.out.println(calories);
            // System.out.println(fat);
            // System.out.println(carbohydrates);
            // System.out.println(protein);
            // System.out.println(alcohol);

            if (recipeElement.getElementsByTagName("rcp:comment").item(0) != null) {

                String comment = recipeElement.getElementsByTagName("rcp:comment").item(0).getTextContent();
                recepie.setComment(comment);
                // System.out.println(comment);
            }

            // String related =
            // recipeElement.getElementsByTagName("rcp:related").item(0).getTextContent();
            NodeList relatedNodes = recipeElement.getElementsByTagName("rcp:related");
            Element relatedNode = (Element) relatedNodes.item(0);
            if (relatedNode != null) {
                String ref = relatedNode.getAttribute("ref");
                String related = relatedNode.getTextContent();

                Related relatedObj = new Related(ref, related);
                recepie.setRelated(relatedObj);

                // System.out.println(ref);
                // System.out.println(related);

            }
            this.recepies.add(recepie);
            
        }


    }

    public ArrayList<Recepie> getRecepies() {
        return recepies;
    }

    private IngredientMajeur Insert_Ingredients(Element ingredientElement) {

        IngredientMajeur ingredient = new IngredientMajeur(ingredientElement.getAttribute("name"));
        
        NodeList subIngredientNodes = ingredientElement.getElementsByTagName("rcp:ingredient");
        for (int y = 0; y < subIngredientNodes.getLength(); y++) {
            Node subIngredientNode = subIngredientNodes.item(y);
            Element subIngredientElement = (Element) subIngredientNode;
            if (subIngredientElement.getParentNode().equals(ingredientElement)){


                if (subIngredientElement.getAttribute("amount").equals("")) {
                    IngredientMajeur subingredient = Insert_Ingredients(subIngredientElement);
                    ingredient.addIngredient(subingredient);
                }
                else{
                    IngredientMineur subIngredient = new IngredientMineur(subIngredientElement.getAttribute("name"), subIngredientElement.getAttribute("amount"), subIngredientElement.getAttribute("unit"));
                    ingredient.addIngredient(subIngredient);
                }
            }
        }
        NodeList preparationNodes = ingredientElement.getElementsByTagName("rcp:step");
        for (int z = 0; z < preparationNodes.getLength(); z++) {
            if (preparationNodes.item(z).getParentNode().getParentNode().equals(ingredientElement)) {
                Node preparationNode = preparationNodes.item(z);
                Element preparationElement = (Element) preparationNode;
                ingredient.addPreparation(preparationElement.getTextContent());
            }
        }
        return ingredient;
    }

}
