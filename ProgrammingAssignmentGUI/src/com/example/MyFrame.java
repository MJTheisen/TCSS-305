package com.example;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigInteger;

/**
 * This Programming Assignment #02 is a calculator implemented with a GUI.
 * This GUI uses a menu to navigate between 4 different types of calculators
 * that output answers based on the buttons and makes an attempt to follow
 * the example calculators online to the best extent possible including
 * quantity of text boxes and buttons.
 *
 */


public class MyFrame extends JFrame implements ActionListener {
    //button arrays for filling buttons and organizing their flow.
    //I didn't want to place specific buttons if I could help it.
    JButton[] buttonArray = new JButton[10];
    JButton[] functionsArray = new JButton[10];
    JButton[] bigNumberFunctionsArray = new JButton[11];

    JMenuBar menuBar;
    JMenu fileMenu;
    JMenuItem standardCalculator, binaryCalculator, hexadecimalCalculator, bigNumberCalculator, exitCalculator;
    JPanel standardPanel, binaryPanel, hexadecimalPanel, bigNumberPanel;
    JLabel standardLabel, binaryLabel, hexadecimalLabel, bigNumberLabel;
    JTextField field1, field2, answerField;
    JTextArea bigField;

    //creating buttons for standard calculator
    JButton additionButton, subtractionButton, multiplicationButton, divisionButton, modulusButton, decimalButton,
            equalButton, deleteButton, clearButton, negativeButton;

    //creating buttons for binary calculator
    JButton binaryAdditionButton, binarySubtractionButton, binaryMultiplicationButton, binaryDivisionButton;

    //creating buttons for hexadecimal calculator
    JButton hexadecimalAdditionButton, hexadecimalSubtractionButton, hexadecimalMultiplicationButton,
            hexadecimalDivisionButton;

    //creating buttons for bignumber calculator
    JButton bigAdditionButton, bigSubtractionButton, bigMultiplicationButton, bigDivisionButton,
            bigPowerButton, bigRootButton, bigSquareButton, bigFactorialButton, bigModButton,
            bigGCDButton, bigLCMButton;

    Font myFont = new Font("Helvetica",Font.BOLD,15);
    Border bigBorder = BorderFactory.createLineBorder(Color.black, 1);

    BigInteger factorial, xinput, yinput, additionBig, subtractionBig, multiplicationBig, divisionBig,
            powerBig, powerResult, rootBig, squareBig, modulusBig, gcdBig, lcmBig;

    int number1, number2;
    double value1, value2, result;
    char operator;
    String input1,input2, answer;

