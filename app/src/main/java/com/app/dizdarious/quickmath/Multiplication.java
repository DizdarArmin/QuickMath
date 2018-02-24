package com.app.dizdarious.quickmath;

import java.util.Random;

/**
 * Created by Dizdar on 2018-02-24.
 */

public class Multiplication {

    private int firstNumber;
    private int secondNumber;
    private int result;
    private int randomResultOne;
    private int randomResultTwo;

    private String operator;





    Multiplication(int number){
        Random random = new Random();
        Random randomTwo = new Random();
        Random randomTree = new Random();
        Random randomFour = new Random();

        firstNumber = random.nextInt(number) + 1;
        secondNumber = randomTwo.nextInt(number ) + 1;
        operator = " * ";

        result = firstNumber * secondNumber;

        randomResultOne = randomTree.nextInt(result - result/2) + 1;
        randomResultTwo = randomFour.nextInt(result + result/2) + 1;



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