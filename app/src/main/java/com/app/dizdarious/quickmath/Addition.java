package com.app.dizdarious.quickmath;

import java.util.Random;

/**
 * Created by Dizdar on 2018-02-22.
 */

public class Addition {

    private int firstNumber;
    private int secondNumber;
    private int result;
    private int randomResultOne;
    private int randomResultTwo;
    private int randomResultTree;
    private int randomizerOne;
    private int randomizerTwo;
    private String operator;

    private int mathLevel;




    Addition(int number){

            Random random = new Random();
            Random randomTwo = new Random();
            firstNumber = random.nextInt(number) + 1;
            secondNumber = random.nextInt(number) + 1;
            operator = "+";


         randomizerOne = random.nextInt(number + (number/3)) + 1;
         randomizerTwo = randomTwo.nextInt(number - (number/2)) + 1;

        result = firstNumber + secondNumber;

        randomResultOne = firstNumber + randomizerOne;
        randomResultTwo = result - randomizerTwo;
        //randomResultTree = (random.nextInt(firstNumber + secondNumber)) + randomizer;



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
    int getRandomResultTree(){
        return randomResultTree;
    }

    public String getOperator() {
        return operator;
    }


}
