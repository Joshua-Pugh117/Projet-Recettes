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

        RecepieRepo recepieRepo = new RecepieRepo("src\\recipes.xml");
        IngredientMajeur x = (IngredientMajeur) recepieRepo.getRecepies().get(4).getIngredients().get(1);
        System.out.println(x.getIngredients().get(0));
        
    }
}