    MyFrame(){

        //start the JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,600);
        this.setLayout(null);

        //creates a menu to choose a calculator type.
        menuBar = new JMenuBar();
        fileMenu = new JMenu("CLICK HERE TO CHOOSE CALCULATOR TYPE");

        //populates text fields and empties them on click
        field1 = new JTextField("");
        field1.setBounds(10,50,300,25);
        field1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                field1.setText("");
            }
        });
        field2 = new JTextField("");
        field2.setBounds(10,80,300,25);
        field2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                field2.setText("");
            }
        });
        answerField = new JTextField(" Result will show here");
        answerField.setBounds(10,110,300,25);
        answerField.setEditable(false);

        bigField = new JTextArea(100,100);
        bigField.setBounds(320,10,650,500);
        bigField.setBorder(bigBorder);
        bigField.setLineWrap(true);
        bigField.setEditable(false);


        //creates button for binary addition using action listener
        binaryAdditionButton = new JButton("+");
        binaryAdditionButton.addActionListener(e -> {
            number1 = Integer.parseInt(field1.getText(),2);
            number2 = Integer.parseInt(field2.getText(),2);
            answer = String.valueOf(number1 + number2);
            answerField.setText(answer +" or in Binary, "+ Integer.toBinaryString(Integer.parseInt(answer)));
        });

        //creates button for binary subtraction using action listener
        binarySubtractionButton = new JButton("-");
        binarySubtractionButton.addActionListener(e -> {
            number1 = Integer.parseInt(field1.getText(),2);
            number2 = Integer.parseInt(field2.getText(),2);
            answer = String.valueOf(number1 - number2);
            answerField.setText(answer +" or in Binary, "+ Integer.toBinaryString(Integer.parseInt(answer)));
        });
        //creates button for binary multiplication using action listener
        binaryMultiplicationButton = new JButton("*");
        binaryMultiplicationButton.addActionListener(e -> {
            number1 = Integer.parseInt(field1.getText(),2);
            number2 = Integer.parseInt(field2.getText(),2);
            answer = String.valueOf(number1 * number2);
            answerField.setText(answer +" or in Binary, "+ Integer.toBinaryString(Integer.parseInt(answer)));
        });
        //creates button for binary division with remainder using action listener
        binaryDivisionButton = new JButton("/");
        binaryDivisionButton.addActionListener(e -> {
            number1 = Integer.parseInt(field1.getText(),2);
            number2 = Integer.parseInt(field2.getText(),2);
            answer = String.valueOf(number1 / number2);
            String answer2 = String.valueOf(number1 % number2);
            answerField.setText(answer + " Remainder " + answer2 + "  or in Binary, "
                    + Integer.toBinaryString(Integer.parseInt(answer)));
        });

        //creates button for hexadecimal addition using action listener
        hexadecimalAdditionButton = new JButton("+");
        hexadecimalAdditionButton.addActionListener(e -> {
            number1 = Integer.parseInt(field1.getText(),16);
            number2 = Integer.parseInt(field2.getText(),16);
            answer = String.valueOf(number1 + number2);
            answerField.setText(answer +" or in Hexadecimal, "+ Integer.toHexString(Integer.parseInt(answer)));
        });

        //creates button for hexadecimal subtraction using action listener
        hexadecimalSubtractionButton = new JButton("-");
        hexadecimalSubtractionButton.addActionListener(e -> {
            number1 = Integer.parseInt(field1.getText(),16);
            number2 = Integer.parseInt(field2.getText(),16);
            answer = String.valueOf(number1 - number2);
            answerField.setText(answer +" or in Hexadecimal, "+ Integer.toHexString(Integer.parseInt(answer)));
        });
        //creates button for hexadecimal multiplication using action listener
        hexadecimalMultiplicationButton = new JButton("*");
        hexadecimalMultiplicationButton.addActionListener(e -> {
            number1 = Integer.parseInt(field1.getText(),16);
            number2 = Integer.parseInt(field2.getText(),16);
            answer = String.valueOf(number1 * number2);
            answerField.setText(answer +" or in Hexadecimal, "+ Integer.toHexString(Integer.parseInt(answer)));
        });
        hexadecimalDivisionButton = new JButton("/");
        hexadecimalDivisionButton.addActionListener(e -> {
            number1 = Integer.parseInt(field1.getText(),16);
            number2 = Integer.parseInt(field2.getText(),16);
            answer = String.valueOf(number1 / number2);
            String answer2 = String.valueOf(number1 % number2);
            answerField.setText(answer + " Remainder " + answer2 + "  or in Hexadecimal, "
                    + Integer.toHexString(Integer.parseInt(answer)));
        });

        additionButton = new JButton("+");
        subtractionButton = new JButton("-");
        multiplicationButton = new JButton("*");
        divisionButton = new JButton("/");
        modulusButton = new JButton("%");
        decimalButton = new JButton(".");
        equalButton = new JButton("=");
        deleteButton = new JButton("DEL");
        clearButton = new JButton("C");
        negativeButton = new JButton("NEG");

        functionsArray[0] = decimalButton;
        functionsArray[1] = negativeButton;
        functionsArray[2] = additionButton;
        functionsArray[3] = subtractionButton;
        functionsArray[4] = multiplicationButton;
        functionsArray[5] = divisionButton;
        functionsArray[6] = modulusButton;
        functionsArray[7] = equalButton;
        functionsArray[8] = deleteButton;
        functionsArray[9] = clearButton;

        for(int i =0; i < 10; i++) {
            buttonArray[i] = new JButton(String.valueOf(i));
            buttonArray[i].addActionListener(this);
        }

        for(int i = 0; i < 10; i++) {
            functionsArray[i].addActionListener(this);
        }

        bigAdditionButton = new JButton("X+Y");
        bigSubtractionButton = new JButton("X-Y");
        bigMultiplicationButton = new JButton("X*Y");
        bigDivisionButton = new JButton("X/Y");
        bigPowerButton = new JButton("X^Y");
        bigRootButton= new JButton("ROOT X");
        bigSquareButton = new JButton("X^2");
        bigFactorialButton = new JButton("X!");
        bigModButton= new JButton("MOD");
        bigGCDButton = new JButton("GCD");
        bigLCMButton = new JButton("LCM");

        bigNumberFunctionsArray[0] = bigAdditionButton;
        bigNumberFunctionsArray[1] = bigSubtractionButton;
        bigNumberFunctionsArray[2] = bigMultiplicationButton;
        bigNumberFunctionsArray[3] = bigDivisionButton;
        bigNumberFunctionsArray[4] = bigPowerButton;
        bigNumberFunctionsArray[5] = bigRootButton;
        bigNumberFunctionsArray[6] = bigSquareButton;
        bigNumberFunctionsArray[7] = bigFactorialButton;
        bigNumberFunctionsArray[8] = bigModButton;
        bigNumberFunctionsArray[9] = bigGCDButton;
        bigNumberFunctionsArray[10] = bigLCMButton;

        for(int i = 0; i < 11; i++) {
            bigNumberFunctionsArray[i].addActionListener(this);
        }

        //create and modify the panel for the Standard Calculator
        standardPanel = new JPanel();
        standardPanel.setBounds(10, 90, 300, 375);
        standardPanel.setLayout(new GridLayout(7,3,5,5));

        //create and modify the panel for Binary Numbers
        binaryPanel = new JPanel();
        binaryPanel.setBounds(10, 150, 300, 375);
        binaryPanel.setLayout(new GridLayout(4,3,5,5));

        //create and modify the panel for Hexadecimal Numbers
        hexadecimalPanel = new JPanel();
        hexadecimalPanel.setBounds(10, 150, 300, 375);
        hexadecimalPanel.setLayout(new GridLayout(4,3,5,5));

        //create and modify the panel for Big Numbers
        bigNumberPanel = new JPanel();
        bigNumberPanel.setBounds(10, 150, 300, 375);
        bigNumberPanel.setLayout(new GridLayout(7,3,5,5));

        //create the new menu items
        standardCalculator = new JMenuItem("Standard Calculator");
        binaryCalculator = new JMenuItem("Binary Calculator");
        hexadecimalCalculator = new JMenuItem("Hexadecimal Calculator");
        bigNumberCalculator = new JMenuItem("Big Number Calculator");
        exitCalculator = new JMenuItem("Exit");

        //add action listeners to the menu items
        standardCalculator.addActionListener(this);
        binaryCalculator.addActionListener(this);
        hexadecimalCalculator.addActionListener(this);
        bigNumberCalculator.addActionListener(this);
        exitCalculator.addActionListener(this);

        //add the menus to the file menu
        fileMenu.add(standardCalculator);
        fileMenu.add(binaryCalculator);
        fileMenu.add(hexadecimalCalculator);
        fileMenu.add(bigNumberCalculator);
        fileMenu.add(exitCalculator);

        //add the fileMenu to the menuBar
        menuBar.add(fileMenu);
        this.setJMenuBar(menuBar);

        //add the panels to the menu selection
        this.add(standardPanel);
        this.add(binaryPanel);
        this.add(hexadecimalPanel);
        this.add(bigNumberPanel);

        //set the panels false at first
        standardPanel.setVisible(false);
        binaryPanel.setVisible(false);
        hexadecimalPanel.setVisible(false);
        bigNumberPanel.setVisible(false);

        //create labels for calculator type title
        standardLabel = new JLabel("Standard Calculator");
        standardLabel.setBounds(90,10,300,25);
        standardLabel.setFont(myFont);
        binaryLabel = new JLabel("Binary Calculator");
        binaryLabel.setBounds(100,10,300,25);
        binaryLabel.setFont(myFont);
        hexadecimalLabel = new JLabel("Hexadecimal Calculator");
        hexadecimalLabel.setBounds(75,10,300,25);
        hexadecimalLabel.setFont(myFont);
        bigNumberLabel = new JLabel("Big Number Calculator");
        bigNumberLabel.setBounds(80,10,300,25);
        bigNumberLabel.setFont(myFont);

        //add the labels
        this.add(standardLabel);
        this.add(binaryLabel);
        this.add(hexadecimalLabel);
        this.add(bigNumberLabel);

        //add buttons to standard panel
        standardPanel.add(buttonArray[0]);
        standardPanel.add(buttonArray[1]);
        standardPanel.add(buttonArray[2]);
        standardPanel.add(buttonArray[3]);
        standardPanel.add(buttonArray[4]);
        standardPanel.add(buttonArray[5]);
        standardPanel.add(buttonArray[6]);
        standardPanel.add(buttonArray[7]);
        standardPanel.add(buttonArray[8]);
        standardPanel.add(buttonArray[9]);
        standardPanel.add(buttonArray[9]);
        standardPanel.add(functionsArray[0]);
        standardPanel.add(functionsArray[1]);
        standardPanel.add(functionsArray[2]);
        standardPanel.add(functionsArray[3]);
        standardPanel.add(functionsArray[4]);
        standardPanel.add(functionsArray[5]);
        standardPanel.add(functionsArray[6]);
        standardPanel.add(functionsArray[7]);
        standardPanel.add(functionsArray[8]);
        standardPanel.add(functionsArray[9]);

        //add buttons to the big number panel
        bigNumberPanel.add(bigNumberFunctionsArray[0]);
        bigNumberPanel.add(bigNumberFunctionsArray[1]);
        bigNumberPanel.add(bigNumberFunctionsArray[2]);
        bigNumberPanel.add(bigNumberFunctionsArray[3]);
        bigNumberPanel.add(bigNumberFunctionsArray[4]);
        bigNumberPanel.add(bigNumberFunctionsArray[5]);
        bigNumberPanel.add(bigNumberFunctionsArray[6]);
        bigNumberPanel.add(bigNumberFunctionsArray[7]);
        bigNumberPanel.add(bigNumberFunctionsArray[8]);
        bigNumberPanel.add(bigNumberFunctionsArray[9]);
        bigNumberPanel.add(bigNumberFunctionsArray[10]);

        //make the labels invisible until calculator type clicked
        standardLabel.setVisible(false);
        binaryLabel.setVisible(false);
        hexadecimalLabel.setVisible(false);
        bigNumberLabel.setVisible(false);

        bigField.setVisible(true);
        this.getContentPane().add(bigField);

        //miscellaneous details of the frame
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    //this method is to deal with the recursive algorithm for power since math.pow doesn't work on bigintegers
    public BigInteger pow(BigInteger base, BigInteger exponent) {
        powerResult = BigInteger.ONE;
        while (exponent.signum() > 0) {
            if (exponent.testBit(0)) powerResult = powerResult.multiply(base);
            base = base.multiply(base);
            exponent = exponent.shiftRight(1);
        }
        return powerResult;
    }
    public BigInteger lcm(BigInteger xBig, BigInteger yBig) {
        if (xBig.signum() == 0 || yBig.signum() == 0)
            return BigInteger.ZERO;
        return xBig.divide(xBig.gcd(yBig)).multiply(yBig).abs();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==standardCalculator) {

            //show proper label and remove others
            standardLabel.setVisible(true);
            binaryLabel.setVisible(false);
            hexadecimalLabel.setVisible(false);
            bigNumberLabel.setVisible(false);

            //show text fields for input
            field1.setText(" ");

            //show proper panel and remove others
            standardPanel.setVisible(true);
            binaryPanel.setVisible(false);
            hexadecimalPanel.setVisible(false);
            bigNumberPanel.setVisible(false);

            //makes fields visible or not

            field1.setVisible(true);
            this.getContentPane().add(field1);
            field2.setVisible(false);
            this.getContentPane().add(field2);
            answerField.setVisible(false);
            this.getContentPane().add(answerField);
        }

        if(e.getSource()==binaryCalculator) {

            //show proper label and remove others
            standardLabel.setVisible(false);
            binaryLabel.setVisible(true);
            hexadecimalLabel.setVisible(false);
            bigNumberLabel.setVisible(false);

            //show text fields for input
            field1.setText(" Enter the First Binary Number");
            field2.setText(" Enter the Second Binary Number");
            answerField.setText(" Result will show here");

            //show proper panel and remove others
            standardPanel.setVisible(false);
            binaryPanel.setVisible(true);
            hexadecimalPanel.setVisible(false);
            bigNumberPanel.setVisible(false);

            //makes fields visible
            field1.setVisible(true);
            this.getContentPane().add(field1);
            field2.setVisible(true);
            this.getContentPane().add(field2);
            answerField.setVisible(true);
            this.getContentPane().add(answerField);

            //buttons. Online Example only has 4 buttons

            binaryPanel.add(binaryAdditionButton);
            binaryPanel.add(binarySubtractionButton);
            binaryPanel.add(binaryMultiplicationButton);
            binaryPanel.add(binaryDivisionButton);

        }

        if(e.getSource()==hexadecimalCalculator) {

            //show proper label and remove others
            standardLabel.setVisible(false);
            binaryLabel.setVisible(false);
            hexadecimalLabel.setVisible(true);
            bigNumberLabel.setVisible(false);

            //show text fields for input
            field1.setText(" Enter the First Hexadecimal Number");
            field2.setText(" Enter the Second Hexadecimal Number");
            answerField.setText(" Result will show here");

            //show proper panel and remove others
            standardPanel.setVisible(false);
            binaryPanel.setVisible(false);
            hexadecimalPanel.setVisible(true);
            bigNumberPanel.setVisible(false);

            //makes fields visible
            field1.setVisible(true);
            this.getContentPane().add(field1);
            field2.setVisible(true);
            this.getContentPane().add(field2);
            answerField.setVisible(true);
            this.getContentPane().add(answerField);

            //buttons. Online Example only has 4 buttons

            hexadecimalPanel.add(hexadecimalAdditionButton);
            hexadecimalPanel.add(hexadecimalSubtractionButton);
            hexadecimalPanel.add(hexadecimalMultiplicationButton);
            hexadecimalPanel.add(hexadecimalDivisionButton);

        }

        if(e.getSource()==bigNumberCalculator) {

            //show proper label and remove others
            standardLabel.setVisible(false);
            binaryLabel.setVisible(false);
            hexadecimalLabel.setVisible(false);
            bigNumberLabel.setVisible(true);

            //show text fields for input
            field1.setText(" Enter the X Value");
            field2.setText(" Enter the Y Value");
            answerField.setText(" Result will show here");
            bigField.setText("");

            //show proper panel and remove others
            standardPanel.setVisible(false);
            binaryPanel.setVisible(false);
            hexadecimalPanel.setVisible(false);
            bigNumberPanel.setVisible(true);

            //makes fields visible
            field1.setVisible(true);
            this.getContentPane().add(field1);
            field2.setVisible(true);
            this.getContentPane().add(field2);
            answerField.setVisible(false);
            this.getContentPane().add(answerField);

        }

        if(e.getSource()==exitCalculator) {
            System.exit(0);
        }

        //visible standard calc values
        for(int i=0;i<10;i++) {
            if(e.getSource() == buttonArray[i]) {
                field1.setText(field1.getText().concat(String.valueOf(i)));
            }
        }

        if (e.getSource() == decimalButton) {
            field1.setText(field1.getText().concat("."));
        }

        if (e.getSource() == negativeButton) {
            double x = Double.parseDouble(field1.getText());
            x *= -1;
            field1.setText(String.valueOf(x));
        }

        //standard calc functions
        if (e.getSource() == additionButton) {
            value1 = Double.parseDouble(field1.getText());
            operator = '+';
            field1.setText("");
        }

        if (e.getSource() == subtractionButton) {
            value1 = Double.parseDouble(field1.getText());
            operator = '-';
            field1.setText("");
        }

        if (e.getSource() == multiplicationButton) {
            value1 = Double.parseDouble(field1.getText());
            operator = '*';
            field1.setText("");
        }

        if (e.getSource() == divisionButton) {
            value1 = Double.parseDouble(field1.getText());
            operator = '/';
            field1.setText("");
        }

        if (e.getSource() == modulusButton) {
            value1 = Double.parseDouble(field1.getText());
            operator = '%';
            field1.setText("");
        }

        //switch case to implement operations
        if (e.getSource() == equalButton) {
            value2 = Double.parseDouble(field1.getText());

            switch (operator) {
                case '+' -> result = (value1 + value2);
                case '-' -> result = (value1 - value2);
                case '*' -> result = (value1 * value2);
                case '/' -> result = (value1 / value2);
                case '%' -> result = (value1 % value2);
            }
            field1.setText(String.valueOf(result));
            value1 = result; //to continue function if you want to keep going
        }

        //deletes a value by basically finding the last char and removing it
        if (e.getSource() == deleteButton) {
            String string = field1.getText();
            field1.setText("");
            for (int i = 0; i < string.length() - 1; i++) {
                field1.setText(field1.getText() + string.charAt(i));
            }
        }

        //sets field to blank
        if (e.getSource() == clearButton) {
            field1.setText("");
        }

        //buttons for big number calculator
        if (e.getSource() == bigAdditionButton) {
            input1 = String.valueOf(field1.getText());
            input2 = String.valueOf(field2.getText());
            xinput = new BigInteger(input1);
            yinput = new BigInteger(input2);
            additionBig = xinput.add(yinput);
            bigField.setText(String.valueOf(additionBig));
        }

        if (e.getSource() == bigSubtractionButton) {
            input1 = String.valueOf(field1.getText());
            input2 = String.valueOf(field2.getText());
            xinput = new BigInteger(input1);
            yinput = new BigInteger(input2);
            subtractionBig = xinput.subtract(yinput);
            bigField.setText(String.valueOf(subtractionBig));
        }

        if (e.getSource() == bigMultiplicationButton) {
            input1 = String.valueOf(field1.getText());
            input2 = String.valueOf(field2.getText());
            xinput = new BigInteger(input1);
            yinput = new BigInteger(input2);
            multiplicationBig = xinput.multiply(yinput);
            bigField.setText(String.valueOf(multiplicationBig));
        }

        if (e.getSource() == bigDivisionButton) {
            input1 = String.valueOf(field1.getText());
            input2 = String.valueOf(field2.getText());
            xinput = new BigInteger(input1);
            yinput = new BigInteger(input2);
            divisionBig = xinput.divide(yinput);
            bigField.setText(String.valueOf(divisionBig));

        }

        if (e.getSource() == bigPowerButton) {
            input1 = String.valueOf(field1.getText());
            input2 = String.valueOf(field2.getText());
            xinput = new BigInteger(input1);
            yinput = new BigInteger(input2);
            powerBig = pow(xinput,yinput);
            bigField.setText(String.valueOf(powerBig));
        }

        if (e.getSource() == bigRootButton) {
            input1 = String.valueOf(field1.getText());
            xinput = new BigInteger(input1);
            rootBig = xinput.sqrt();
            bigField.setText(String.valueOf(rootBig));

        }

        if (e.getSource() == bigSquareButton) {
            input1 = String.valueOf(field1.getText());
            xinput = new BigInteger(input1);
            squareBig = xinput.pow(2);
            bigField.setText(String.valueOf(squareBig));

        }

        if (e.getSource() == bigFactorialButton) {
            value1 = Double.parseDouble(field1.getText());
            factorial = BigInteger.ONE;
            for(int counter = (int) value1; counter >= 2; counter--) {
                factorial = factorial.multiply(BigInteger.valueOf(counter));
            }
            bigField.setText(String.valueOf(factorial));
            field1.setText("");
        }

        if (e.getSource() == bigModButton) {
            input1 = String.valueOf(field1.getText());
            input2 = String.valueOf(field2.getText());
            xinput = new BigInteger(input1);
            yinput = new BigInteger(input2);
            modulusBig = xinput.mod(yinput);
            bigField.setText(String.valueOf(modulusBig));

        }

        if (e.getSource() == bigGCDButton) {
            input1 = String.valueOf(field1.getText());
            input2 = String.valueOf(field2.getText());
            xinput = new BigInteger(input1);
            yinput = new BigInteger(input2);
            gcdBig = xinput.gcd(yinput);
            bigField.setText(String.valueOf(gcdBig));

        }

        if (e.getSource() == bigLCMButton) {
            input1 = String.valueOf(field1.getText());
            input2 = String.valueOf(field2.getText());
            xinput = new BigInteger(input1);
            yinput = new BigInteger(input2);
            lcmBig = lcm(xinput,yinput);
            bigField.setText(String.valueOf(lcmBig));
        }
    }
}

