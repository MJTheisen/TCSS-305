package com.example;
import java.util.Scanner;
// Michael Theisen
/**
 *
 *    This file is the Object-Oriented Binary and Hexadecimal Calculator.
 *    It allows the user to choose between which calculator to use,
 *    then proceeds to turn both values, be they binary or hexadecimal,
 *    into integers that can then be added to each other, subtracted
 *    from, multiplied by or divided by with remainder. The program
 *    then returns the value back in the form of integer and either
 *    binary or hexadecimal according to the requirements of the
 *    online example calculators.
 */
public class Main {
    // the main method uses a scanner to take in input and refer to static methods
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nChoose a type of calculator.\n\n1. Binary Calculator\n2. Hexadecimal Calculator\n");
        int choice = scanner.nextInt();
        // this if triggers the binary methods
        if (choice == 1) {
            int decimal = firstBinaryToDecimal();
            int decimal2 = secondBinaryToDecimal();
            int answer = operationProcessor(decimal, decimal2);
            String binaryFinal = Integer.toBinaryString(answer);
            // this allows the decimal answer to be turned back into Binary.
            System.out.println("\nThat final answer in Binary is [" + binaryFinal + "].");
            // this else if triggers the hexadecimal methods
        } else if (choice == 2) {
            int decimal = firstHexadecimalToDecimal();
            int decimal2 = secondHexadecimalToDecimal();
            int answer = operationProcessor(decimal, decimal2);
            String hexadecimalFinal = Integer.toHexString(answer);
            // this while loop allows the decimal answer to be turned back into Hexadecimal.
            System.out.println("\nThat final answer in Hexadecimal is [" + hexadecimalFinal + "]. ");
        }
    }
    // this static int takes the first 8 digit binary value from the user and converts it to decimal
    private static int firstBinaryToDecimal() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter the first 8 digit Binary consisting of only 0s and 1s.\n");
        String binaryNumber = scanner.nextLine();
        return Integer.parseInt(binaryNumber, 2);
    }
    // this static int takes the second 8 digit binary value from the user and converts it to decimal
    private static int secondBinaryToDecimal() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter the second 8 digit Binary consisting of only 0s and 1s.\n");
        String binaryNumber2 = scanner.nextLine();
        return Integer.parseInt(binaryNumber2, 2);
    }
    // this static int takes the first 3 digit hexadecimal value from the user and converts it to decimal
    private static int firstHexadecimalToDecimal(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter the first 3 digit Hexadecimal consisting of only 0123456789ABCDEF.\n");
        String hexadecimalNumber = scanner.nextLine();
        return Integer.parseInt(hexadecimalNumber, 16);
    }
    // this static int takes the second 3 digit hexadecimal value from the user and converts it to decimal
    private static int secondHexadecimalToDecimal(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter the second 3 digit Hexadecimal consisting of only 0123456789ABCDEF.\n");
        String hexadecimalNumber = scanner.nextLine();
        return Integer.parseInt(hexadecimalNumber, 16);
    }
    // this static int takes in either the final decimal values from binary static in or hexadecimal static int and accepts an operation from the user.
    private static int operationProcessor(int decimal1, int decimal2) {
        System.out.print("\nEnter a number for an operator\n\n1. [+]  2. [-]  3. [*]  4. [/] \n\n");
        Scanner input = new Scanner(System.in);
        int operator = input.nextInt();
        int answer = 0 ;
        // this if, else if statement was about 3 lines shorter than the switch case. Still delivers the same algorithm.
        if (operator == 1) {
            answer = decimal1 + decimal2;
            System.out.println("\n[ " + decimal1 + " + " + decimal2 + " ]" + " = [" + answer + "] is the final answer in decimal.");
        } else if (operator == 2) {
            answer = decimal1 - decimal2;
            System.out.println("\n[ " + decimal1 + " - " + decimal2 + " ]" + " = [" + answer + "] is the final answer in decimal.");
        } else if (operator == 3) {
            answer = decimal1 * decimal2;
            System.out.println("\n[ " + decimal1 + " * " + decimal2 + " ]" + " = [" + answer + "] is the final answer in decimal.");
        } else if (operator == 4) {
            answer = decimal1 / decimal2;
            System.out.println("\n[ " + decimal1 + " / " + decimal2 + " ]" + " = [" + answer + "] is the final answer in decimal" + " with a remainder " + (decimal1 % decimal2));
        }
        return answer;
    }
}