/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cardgame;

/**
 *
 * @author Scalene
 */
public class CitySet {
    private City[] cities;
    private int cityNum;

    public CitySet(City inner, City outer, City frontier){
        cities = new City[3];
        cities[0] = inner;
        cities[1] = outer;
        cities[2] = frontier;
        cityNum = 7;
    }

    @Override
    public String toString(){
        return "CitySet[inner[" + cities[0].toString() + "]&" +
                "outer[" + cities[1].toString() + "]&" +
                "frontier[" + cities[2].toString() + "]]";
    }

    public int getTopCity(){
        if(cityNum>3)
            return 2;
        else if(cityNum>1)
            return 1;
        return 0;
    }

    public int getTotalHealth(){
        int sum = 0;
        switch(cityNum){
            case 7: return getHealth(0)+getHealth(1)+getHealth(2);
            case 3: return getHealth(0)+getHealth(1);
            case 1: return getHealth(0);
            case 5: return getHealth(0)+getHealth(2);
            case 6: return getHealth(1)+getHealth(2);
            case 2: return getHealth(1);
            case 4: return getHealth(2);
            default: return 0;
        }
    }

    public boolean isValidCity(int city){
        return city < 3 && city > -1;
    }

    public int getHealth(int city){
        return cities[city].getHealth();
    }

    public void changeHealth(int change, int city){
        cities[city].changeHealth(change);
        if(cities[city].getHealth()<1)
            switch(city){
                case 0: cityNum ^= 1;
                    break;
                case 1: cityNum ^= 2;
                    break;
                case 2: cityNum ^= 4;
                    break;
            }
    }


    public int fieldSize(int city){
        return cities[city].FIELD;
    }
}
