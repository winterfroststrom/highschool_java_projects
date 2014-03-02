/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package neohellianalmagemachina;

/**
 *
 * @author Scalene
 */
import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.ArrayList;

public class Logic {
    public static final int hsize = 5;
    public static final int csize = 8;
    public static final int unitnum = 3;
    public static final int playernum = 2;
    public static final int[] angles = {330,300,240,210,150,120,60,30};
    public static final int[][] stats = {{4,2,4,1},{6,3,1,2},{3,4,2,0}};
    public static final String[] names = {"Wizard","Warrior","Priest"};
    public static final int HP = 0;
    public static final int ATK = 1;
    public static final int RNG= 2;
    public static final int MOV = 3;
    public static final int wizard = 0;
    public static final int warrior = 1;
    public static final int priest = 2;
    public static final int p1 = 0;
    public static final int p2 = 1;
    public static final int nothing = 0;
    public static final int attack = 1;
    public static final int move = 2;
    public static final int ongoing = 0;
    public static final int p1wins = 1;
    public static final int p2wins = 2;
    public static final int stalemate = 3;
    public static final int stalematenum = 40;
    public String[] playernames = {"Player 1","Player 2"};
    Main main;
    int player;
    int[][][] board;
    int[][] playerhp;
    int[][][] playerhc;
    private int state;
    boolean[] placed;
    private boolean hotseat;
    private boolean paused;
    private int unit;
    private int cco;
    private int hco;
    private boolean[][] res;
    private boolean updateres;
    History history;
    int turn;
    int winstate;
    int turncounter;
    AI ai;

    public Logic(Main main) {
        this.main = main;
        hotseat = false;
        paused = false;
    }

    public void startSingle(){
        player = p1;
        setUpBoardAndUnits();
        unit = wizard;
        updateres = true;
        turn = 0;
        placed = new boolean[playernum];
        placed[p1] = false;
        placed[p2] = false;
        history = new History(this);
        ai = new AI(this);
        if(main.computer == p1){
            changeHotSeat();
            decide();
        }
    }

    public void startTwo(){
        player = p1;
        setUpBoardAndUnits();
        unit = wizard;
        updateres = true;
        placed = new boolean[playernum];
        placed[p1] = false;
        placed[p2] = false;
        turn = 0;
        history = new History(this);
    }

    private void setUpBoardAndUnits(){
        board = new int[playernum][hsize][csize];
        playerhc = new int[playernum][unitnum][2];
        for(int i = 0;i<hsize;i++){
            for(int j = 0;j<csize;j++){
                board[p1][i][j] = -1;
                board[p2][i][j] = -1;
            }
        }
        playerhp = new int[playernum][unitnum];
        playerhp[p1][wizard] = stats[wizard][HP];
        playerhp[p2][wizard] = stats[wizard][HP];
        playerhp[p1][priest] = stats[priest][HP];
        playerhp[p2][priest] = stats[priest][HP];
        playerhp[p1][warrior] = stats[warrior][HP];
        playerhp[p2][warrior] = stats[warrior][HP];
    }

    public void moveCursor(int h, int c){
        if(cco>3)
            h *= -1;
        EPoint destination = getHCEPoint(hco,cco,h,c);
        hco = destination.x;
        cco = destination.y;
        //System.out.println("h " + hco + " c " + cco);
    }

    public void setUnit(int unit){
        if(placed[p2]){
            if(isDead(player,unit))
                return;
            this.unit = unit;
            res = new boolean[hsize][csize];
            updateres = true;
        }
    }

    public void setState(int state){
        this.state = state;
        updateres = true;
    }

    public int getState() {
        return state;
    }

    public int getUnit() {
        return unit;
    }

    public void decide(){
        switch(main.gametype){
            case Main.single: decideForSingle();
                break;
            case Main.two: decideForTwo();
                break;
        }
    }
    private void decideForSingle(){
        if(placed[p2]){
            if(player != main.computer){
                if(playForHuman())
                    playForComputer();
            }
        } else {
            if(placed[p1]){
                if(main.computer == p1){
                    placeForHuman();
                    if(placed[p2])
                        playForComputer();
                } else {
                    placeForComputer();
                }
            } else {
                if(main.computer == p2){
                    placeForHuman();
                    if(placed[p1])
                        placeForComputer();
                } else {
                    placeForComputer();
                }
            }
        }
    }

