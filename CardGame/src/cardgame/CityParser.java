/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cardgame;

/**
 *
 * @author Scalene
 */
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.io.IOException;
import org.w3c.dom.*;

public class CityParser {
    /*public static void main(String[]args){
        ArrayList<Integer> test = new ArrayList<Integer>();
        test.add(1);
        test.add(1);
        test.add(1);
        System.out.println(parseCity(test));
    }*/

    public static CitySet parseCity(ArrayList<Integer> citiesIndexs){
        if(citiesIndexs.size() != 3)
            return null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try{
            City[] cityset = new City[3];
            DocumentBuilder parser = factory.newDocumentBuilder();
            Document doc = parser.parse("\\src\\CardGame\\City.xml");
            NodeList cities = doc.getChildNodes().item(2).getChildNodes();
            for(int i = 0;i<3;i++){
                NodeList city = cities.item(citiesIndexs.get(i)*2-1).getChildNodes();
                NodeList basic = city.item(1).getChildNodes();
                String name = basic.item(1).getFirstChild().getNodeValue();//name
                int health = Integer.parseInt(basic.item(3).getFirstChild().getNodeValue());//health
                String theme = basic.item(5).getFirstChild().getNodeValue();//theme
                int field= Integer.parseInt(city.item(3).getFirstChild().getNodeValue());//field
                cityset[i] = new City(name,health,theme,field);
            }
            return new CitySet(cityset[0],cityset[1],cityset[2]);
        } catch(ParserConfigurationException e){
            e.printStackTrace();
        } catch(SAXException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
