package com.example;
// Michael Theisen
/**
 *
 *    This file is the Procedural Binary and Hexadecimal Calculator.
 *    It allows the user to choose between which calculator to use,
 *    then proceeds to turn both values, be they binary or hexadecimal,
 *    into integers that can then be added to each other, subtracted
 *    from, multiplied by or divided by with remainder. The program
 *    then returns the value back in the form of integer and either
 *    binary or hexadecimal according to the requirements of the
 *    online example calculators.
 */

import java.util.Scanner;
public class Main {
    // the main method uses a scanner to take in input and refer to static methods
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nChoose a type of calculator.\n\n1. Binary Calculator\n2. Hexadecimal Calculator\n");
        int choice = scanner.nextInt();
        switch(choice) {
            // this case triggers the binary methods
            case 1 -> {
                int decimal = firstBinaryToDecimal();
                int decimal2 = secondBinaryToDecimal();
                int answer = operationProcessor(decimal, decimal2);
                String binaryFinal = "";
                // this while loop allows the decimal answer to be turned back into Binary.
                while(true){
                    int r = (answer % 2);
                    answer = (answer / 2);
                    binaryFinal = (r + binaryFinal);
                    if (answer == 0) break;
                }
                System.out.println("\nThat final answer in Binary is [" + binaryFinal + "].");
            }
            // this case triggers the hexadecimal methods
            case 2 -> {
                int decimal = firstHexadecimalToDecimal("");
                int decimal2 = secondHexadecimalToDecimal("");
                int answer = operationProcessor(decimal, decimal2);
                String hexadecimalFinal = "";
                char hex[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
                // this while loop allows the decimal answer to be turned back into Hexadecimal.
                while(answer>0) {
                    int r = (answer % 16);
                    hexadecimalFinal = (hex[r] + hexadecimalFinal);
                    answer = (answer / 16);
                }
                System.out.println("\nThat final answer in Hexadecimal is [" + hexadecimalFinal + "]. ");
            }
            default -> {System.out.println("Please choose a correct answer.");}
        }
    }
    // this static int takes the first 8 digit binary value from the user and converts it to decimal
    private static int firstBinaryToDecimal() {
        Scanner scanner = new Scanner(System.in);
        int lastDigit, decimal = 0, i = 0, result = 1, base = 2;
        System.out.println("\nEnter the first 8 digit Binary consisting of only 0s and 1s.\n");
        // Input the first binary number;
        int binary = scanner.nextInt();
        // First binary is converted to Decimal value.
        while (binary > 0) {
            lastDigit = binary % 10;
            // This while loop replaces Math.pow(2,i).
            while (i > 0) {
                result = result * base;
                i--;
            }
            decimal += result * lastDigit;
            binary = binary / 10;
            i++;
        }
        return decimal;
    }
    // this static int takes the second 8 digit binary value from the user and converts it to decimal
    private static int secondBinaryToDecimal() {
        Scanner scanner = new Scanner(System.in);
        int lastDigit2, decimal2 = 0, j = 0, result2 = 1, base = 2;
        System.out.println("\nEnter the second 8 digit Binary consisting of only 0s and 1s.\n");
        //Input the second binary number
        int binary2 = scanner.nextInt();
        // Second binary is converted to Decimal value.
        while (binary2 > 0) {
            lastDigit2 = binary2 % 10;
            // This while loop replaces Math.pow(2,j).
            while (j > 0) {
                result2 = result2 * base;
                j--;
            }
            decimal2 += result2 * lastDigit2;
            binary2 = binary2 / 10;
            j++;
        }
        return decimal2;
    }
    // this static int takes the first 3 digit hexadecimal value from the user and converts it to decimal
    private static int firstHexadecimalToDecimal(String hexadecimal){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter the first 3 digit Hexadecimal consisting of only 0123456789ABCDEF.\n");
        //Input the first hexadecimal number
        hexadecimal = scanner.nextLine();
        String fullHexSet = "0123456789ABCDEF";
        int decimal = 0;
        for (int i = 0; i < hexadecimal.length(); i++) {
            char hexSetChar = hexadecimal.charAt(i);
            int j = fullHexSet.indexOf(hexSetChar);
            decimal = 16 * decimal + j;
        }
        return decimal;
    }
    // this static int takes the second 3 digit hexadecimal value from the user and converts it to decimal
    private static int secondHexadecimalToDecimal(String hexadecimal){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter the second 3 digit Hexadecimal consisting of only 0123456789ABCDEF.\n");
        //Input the second hexadecimal number
        hexadecimal = scanner.nextLine();
        String fullHexSet = "0123456789ABCDEF";
        int decimal2 = 0;
        for (int i = 0; i < hexadecimal.length(); i++) {
            char hexSetChar = hexadecimal.charAt(i);
            int j = fullHexSet.indexOf(hexSetChar);
            decimal2 = 16 * decimal2 + j;
        }
        return decimal2;
    }
    // this static int takes in either the final decimal values from binary static in or hexadecimal static int and accepts an operation from the user.
    private static int operationProcessor(int decimal1, int decimal2) {
        System.out.print("\nEnter a number for an operator\n\n1. [+]  2. [-]  3. [*]  4. [/] \n\n");
        Scanner input = new Scanner(System.in);
        int operator = input.nextInt();
        int answer = 0 ;
        switch (operator) {
            case 1 -> { answer = decimal1 + decimal2;
                System.out.println("\n[ " + decimal1 + " + " + decimal2 + " ]" + " = [" + answer + "] is the final answer in decimal.");
            }
            case 2 -> { answer = decimal1 - decimal2;
                System.out.println("\n[ " + decimal1 + " - " + decimal2 + " ]" + " = [" + answer + "] is the final answer in decimal.");
            }
            case 3 -> { answer = decimal1 * decimal2;
                System.out.println("\n[ " + decimal1 + " * " + decimal2 + " ]" + " = [" + answer + "] is the final answer in decimal.");
            }
            case 4 -> { answer = decimal1 / decimal2;
                System.out.println("\n[ " + decimal1 + " / " + decimal2 + " ]" + " = [" + answer + "] is the final answer in decimal" + " with a remainder " + (decimal1 % decimal2));
            }
            default -> System.out.println("no");
        }
        return answer;
    }
}