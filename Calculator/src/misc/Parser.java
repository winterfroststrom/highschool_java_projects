/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package misc;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
/**
 *
 * @author Scalene
 */
public class Parser {
    public static void parsesite(){
        try{
            URL a = new URL("http://www.freewebs.com/fuzzypaws/accessory.html");
            URLConnection as = a.openConnection();
            InputStream in = as.getInputStream();
            InputStreamReader inr = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(inr);
            BufferedWriter out = new BufferedWriter(new FileWriter("text.text"));
            String output;
            int all = 0;
            while((output = br.readLine()) != null){
                if(!br.ready())
                    Thread.sleep(100);
                //output = output.replaceAll("<[\\w|=|\"|'|:|#|/|%|$|@|!|^|&|(|)|.]+>", "\n\r");
                output = output.replaceAll("[\\t\\n\\x0B\\f\\r]+", "");
                output = output.replaceAll("[ ]+", " ");
                all++;

                out.write(output);
                System.out.println(output);
                if(all>100){
                    all = 0;
                    out.flush();
                }
            }
            in.close();
            inr.close();

            br.close();
            out.close();
        } catch(MalformedURLException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    public static void parsefile(String from, String to){
        try{
            File af = new File(from);
            FileReader as = new FileReader(af);


            BufferedReader br = new BufferedReader(as);
            BufferedWriter out = new BufferedWriter(new FileWriter(to));
            String output;
            int all = 0;
            while((output = br.readLine()) != null){
                if(!br.ready())
                    Thread.sleep(100);
                //output = output.replaceAll("\\{[\\w|=|\"|'|:|#|/|%|$|@|!|^|&|(|)|.|,|;|\\| |_|-|+|?]+\\}", "\n\r");
                //output = output.replaceAll("function", "");
                //output = output.replaceAll("\\(\\)", "\n\r\n\r");
                //output = output.replaceAll("[\\t\\n\\x0B\\f\\r]+", "");
                //output = output.replaceAll("<[\\w|=|\"|'|:|#|/|%|$|@|!|^|&|(|)|.|,|;|\\| |_|-|+|?]+>", "\n\r");
                //output = output.replaceAll("Aerial   Divine   Normal   Imp     African   Earth   Plushie  Spitz     Bane   Egyptian   Tempest     Corsie   Fire   Water", "\n\r\n\r");
                //output = output.replaceAll("&#39;s ear.", "");




                //output = output.replaceAll("[\\t\\x0B\\f\\n\\r]+", " ");
                //output = output.replaceAll("  ", "\n\r\n\r");
                output = output.replaceAll("\n\r\n\r", "\n\r");
                out.write(output);
                System.out.println(output);
                all++;
                if(all>100){
                    all = 0;
                    out.flush();
                }
            }
            br.close();
            out.close();

        } catch(MalformedURLException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        //parsesite();
        //parsefile("C:\\Documents and Settings\\Scalene\\My Documents\\NetBeansProjects\\Calculator\\text.text","text2.text");
        parsefile("C:\\Documents and Settings\\Scalene\\My Documents\\NetBeansProjects\\Calculator\\text2.text","text.text");

    }
}
