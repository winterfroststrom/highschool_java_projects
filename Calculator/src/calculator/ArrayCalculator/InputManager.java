/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package calculator.ArrayCalculator;

/**
 *
 * @author Scalene
 */

public class InputManager {
    private final String originalString;

    public InputManager(String input){
        originalString = input.replaceAll("\\s+","");
    }
}