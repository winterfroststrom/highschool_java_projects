/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package monopoly;

/**
 *
 * @author Scalene
 */
public enum Properties {
    P1("Mediterranean Avenue",60,2,50,30,10,30,90,160,250,1),
    P2("Baltic Avenue",60,4,50,30,20,60,180,320,450,1),
    P3("Oriental Avenue",100,6,50,50,30,90,270,400,550,2),
    P4("Vermont Avenue",100,6,50,50,30,90,270,400,550,2),
    P5("Connecticut Avenue",120,8,50,60,40,100,300,450,600,2),
    P6("St. Charles Place",140,10,100,70,50,150,450,625,750,3),
    P7("States Avenue",140,10,100,70,50,150,450,625,750,3),
    P8("Virginia Avenue",160,12,100,80,60,180,500,700,900,3),
    P9("St. James Place",180,14,100,90,70,200,550,700,900,4),
    P10("Tennessee Avenue",180,14,100,90,70,200,550,700,950,4),
    P11("New York Avenue",200,16,100,100,80,220,600,800,1000,4),
    P12("Kentucky Avenue",220,18,150,110,90,250,700,875,1050,5),
    P13("Indiana Avenue",220,18,150,110,90,250,700,875,1050,5),
    P14("Illinois Avenue",240,20,150,120,100,300,750,925,1100,5),
    P15("Atlantic Avenue",260,22,150,130,110,330,800,975,1150,6),
    P16("Ventnor Avenue",260,22,150,130,110,330,800,975,1150,6),
    P17("Marvin Gardens",280,24,150,140,120,360,850,1025,1200,6),
    P18("Pacific Avenue",300,26,200,150,130,390,900,1100,1275,7),
    P19("North Carolina Avenue",300,26,200,150,130,390,900,1100,1275,7),
    P20("Pennsylvania Avenue",320,28,200,160,150,450,1000,1200,1400,7),
    P21("Park Place",350,35,200,175,175,500,1100,1300,1500,8),
    P22("Boardwalk",400,50,200,200,200,600,1400,1700,2000,8),
    R1("Reading Railroad",200,25,0,100,50,100,200,0,0,9),
    R2("Pennsylvania Railroad",200,25,0,100,50,100,200,0,0,9),
    R3("B&B Railroad",200,25,0,100,50,100,200,0,0,9),
    R4("Short Line",200,25,0,100,50,100,200,0,0,9),
    U1("Electric Company",150,4,0,75,10,0,0,0,0,10),
    U2("Water Works",150,4,0,75,10,0,0,0,0,10);

    final String name;
    final int cost;
    final int rent;
    final int house;
    final int mortgage;
    final int h1;
    final int h2;
    final int h3;
    final int h4;
    final int h5;
    final int set;

    private Properties(String name, int cost, int rent, int house, int mortgage, int h1, int h2, int h3, int h4, int h5, int set) {
        this.name = name;
        this.cost = cost;
        this.rent = rent;
        this.house = house;
        this.mortgage = mortgage;
        this.h1 = h1;
        this.h2 = h2;
        this.h3 = h3;
        this.h4 = h4;
        this.h5 = h5;
        this.set = set;
    }

    public int getFirst(){
        if(set == 1) return 0;
        else if(set<8) return set*3 -1;
        else if(set == 8) return 20;
        else if(set == 9) return 22;
        else return 26;
    }
    public int getSecond(){
        if(set == 1) return 1;
        else if(set<8) return set*3;
        else if(set == 8) return 21;
        else if(set == 9) return 23;
        else return 27;
    }
    public int getThird(){
        if(set == 1) return 1;
        else if(set<8) return set*3 +1;
        else if(set == 8) return 21;
        else if(set == 9) return 24;
        else return 27;
    }
    public int getFourth(){
        if(set == 1) return 1;
        else if(set<8) return set*3 +1;
        else if(set == 8) return 21;
        else if(set == 9) return 25;
        else return 27;
    }
}