    private void placeForComputer(){
        ai.placePieces();
        switchTurn();
    }
    
    private void playForComputer(){
        ai.playTurn();
        switchTurn();
    }

    private void decideForTwo(){
        if(placed[p2]){
            playForHuman();
        } else {
            if(placed[p1]){
                placeForHuman();
            } else {
                placeForHuman();
            }
        }
    }

    private boolean playForHuman(){
        if(isDead(player,unit))
            return false;
        switch(state){
            case attack:
                if(!attack()) return false;
                break;
            case move:
                if(isValidMove()){
                    moveUnit();
                } else return false;
                break;
            default: return false;
        }
        changeHotSeat();
        switchTurn();
        return  true;
    }

    private void placeForHuman(){
        if(isValidPlace())
            placeUnit();
        else return;
        unit++;
        if(unit >= unitnum){
            unit = 0;
            placed[player] = true;
            changeHotSeat();
            switchTurn();
        }
    }

    private boolean isValidPlace(){
        return board[player][hco][cco] == -1;
    }

    private boolean isValidAttack(){
        if(state != attack)
            setState(attack);
        if(updateres)
            updateRes();
        if(board[otherPlayer(player)][hco][cco]!= -1)
                if(isDead(otherPlayer(player),board[otherPlayer(player)][hco][cco]))
                    return false;
        return res[hco][cco];
    }

    private boolean attack(){
        if(isValidAttack()){
            if(board[otherPlayer(player)][hco][cco]!= -1){
                playerhp[otherPlayer(player)][board[otherPlayer(player)][hco][cco]] -= stats[unit][ATK];
                if(isDead(otherPlayer(player),board[otherPlayer(player)][hco][cco])){
                    history.add(player,new Turn(state, unit, hco, cco, Turn.kill,turn));
                } else {
                    history.add(player,new Turn(state, unit, hco, cco, Turn.damage,turn));
                    turncounter = 0;
                }
            } else {
                history.add(player,new Turn(state, unit, hco, cco, Turn.miss,turn));
            }
        } else return false;
        return true;
    }

    private boolean isValidMove(){
        if(state != move)
            setState(move);
        if(updateres)
            updateRes();
        return res[hco][cco];
    }

    private void moveUnit(){
        if(isDead(player,unit))
            return;
        board[player][playerhc[player][unit][0]][playerhc[player][unit][1]] = -1;
        board[player][hco][cco] = unit;
        playerhc[player][unit][0] = hco;
        playerhc[player][unit][1] = cco;
        history.add(player,new Turn(state, unit, hco, cco, Turn.miss,turn));
    }

    private void placeUnit(){
        if(isDead(player,unit))
            return;
        board[player][hco][cco] = unit;
        playerhc[player][unit][0] = hco;
        playerhc[player][unit][1] = cco;
        history.add(player,new Turn(nothing, unit, hco, cco, Turn.miss,turn));
    }

    public int getCco() {
        return cco;
    }

    public int getHco() {
        return hco;
    }

    public boolean getHotSeat(){
        return hotseat;
    }

    public boolean getPaused(){
        return paused;
    }

    public void changeHotSeat(){
        hotseat = !hotseat;
    }

    public void changePaused(){
        paused = !paused;
    }

    public void updateRes(){
        getPlayerValidLocations(playerhc[player][getUnit()][0],playerhc[player][getUnit()][1]);
    }

    public boolean[][] getPlayerValidLocations(int h, int c){
        res = new boolean[hsize][csize];
        if(isDead(player,unit))
            return res;
        int range;
        if(state == attack){
            range = stats[unit][RNG];
        } else if(state == move){
            range = stats[unit][MOV];
        } else range = 0;
        res = getValidRangeLocations(range, h, c);
        if(state == move){
            for(int i = 0;i<unitnum;i++){
                if(board[player][playerhc[player][i][0]][playerhc[player][i][1]] == i)
                    res[playerhc[player][i][0]][playerhc[player][i][1]] = false;
            }
        }
        updateres = false;
        return res;
    }

