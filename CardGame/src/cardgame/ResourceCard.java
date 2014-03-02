/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cardgame;

/**
 *
 * @author Scalene
 */
public class ResourceCard extends Card{
    private Resource gainAmount;

    public ResourceCard(String name,Resource amount){
        super(name, new Resource(0,0,0,0));
        gainAmount = amount;
    }

    @Override
    public String toString(){
        return super.toString() + "&resource["+gainAmount.toString()+"]";
    }

    @Override
    public String cardType(){
        return "resource";
    }

    public Resource getResource(){
        return gainAmount;
    }
}
