package com.app.dizdarious.quickmath;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Dizdar on 2018-02-24.
 */

public class Division {
    private int firstNumber;
    private int secondNumber;
    private int numberHelper;
    private int result;
    private int randomResultOne;
    private int randomResultTwo;
    ArrayList<Integer> brojevi;

    private String operator;





    Division(int number){
        Random random = new Random();
        Random randomTwo = new Random();
        Random randomTree = new Random();
        Random randomFour = new Random();
        brojevi = new ArrayList<>();
        firstNumber = random.nextInt(number) + 1;
        int i;
        for (i = 1; i <= firstNumber; i++) {
            if (firstNumber % i == 0) {
                brojevi.add(i);
            }
        }


        numberHelper = randomTwo.nextInt(brojevi.size()) + 1;
        secondNumber = brojevi.get(numberHelper-1);


        operator = " - ";

        result = firstNumber / secondNumber;

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
