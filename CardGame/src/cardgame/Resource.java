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
public class Resource {
    private int[] resource;
    public Resource(int pop, int mana, int parts, int order){
        resource = new int[4];
        resource[0] = pop;
        resource[1] = mana;
        resource[2] = parts;
        resource[3] = order;
    }

    public boolean hasResources(int[] toCheck){
        boolean check = true;
        for(int i=0;i<4;i++)
            check = check && toCheck[i] >= resource[i];
        return check;
    }

    public int[] addResources(int[] addTo){
        for(int i =0;i<3;i++)
            addTo[i] += resource[i];
        return addTo;
    }

    @Override
    public String toString(){
        return "Resource["+resource[0]+","+resource[1]+","+resource[2]+","+resource[3]+"]";
    }

    public static Resource sum(ArrayList<Resource> resources){
        int[] sums = new int[4];
        for(Resource res:resources){
            sums[0]+=res.resource[0];
            sums[1]+=res.resource[1];
            sums[2]+=res.resource[2];
            sums[3]+=res.resource[3];
        }
        return new Resource(sums[0],sums[1],sums[2],sums[3]);
    }

    public static Resource sumCards(ArrayList<ResourceCard> resources){
        int[] sums = new int[4];
        for(ResourceCard card:resources){
            sums[0]+=card.getResource().resource[0];
            sums[1]+=card.getResource().resource[1];
            sums[2]+=card.getResource().resource[2];
            sums[3]+=card.getResource().resource[3];
        }
        return new Resource(sums[0],sums[1],sums[2],sums[3]);
    }
}
