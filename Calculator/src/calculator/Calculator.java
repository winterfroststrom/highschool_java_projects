package calculator;

/**
 * @(#)Calculator.java
 *
 * Calculator application
 *
 * @author Sparky
 * @version 1.00 2009/10/10
 */
import java.util.Scanner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    public static void main(String[] args) {

    	Scanner scanner = new Scanner(System.in);

    	String input = scanner.nextLine();
    	input = input.replaceAll("\\s+","");

    	Pattern inputPattern = Pattern.compile("[=]");
    	Matcher inputMatcher = inputPattern.matcher(input);
    	if(inputMatcher.find()){
    		int i = 0;
    		int j = 0;

    		j = inputMatcher.start();
    		String firstHalf = input.substring(0,j);
    		String secondHalf = input.substring(j+1);

    		Pattern equationSplit = Pattern.compile("[a-zA-Z]+");
    		Matcher firstMatcher = equationSplit.matcher(firstHalf);
    		Matcher secondMatcher = equationSplit.matcher(secondHalf);
    		float sum = 0;
    		String varName = "";
    		if(firstMatcher.find()){
    			float otherSide = subadd(secondHalf);
    			i = firstMatcher.start();
    			j = firstMatcher.end();
    			if(i == 0){
    				varName = firstHalf.substring(i,j);
    				firstHalf = firstHalf.replace(varName.subSequence(0,varName.length()),"");
					sum = otherSide - subadd(firstHalf);
    			} else {
    				String sign = firstHalf.substring(i-1,i);
    				String signVarName = firstHalf.substring(i-1,j);
    				firstHalf = firstHalf.replace(signVarName.subSequence(0,signVarName.length()),"");
    				if(sign.equals("-")){
    					sum = -(otherSide - subadd(firstHalf));
    				} else if(sign.equals("+")){
						sum = otherSide - subadd(firstHalf);
    				}
    				varName = signVarName.substring(1);
    			}
    		} else if(secondMatcher.find()){
    			float otherSide = subadd(firstHalf);
    			i = secondMatcher.start();
    			j = secondMatcher.end();
    			if(i == 0){
    				varName = secondHalf.substring(i,j);
    				secondHalf = secondHalf.replace(varName.subSequence(0,varName.length()),"");
					sum = otherSide - subadd(secondHalf);
    			} else {
    				String sign = secondHalf.substring(i-1,i);
    				String signVarName = secondHalf.substring(i-1,j);
    				secondHalf = secondHalf.replace(signVarName.subSequence(0,signVarName.length()),"");
    				if(sign.equals("-")){
	    				sum = -(otherSide - subadd(secondHalf));
    				} else if(sign.equals("+")){
						sum = otherSide - subadd(secondHalf);
    				}
    				varName = signVarName.substring(1);
    			}
    		}
    		System.out.println(varName + " = " + sum);
    	} else {
    		System.out.println(subadd(input));
    	}
    }

    static float subadd(String expression){
    	if(expression.equals("")){
    		return 0;
    	}
    	Pattern expressionPattern = Pattern.compile("[-+]");
    	String check = expression.substring(0,1);
    	if(check.equals("-")){
    		expression = expression.substring(1);
    		return subAddHelp(expression,expressionPattern.matcher(expression),0,1);
    	} else if(check.equals("+")){
    		expression = expression.substring(1);
    		return subAddHelp(expression,expressionPattern.matcher(expression),0,0);
    	}
    	return subAddHelp(expression,expressionPattern.matcher(expression),0,0);
    }

    private static float subAddHelp(String expression, Matcher expressionMatcher,int begin, int signVal){
    	if(expressionMatcher.find()){
    		int i = expressionMatcher.start();
    		Float sum = Float.parseFloat(expression.substring(begin,i));
    		if(signVal==1){
    			sum = -sum;
    		}
    		String sign = expression.substring(i,i+1);
    		if(sign.equals("+")){
    			return sum + subAddHelp(expression,expressionMatcher,i+1,0);
    		} else if(sign.equals("-")) {
    			return sum + subAddHelp(expression,expressionMatcher,i+1,1);
    		} else {
    			return sum;
    		}
    	} else {
    		if(signVal==1){
    			return -Float.parseFloat(expression.substring(begin));
    		}
    		return Float.parseFloat(expression.substring(begin));
    	}
    }
}
