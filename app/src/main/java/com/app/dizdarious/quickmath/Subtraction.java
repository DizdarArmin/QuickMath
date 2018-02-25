package com.app.dizdarious.quickmath;

import java.util.Random;

/**
 * Created by Dizdar on 2018-02-24.
 */

public class Subtraction {
    private int firstNumber;
    private int secondNumber;
    private int result;
    private int randomResultOne;
    private int randomResultTwo;

    private String operator;





    Subtraction(int number){
        Random random = new Random();
        Random randomTwo = new Random();
        Random randomTree = new Random();
        Random randomFour = new Random();
        firstNumber = random.nextInt(number) + 1;
        secondNumber = randomTwo.nextInt(firstNumber - 1 ) + 1;
        result = firstNumber - secondNumber;

        randomResultOne = randomTree.nextInt(result - 1) + 1;
        randomResultTwo = randomFour.nextInt(result + 10) + 1;



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