    private boolean[][] getValidRangeLocations(int range, int h, int c){
        boolean[][] locations = new boolean[hsize][csize];
        HashSet<EPoint> set = new HashSet<EPoint>();
        set.add(new EPoint(h,c));
        //boolean center = false;
        while(range > 0){
            HashSet<EPoint> temp = new HashSet<EPoint>();
            for(EPoint point: set){
                temp.add(getHCEPoint(point.x,point.y,1,0));
                temp.add(getHCEPoint(point.x,point.y,0,1));
                temp.add(getHCEPoint(point.x,point.y,-1,0));
                temp.add(getHCEPoint(point.x,point.y,0,-1));
                /*if(!center){if(p.x == 0){for(int i = 0;i<csize;i++)temp.add(new EPoint(0,i));
                    }center = true;}for(int i = (p.x == 0?0:-1);i<2;i++){for(int j = -1;j<2;j++){
                        temp.add(getHCEPoint(p.x,p.y,i,j));}}*/
            }
            for(EPoint point: temp){
                set.add(point);
            }
            range--;
        }
        for(EPoint point: set){
            locations[point.x][point.y] = true;
        }
        return locations;
    }

    private int getRangeSum(int range, int h, int c){
        int sum = 0;
        HashSet<EPoint> set = new HashSet<EPoint>();
        set.add(new EPoint(h,c));
        while(range > 0){
            HashSet<EPoint> temp = new HashSet<EPoint>();
            for(EPoint point: set){
                temp.add(getHCEPoint(point.x,point.y,1,0));
                temp.add(getHCEPoint(point.x,point.y,0,1));
                temp.add(getHCEPoint(point.x,point.y,-1,0));
                temp.add(getHCEPoint(point.x,point.y,0,-1));
            }
            for(EPoint point: temp){
                set.add(point);
                sum += 1;
            }
            range--;
        }
        return sum;
    }

    private static EPoint getHCEPoint(int h, int c, int dh, int dc){
        h += dh;
        if(h < 0){
            c += csize/2;
            h = 0;
        }
        if(h >= hsize)
            h = hsize - 1;

        c += dc;
        while(c < 0)
            c += csize;
        c %= csize;
        EPoint hc = new EPoint(h,c);
        return hc;
    }

