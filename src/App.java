import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import models.*;
import Repositories.*;


public class App {
    public static void main(String[] args) throws Exception {
        File xmlFile = new File("src\\recipes.xml");
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
            
            System.out.println(id);
            System.out.println(title);
            System.out.println(date);

            
            

            NodeList ingredientNodes = recipeElement.getElementsByTagName("rcp:ingredient");
            for (int j = 0; j < ingredientNodes.getLength(); j++){
                Node ingredientNode = ingredientNodes.item(j);
                if (ingredientNode.getParentNode().equals(recipeElement)) {
                    Element ingredientElement = (Element) ingredientNode;
                    System.out.println("--" + ingredientElement.getAttribute("name") + " " + ingredientElement.getAttribute("amount") + " " + ingredientElement.getAttribute("unit"));
                    if (ingredientElement.getAttribute("amount").equals("")) {
                        NodeList subIngredientNodes = ingredientElement.getElementsByTagName("rcp:ingredient");
                        for (int y = 0; y < subIngredientNodes.getLength(); y++){
                            Node subIngredientNode = subIngredientNodes.item(y);
                            Element subIngredientElement = (Element) subIngredientNode;
                            System.out.println("----" + subIngredientElement.getAttribute("name") + " " + subIngredientElement.getAttribute("amount") + " " + subIngredientElement.getAttribute("unit"));
                        }
                        NodeList preparationNodes = ingredientElement.getElementsByTagName("rcp:step");
                        for (int z = 0; z < preparationNodes.getLength(); z++){
                            Node preparationNode = preparationNodes.item(z);
                            Element preparationElement = (Element) preparationNode;
                            System.out.println("----" + preparationElement.getTextContent());
                        }

                    }
                }
            }

            
        }
    }
}
