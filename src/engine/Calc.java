package engine;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calc {

	public static void main(String[] args) {
		// Declare all the variables needed
		String operand1 = null,
				sign = null,
				operand2 = null; 
		double result = 0, firstValue = 0, secondValue = 0;
		
		// Introduction to program
		calculatorIntro();
		
		// Scan the operation
		String operation = scan();
		// Split the three different parts of the calculation through RegEx
		firstValue = setFirstValue(operand1, operation);
		sign = setBinaryOperation(sign, operation);
		secondValue = setSecondValue(operand2, operation);
		// Do the operation
		result = calculationProcess(sign, result, firstValue, secondValue);
		
	}

	private static double calculationProcess(String sign, double result, double firstValue, double secondValue) {
		switch(sign) {
			case "+":
				result = firstValue+secondValue;
				break;
			case "-":
				result = firstValue-secondValue;
				break;
			case "*":
				result = firstValue*secondValue;
				break;
			case "/":
				result = firstValue/secondValue;
				break;
			case "^":
				result = Math.pow(firstValue, secondValue);
				break;
		}
		System.out.println(result);
		return result;
	} 

	private static String setBinaryOperation(String sign, String operation) {
		// 2. Operation sign
		Pattern operationSign = Pattern.compile("[-+*/^]");
		// Type of operation
		Matcher matcher2 = operationSign.matcher(operation);
		if(matcher2.find()) {			
			sign = matcher2.group(0);
		}
		return sign;
	}

	private static double setSecondValue(String operand2, String operation) {
		// 3. Second digits group
		Pattern secondOperand = Pattern.compile("\\d+$");
		// Second number
		Matcher matcher3 = secondOperand.matcher(operation);
		if (matcher3.find()) {
			operand2 = matcher3.group(0);
		}
		return Double.parseDouble(operand2);
	}

	private static double setFirstValue(String operand1, String operation) {
		// 1. First digits group: Optional (-) plus 1 or more digits 
		Pattern firstOperand = Pattern.compile("^-?\\d+");
		// First number
		Matcher matcher1 = firstOperand.matcher(operation);
		if (matcher1.find()) {
			operand1 = matcher1.group(0);
		}
		return Double.parseDouble(operand1);
	}

	private static void calculatorIntro() {
		// Prompt user
		System.out.println("This is a simple calculator that works from the terminal.");
		System.out.println("It accepts sums ( + ), substractions ( - ), multiplications ( * ), divisions ( / ) and powers ( ^ )");
		System.out.println("Type the calculation as follows: 2 + 2 ( ENTER )");
		System.out.println("Enter now the operation you want to do");
	}

	private static String scan() {
		// Open Scanner
		Scanner sc =  new Scanner(System.in);
		// Prompt the operation and close Scanner
		String operation = sc.nextLine();
		sc.close();
		// Remove whitespace
		operation.replaceAll("\\s", "");
		return operation;
	}
}