    private static class EPoint extends Point{
        public EPoint(int h, int c) {
            super(h,c);
        }

        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof Point))
                return false;
            Point p = (Point) obj;
            if(x != p.x)
                return false;
            else if(y != p.y)
                return false;
            return true;
        }

        @Override
        public int hashCode() {
            return x*100000+y;
        }
    }

    private boolean isDead(int p, int unit){
        return playerhp[p][unit] < 1;
    }

    static class Turn{
        int unit;
        int state;
        int h;
        int c;
        int turn;
        int hit;
        static final int miss = 0;
        static final int damage = 1;
        static final int kill = 2;


        public Turn(int state, int unit, int h, int c, int hit, int turn) {
            this.unit = unit;
            this.state = state;
            this.h = h;
            this.c = c;
            this.hit = hit;
            this.turn = turn;
        }

        @Override
        public String toString() {
            String string = "Turn " + turn + ": ";
            switch(state){
                case attack: string += (names[unit] + " attacked <" + h + "," + c + "> and "
                        + (hit==miss?"missed!":(hit==kill?"killed!":"hit!")));
                    break;
                case move: string += (names[unit] + " moved!");
                    break;
                case nothing: string += (names[unit] + " placed!");
                    break;
                default: string += ("State error!");
            }
            return string;
        }
    }

    private int otherPlayer(int p){
        return (p + 1) % 2;
    }

    static class History{
        ArrayList<LinkedList<Turn>> history;
        Logic logic;
        int[] indexes;
        int[] sizes;
        Turn[][][] memory;
        int[] memoryindex;
        static final int memorysize = 25;

        public History(Logic logic) {
            this.logic = logic;
            history = new ArrayList<LinkedList<Turn>>(playernum);
            history.add(p1,new LinkedList<Turn>());
            history.add(p2,new LinkedList<Turn>());
            indexes = new int[playernum];
            sizes = new int[playernum];
            memory = new Turn[playernum][playernum][memorysize];
            memoryindex = new int[playernum];
        }

        public void add(int player, Turn t){
            history.get(player).add(t);
            indexes[player]++;
            sizes[player]++;
        }

        public void change(int player, int change){
            if(indexes[player] + change >sizes[player])
                indexes[player] = sizes[player];
            else if(indexes[player] + change < 1)
                indexes[player] = 1;
            else
                indexes[player] += change;
        }

        public Turn getTurn(int player){
            //System.out.println(sizes[player]);
            if(sizes[player] < 1)
                return null;
            return history.get(player).get(indexes[player]-1);
        }

        public void setMaxIndex(){
            indexes[0] = sizes[0];
            indexes[1] = sizes[1];
        }

        public void addToMemory(int player){
            memory[logic.player][player][memoryindex[logic.player]] = getTurn(player);
            memoryindex[logic.player]++;
            memoryindex[logic.player] %= memorysize;
        }

        public void clearMemory(){
            for(int i = 0;i < memorysize;i++){
                memory[logic.player][0][i] = null;
                memory[logic.player][1][i] = null;
            }
        }
    }

    private void switchTurn(){
        player = otherPlayer(player);
        history.setMaxIndex();
        if(placed[p2] && player == p1){
            turn++;
            turncounter++;
        }
        unit = 0;
        hco = 0;
        cco = 0;
        setState(nothing);
        if(turncounter >= stalematenum){
            winstate = stalemate;
        } else if(isDead(player,wizard) && isDead(player,warrior) && isDead(player,priest)){
            winstate = (player == p1?p2wins:p1wins);
        }
    }

    public void clear(){
        player = 0;
        board = null;
        playerhp = null;
        playerhc = null;
        state = nothing;
        placed[p1] = false;
        placed[p2] = false;
        hotseat = false;
        paused = false;
        unit = wizard;
        cco = 0;
        hco = 0;
        res = null;
        updateres = true;
        history = null;
        turn = 0;
        winstate = ongoing;
        turncounter = 0;
        ai = null;
    }

    private class AI{
        Logic logic;
        Random picker;
        boolean thinking;
        ArrayList<LinkedList<boolean[][]>> aelim;//unit-move-h-c
        ArrayList<LinkedList<boolean[][]>> melim;

        public AI(Logic logic) {
            this.logic = logic;
            picker = new Random();
            thinking = false;
            aelim = new ArrayList<LinkedList<boolean[][]>>(unitnum);//false == possible
            melim = new ArrayList<LinkedList<boolean[][]>>(unitnum);
            for(int i = 0;i < unitnum;i++){
                aelim.add(i, new LinkedList<boolean[][]>());
                melim.add(i, new LinkedList<boolean[][]>());
                boolean[][] aelimtemp = new boolean[hsize][csize];
                boolean[][] melimtemp = new boolean[hsize][csize];
                aelim.get(i).addLast(aelimtemp);
                melim.get(i).addLast(melimtemp);
            }
        }

        public void placePieces(){
            thinking = true;
            unit = 0;
            while(unit < unitnum){
                int bestvalue = 0;
                int besth = 0;
                int bestc = 0;
                for(int i = 0;i < hsize;i++){
                    a:
                    for(int j = 0;j < csize;j++){
                        int testvalue = 0;
                        for(int k = 0;k < unitnum;k++){
                            if(board[player][i][j] == k){
                                continue a;
                            }
                        }
                        testvalue += getRangeSum(stats[unit][RNG], i, j);
                        testvalue += getRangeSum(stats[unit][MOV], i, j);
                        testvalue += (picker.nextInt(4) - 2);
                        if(testvalue > bestvalue){
                            bestvalue = testvalue;
                            besth = i;
                            bestc = j;
                        }
                    }
                }
                hco = besth;
                cco = bestc;
                if(isValidPlace()){
                    placeUnit();
                } else {
                    error("Placement invalid!");
                    return;
                }
                unit++;
            }
            unit = 0;
            placed[player] = true;
            thinking = false;
        }

        public void playTurn(){
            if(winstate != ongoing)
                return;
            thinking = true;
            Turn last = history.getTurn(otherPlayer(player));
            if(last == null){
                error("Human's last turn not found!");
                return;
            }
            Turn clast = history.getTurn(player);
            if(clast == null){
                error("Computer's last turn not found!");
                return;
            }
            processLastComputerTurn(last,clast);
            processLastPlayerTurn(last);
            LinkedList<int[]> list = new LinkedList<int[]>();
            LinkedList<int[]> hlist = new LinkedList<int[]>();
            int[] bestvalue2 = new int[unitnum];
            setUnitHits(bestvalue2,list,hlist);

            if(hlist.size() > 0){
                setState(attack);
                setBestAttack(getBestAttackUnit(bestvalue2),hlist);
                if(isValidAttack()){
                    attack();
                } else {
                    error("Invalid attack!");
                    return;
                }
            } else {
                setState(move);
                if(hlist.size() > 0){
                    setBestMove(getBestMoveUnit(list,hlist),hlist);
                } else {
                    setRandomMove();
                }
                if(isValidMove()){
                    moveUnit();
                } else {
                    error("Invalid move!");
                    return;
                }
            }
            thinking = false;
        }

        private void processLastComputerTurn(Turn last, Turn clast){
            if(clast.state == attack){
                for(int i = 0;i < unitnum;i++){
                    if(isDead(otherPlayer(player),i))
                        continue;
                    boolean[][] mposs = melim.get(i).removeLast();
                    if(clast.hit == Turn.damage){
                        for(int j = 0;j < hsize;j++){
                            for(int k = 0;k < csize;k++){
                                if(j != clast.h && k != clast.c)
                                    mposs[j][k] = true;
                            }
                        }
                    } else {
                        boolean[][] lposs = getValidRangeLocations(stats[last.unit][RNG],last.h,last.c);
                        for(int j = 0;j < hsize;j++){
                            for(int k = 0;k < csize;k++){
                                mposs[j][k] = mposs[j][k] && !lposs[j][k];
                            }
                        }
                    }
                    melim.get(i).addLast(mposs);
                }
            }
        }

        private void processLastPlayerTurn(Turn last){
            switch(last.state){
                case attack:
                    boolean[][] lposs = getValidRangeLocations(stats[last.unit][RNG],last.h,last.c);
                    boolean[][] aposs = aelim.get(last.unit).removeLast();
                    for(int i = 0;i < hsize;i++){
                        for(int j = 0;j < csize;j++){
                            aposs[i][j] = aposs[i][j] || !lposs[i][j];
                        }
                    }
                    aelim.get(last.unit).addLast(aposs);
                    break;
                case move:
                    boolean[][] aelimtemp = new boolean[hsize][csize];
                    boolean[][] melimtemp = new boolean[hsize][csize];
                    aelim.get(last.unit).addLast(aelimtemp);
                    melim.get(last.unit).addLast(melimtemp);
                    break;
            }
        }

        private void setUnitHits(int[] unitNumHits,LinkedList<int[]> list, LinkedList<int[]> hlist){
            int bestvalue = 0;
            int bestunit = 0;
            boolean[][][] current = new boolean[unitnum][hsize][csize];
            boolean[][][] hitable = new boolean[unitnum][hsize][csize];
            for(int i = 0;i<unitnum;i++){
                boolean[][] hunit = getValidRangeLocations(stats[i][RNG],
                        playerhc[player][i][0],playerhc[player][i][1]);
                boolean[][] aposs = melim.get(i).getLast();
                boolean[][] mposs = melim.get(i).getLast();
                int testvalue = 0;
                for(int j = 0;j < hsize;j++){
                    for(int k = 0;k < csize;k++){
                        if(!isDead(player,i))
                            hitable[i][j][k] = hitable[i][j][k] || hunit[j][k];
                        if(isDead(otherPlayer(player),i))
                            continue;
                        current[i][j][k] = !(mposs[j][k] || aposs[j][k]);
                        if(current[i][j][k])
                            testvalue++;
                    }
                }
                if(testvalue > bestvalue){
                    bestvalue = testvalue;
                    bestunit = i;
                }
            }
            if(bestvalue < 1){
                error("Opponent has no units!");
                return;
            }
            for(int i = 0;i < hsize;i++){
                for(int j = 0;j < csize;j++){
                    if(current[bestunit][i][j]){
                        for(int k = 0;k < unitnum;k++){
                            int[] temp = new int[3];// unit - h - c
                            temp[0] = k;
                            temp[1] = i;
                            temp[2] = j;
                            if(hitable[k][i][j]){
                                hlist.add(temp);
                                unitNumHits[k]++;
                            } else {
                                list.add(temp);
                            }
                        }
                    }
                }
            }
        }

        private int getBestAttackUnit(int[] unitAttackValues){
            int bestunit = 0;
            int bestvalue = 0;
            for(int i = 0;i < unitnum;i++){
                if(unitAttackValues[i] > bestvalue){
                    bestvalue = unitAttackValues[i];
                    bestunit = i;
                }
            }
            return bestunit;
        }

        private void setBestAttack(int bestunit, LinkedList<int[]> hlist){
            LinkedList<int[]> list = new LinkedList<int[]>();
            for(int[] temp: hlist){
                if(temp[0] == bestunit)
                    list.add(temp);
            }
            int pick = picker.nextInt(list.size());
            int[] choice = list.get(pick);
            unit = choice[0];
            hco = choice[1];
            cco = choice[2];
        }

        private int getBestMoveUnit(LinkedList<int[]> list,LinkedList<int[]> hlist){
            int bestunit = 0;
            int bestvalue = 0;
            hlist = new LinkedList<int[]>();
            for(int i = 0;i < unitnum;i++){
                if(!isDead(player,i)){
                    int testvalue = 0;
                    boolean[][] movable = getValidRangeLocations(stats[i][MOV + RNG],
                            playerhc[player][i][0],playerhc[player][i][1]);
                    for(int w = 0;w < unitnum;w++){
                        if(board[player][playerhc[player][w][0]][playerhc[player][w][1]] == w){
                            movable[playerhc[player][w][0]][playerhc[player][w][1]] = false;
                        }
                    }
                    for(int j = 0;j < hsize;j++){
                        for(int k = 0;k < csize;k++){
                            if(movable[j][k]){
                                for(int[] target:list){
                                    if(target[1] == j && target[2] ==k){
                                        int[] temp = new int[3];
                                        temp[0] = i;
                                        temp[1] = j;
                                        temp[2] = k;
                                        hlist.add(temp);
                                        testvalue++;
                                    }
                                }
                            }
                        }
                    }
                    if(testvalue > bestvalue){
                        bestvalue = testvalue;
                        bestunit = i;
                    }
                }
            }
            return bestunit;
        }

        private void setBestMove(int bestunit,LinkedList<int[]> moves){
            LinkedList<int[]> list = new LinkedList<int[]>();
            for(int[] moving:moves){
                if(moving[0] == bestunit){
                    list.add(moving);
                }
            }
            int pick = picker.nextInt(list.size());
            int[] choice = list.get(pick);
            unit = choice[0];
            hco = choice[1];
            cco = choice[2];
        }

        private void setRandomMove(){
            for(int i = 0;i < unitnum;i++){
                if(!isDead(player,i)){
                    unit = i;
                    EPoint poss = getHCEPoint(playerhc[player][i][0],playerhc[player][i][1],1,0);
                    hco = poss.x; cco = poss.y; if(isValidMove()) break;
                    poss = getHCEPoint(playerhc[player][i][0],playerhc[player][i][1],-1,0);
                    hco = poss.x; cco = poss.y; if(isValidMove()) break;
                    poss = getHCEPoint(playerhc[player][i][0],playerhc[player][i][1],0,-1);
                    hco = poss.x; cco = poss.y; if(isValidMove()) break;
                    poss = getHCEPoint(playerhc[player][i][0],playerhc[player][i][1],0,-1);
                    hco = poss.x; cco = poss.y; if(isValidMove()) break;
                }
            }
        }

        public String getStatus(){
            String string = "";
            if(thinking){
                string += (playernames[main.computer] + " <Computer> is thinking.");
                return string;
            }
            Turn lastturn = history.getTurn(main.computer);
            if(lastturn != null)
                string += (playernames[main.computer] + " <Computer>: " + lastturn.toString());
            return string;
        }
    }

    public String getAIStatus(){
        return ai.getStatus();
    }

    private void error(String s){
        System.out.println("AI Error: " + s);
        switch(main.computer){
            case p1: winstate = p2wins;
                return;
            case p2: winstate = p1wins;
                return;
        }
    }

    public boolean[][] getRes(){
        if(updateres){
            updateRes();
            updateres = false;
        }
        return res;
    }

    public boolean aithinking(){
        return ai.thinking;
    }
}
