/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alter.Model;

/**
 *
 * @author Scalene
 */

//import alter.Model.Map.Tile;
//import alter.Model.Map.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class FileLoader {
    

    /*public static Map readMap(String fileName){
        ArrayList<String> data = new ArrayList<String>();
    	try{
            FileReader reader = new FileReader(fileName);
	    BufferedReader file = new BufferedReader(reader);
            while(file.ready()){
                data.add(file.readLine());
            }
            file.close();
            reader.close();
            return process(data);
	} catch(java.io.FileNotFoundException e) {
            e.printStackTrace();
        } catch(java.io.IOException e) {
            e.printStackTrace();
        } catch(MapError e){
            e.printStackTrace();
            java.lang.System.out.println(e);
        }
        return null;
    }

    private static Map process(ArrayList<String> data) throws MapError{
        if(!data.get(0).equals("Name:")) throw new MapError(MapError.TAG_ERROR);
        if(!data.get(1).matches("(\\w|\\s)+")) throw new MapError(MapError.NAME_ERROR);
        if(!data.get(2).equals("Size-X:")) throw new MapError(MapError.TAG_ERROR);
        if(!data.get(3).matches("(\\d)+")) throw new MapError(MapError.X_ERROR);
        if(!data.get(4).equals("Size-Y:")) throw new MapError(MapError.TAG_ERROR);
        if(!data.get(5).matches("(\\d)+")) throw new MapError(MapError.Y_ERROR);
        if(!data.get(6).equals("Size-Tile:")) throw new MapError(MapError.TAG_ERROR);
        if(!data.get(7).matches("(\\d)+")) throw new MapError(MapError.SIZE_ERROR);
        if(!data.get(8).equals("Size-Item:")) throw new MapError(MapError.TAG_ERROR);
        if(!data.get(9).matches("(\\d)+")) throw new MapError(MapError.SIZE_ERROR);
        if(!data.get(10).equals("Size-NPC:")) throw new MapError(MapError.TAG_ERROR);
        if(!data.get(11).matches("(\\d)+")) throw new MapError(MapError.SIZE_ERROR);
        if(!data.get(12).equals("Size-Node:")) throw new MapError(MapError.TAG_ERROR);
        if(!data.get(13).matches("(\\d)+")) throw new MapError(MapError.SIZE_ERROR);
        String name     = data.get(1);
        int x           = Integer.parseInt(data.get(3));
        int y           = Integer.parseInt(data.get(5));
        int tileSize    = Integer.parseInt(data.get(7));
        int itemSize    = Integer.parseInt(data.get(9));
        int npcSize     = Integer.parseInt(data.get(11));
        int nodeSize    = Integer.parseInt(data.get(13));
        Map map = new Map(name,x,y,tileSize,itemSize,npcSize,nodeSize);
        if(!data.get(14).equals("Tile:")) throw new MapError(MapError.TAG_ERROR);
        for(int i = 0;i<tileSize;i++){
            if(!data.get(15+i).matches("(\\w|\\s)+")) throw new MapError(MapError.TILE_ERROR);
            String[] parts = data.get(15+i).split("(\\s)");
            if(!parts[0].matches("\\w")) throw new MapError(MapError.TILE_ERROR);
            if(!parts[1].matches("\\w+")) throw new MapError(MapError.TILE_ERROR);
            map.addNewTileId(parts[0].charAt(0), new Tile(parts[0].charAt(0),null,parts[1].equals(Tile.WALK)));
        }
        
        if(!data.get(15+tileSize).equals("Array:")) throw new MapError(MapError.TAG_ERROR);
        for(int i = 0;i<y;i++){
            if(!data.get(16+tileSize+i).matches("(\\d)+")) throw new MapError(MapError.ARRAY_ERROR);
            char[] chars = data.get(16+tileSize+i).toCharArray();
            for(char j = 0;j<chars.length;j++){
                map.setTile(chars[j], i, j);
            }
        }
        return map;
    }

    public static void main(String[] args){
        Map map = FileLoader.readMap("src\\alter\\Resources\\Maps\\f1.sam");
        java.lang.System.out.println(map);
    }*/
}
