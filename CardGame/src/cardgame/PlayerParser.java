/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cardgame;

/**
 *
 * @author Scalene
 */
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

public class PlayerParser {

    /*public static void main(String[]args){
        Player as =  new Player("Player");
    }*/

    public static ArrayList<Integer>[] parsePlayer(String playerFile){
       DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
       ArrayList<Integer>[] cards = new ArrayList[3];
       cards[0] = new ArrayList<Integer>();
       cards[1] = new ArrayList<Integer>();
       cards[2] = new ArrayList<Integer>();
       try{
            DocumentBuilder parser = factory.newDocumentBuilder();
            Document doc = parser.parse(new File("src\\CardGame\\"+playerFile+".xml"));
            NodeList attributes = doc.getChildNodes().item(2).getChildNodes();
            NodeList cities = attributes.item(1).getChildNodes();
            cards[0].add(Integer.parseInt(cities.item(1).getFirstChild().getNodeValue()));//city
            cards[0].add(Integer.parseInt(cities.item(3).getFirstChild().getNodeValue()));//city
            cards[0].add(Integer.parseInt(cities.item(5).getFirstChild().getNodeValue()));//city
            int cardTotal = Integer.parseInt(attributes.item(3).getFirstChild().getNodeValue())*2-1;//meta num card
            NodeList cardList = attributes.item(5).getChildNodes();
            for(int i = 1;i <= cardTotal;i += 2){
                cards[1].add(Integer.parseInt(cardList.item(i).getFirstChild().getFirstChild().getNodeValue()));//cardid
                cards[2].add(Integer.parseInt(cardList.item(i).getFirstChild().getFirstChild().getNodeValue()));//cardnum
            }
       } catch(SAXException e) {
            e.printStackTrace();
       } catch(ParserConfigurationException e){
            e.printStackTrace();
       } catch (IOException e) {
            e.printStackTrace();
       }

       return cards;
    }
}
