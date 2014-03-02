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

public class CardParser {
   /*public static void main(String[]args){
        ArrayList<Integer> a = new ArrayList<Integer>();
        ArrayList<Integer> b = new ArrayList<Integer>();
        a.add(1);b.add(1);
        a.add(6);b.add(1);
        a.add(8);b.add(1);
        createDeck(a,b);
    }*/
    /**
     *@param cardIndexes all int in val > 0
     */
    
    public static ArrayList<Card> createDeck(ArrayList<Integer> cardIndexes,ArrayList<Integer> cardNumbers){
        if(cardIndexes.size() != cardNumbers.size())
            return null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        ArrayList<Card> deck = new ArrayList<Card>();
        try{
            DocumentBuilder parser = factory.newDocumentBuilder();
            Document doc = parser.parse(new File("src\\CardGame\\Card.xml"));
            NodeList documentParts = doc.getChildNodes();
            NodeList cards = documentParts.item(2).getChildNodes();
            for(int i =0;i<cardIndexes.size();i++){
                Node card = cards.item(adjust(cardIndexes.get(i)));
                if(card.getNodeName().equalsIgnoreCase("unit")){
                    Card toAdd = parseUnit(card);
                    for(int j =0;j<cardNumbers.get(i);j++)
                        deck.add(toAdd);
                } else if(card.getNodeName().equalsIgnoreCase("action")){
                    Card toAdd = parseAction(card);
                    for(int j =0;j<cardNumbers.get(i);j++)
                        deck.add(toAdd);
                } else if(card.getNodeName().equalsIgnoreCase("resource")){
                    Card toAdd = parseResource(card);
                    for(int j =0;j<cardNumbers.get(i);j++)
                        deck.add(toAdd);
                }
            }
        } catch(SAXException e) {
            e.printStackTrace();
        } catch(ParserConfigurationException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return deck;
    }

    private static Card parseAction(Node action){
        String name,theme,target1,target2,meta = "";
        NodeList attributes = action.getChildNodes();
        name = attributes.item(1).getFirstChild().getNodeValue();//name
        String[] costs = attributes.item(2).getFirstChild().getNodeValue().split("[-]");//cost
        Resource cost = new Resource(Integer.parseInt(costs[0]),Integer.parseInt(costs[1]),Integer.parseInt(costs[2]),Integer.parseInt(costs[3]));
        theme = attributes.item(3).getFirstChild().getNodeValue();//theme
        NodeList effect = attributes.item(5).getChildNodes();
        //singleLayer(effect,3);
        target1 = effect.item(0).getFirstChild().getNodeValue();//tar1
        target2 = effect.item(1).getFirstChild().getNodeValue();//tar2
        NodeList change = effect.item(3).getChildNodes();
        meta = change.item(0).getFirstChild().getNodeValue();//meta
        return new Card(name,cost);
        ////////////////////////////////add meta interpreter///////////////////////////////////////////////////
    }

    private static Card parseUnit(Node unit){
        String name,theme = "";
        int health,attack = 0;
        NodeList attributes = unit.getChildNodes();
        Node unitNameNode = attributes.item(adjust(1)).getFirstChild();
        name = unitNameNode.getNodeValue();//name
        Node stats = attributes.item(adjust(2));//stats
        NodeList stat = stats.getChildNodes();//stats
        health = Integer.parseInt(stat.item(0).getFirstChild().getNodeValue());//health
        attack = Integer.parseInt(stat.item(1).getFirstChild().getNodeValue());//attack
        String[] costs = stat.item(2).getFirstChild().getNodeValue().split("[-]");//cost
        Resource cost = new Resource(Integer.parseInt(costs[0]),Integer.parseInt(costs[1]),Integer.parseInt(costs[2]),Integer.parseInt(costs[3]));
        theme = stat.item(3).getFirstChild().getNodeValue();//theme
        return new UnitCard(name,health,attack,cost,theme);
    }
    
    private static ResourceCard parseResource(Node resource){
        NodeList attributes = resource.getChildNodes();
        //singleLayer(attributes,5);
        String name = attributes.item(1).getFirstChild().getNodeValue();//name
        String[] amounts = attributes.item(2).getFirstChild().getNodeValue().split("[-]");//amount
        Resource amount = new Resource(Integer.parseInt(amounts[0]),Integer.parseInt(amounts[1]),Integer.parseInt(amounts[2]),Integer.parseInt(amounts[3]));
        return new ResourceCard(name,amount);
    }

    private static int adjust(int n){
        return n*2-1;
    }

    public static void singleLayer(NodeList n, int en){
        for(int i =0;i < en;i++){
            System.out.println(n.item(i).getNodeName());
        }
        System.out.println("##################");
        for(int i =0;i < en;i++){
            System.out.println(n.item(i).getNodeName());
            System.out.println(n.item(i).getNodeValue());
        }
    }

    private static void traverse (Node n){
        // Extract node info:
        String nodename = n.getNodeName();
        String test = n.getNodeValue();
        if(test != null)test = test.trim();
        // Print and continue traversing.
        System.out.println ("Node: " + n.getNodeName() + " value=[" + test + "]");

        // Now traverse the rest of the tree in depth-first order.
        if (n.hasChildNodes()) {
            // Get the children in a list.
            NodeList nl = n.getChildNodes();
            // How many of them?
            int size = nl.getLength();
            System.out.println();
            for (int i=0; i<size; i++)
                // Recursively traverse each of the children.
                traverse (nl.item(i));
       }
    }
}
