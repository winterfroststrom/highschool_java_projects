/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alter.Model.Map;

/**
 *
 * @author Scalene
 */

public abstract class Map {
    private final String name;
    private final int x;
    private final int y;
    protected BaseTile[][] base;
    protected ObjectTile[][] objects;

    public Map(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
        base = new BaseTile[x][y];
        objects = new ObjectTile[x][y];
    }

    public String getName() {
        return name;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public MapInstance getMapInstance(){
        return null;
    }

    @Override
    public String toString() {
        String out = "\n";
        for(int i = 0;i<base.length;i++){
            for(int j =0;j<base[i].length;j++){
                out += base[i][j].toString();
            }
            out += "\n";
        }
        return out;
    }
    
    /*public static void main(String[] args){
        Map map = FileLoader.readMap("src\\alter\\Resources\\Maps\\f1.sam");
        java.lang.System.out.println(map);
    }*/
}
