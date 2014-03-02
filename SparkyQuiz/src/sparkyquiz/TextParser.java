/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sparkyquiz;

/**
 *
 * @author Scalene
 */
import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.FileReader;

public class TextParser extends ArrayList<String>{
    private int maxSize;
    
    public TextParser(FileReader reader){
    	super();
    	try{
	    BufferedReader file = new BufferedReader(reader);
            int i = 0;
            while(file.ready()){
                String in = file.readLine();
        	if(i == 0){
                    add(in);
                    i++;
        	} else if(i == 1){
                    add(in);
                    i++;
        	} else {
                    i = 0;
       		}
            }
	} catch(java.io.FileNotFoundException e){
            System.err.println(">>>>" + e.getMessage() );
            e.printStackTrace();
        } catch(java.io.IOException e){
            System.err.println(">>>>" + e.getMessage() );
            e.printStackTrace();
        }
        maxSize = size();
    }
    public ExtraString next(){
    	return new ExtraString(remove(size()-2).trim(),remove(size()-1).trim(),0);
    }

    public boolean hasNext(){
        return size() > 1;
    }

    public int maxSize(){
        return maxSize;
    }
}
