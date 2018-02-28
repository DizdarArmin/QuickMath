package com.app.dizdarious.quickmath;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Dizdar on 2018-02-25.
 */

public class MathOperator {
    private int firstNumber;
    private int secondNumber;

    private int result;
    private int randomResultOne;
    private int randomResultTwo;


    private String operator;
    private Random random;



    MathOperator(){
        random = new Random();

    }

    public void addition (int number){
        firstNumber = random.nextInt(number);
        secondNumber = random.nextInt(number);
        operator = "+";
        result = firstNumber + secondNumber;

        randomResultOne = random.nextInt(result + result);
        randomResultTwo = random.nextInt(result);
    }

    public void subtraction (int number) {
        firstNumber = random.nextInt(number) + 1;
        secondNumber = random.nextInt(firstNumber);
        operator = "-";
        result = firstNumber - secondNumber;

        randomResultOne = random.nextInt(result * 2);
        randomResultTwo = random.nextInt(result + result/2);
    }

    public void multiplication (int number){
        firstNumber = random.nextInt(number) + 1;
        secondNumber = random.nextInt(number ) + 1;
        operator = "*";

        result = firstNumber * secondNumber;

        randomResultOne = random.nextInt(result + result) + 1;
        randomResultTwo = random.nextInt(result - result/5) + 1;
    }

    public void division (int number){
        ArrayList<Integer> allDivisors = new ArrayList<>();
        int numberHelper;
        int i;
        operator = "/";
        firstNumber = random.nextInt(number) + 1;

        for (i = 1; i <= firstNumber; i++) {
            if (firstNumber % i == 0) {
                allDivisors.add(i);
            }
        }


        numberHelper = random.nextInt(allDivisors.size()) + 1;
        secondNumber = allDivisors.get(numberHelper - 1);


        result = firstNumber / secondNumber;

        randomResultOne = random.nextInt(result * 5) + 1;
        randomResultTwo = random.nextInt(result * 4) + 1;
    }



    int getFirstNumber(){
        return firstNumber;
    }
    int getSecondNumber(){
        return secondNumber;
    }
    int getResult(){
        return result;
    }

    int getRandomResultOne(){
        return randomResultOne;
    }
    int getRandomResultTwo(){
        return randomResultTwo;
    }

    public String getOperator() {
        return operator;
    }


}
