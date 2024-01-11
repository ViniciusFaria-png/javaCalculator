package com.mycompany.swingcalculator;

import java.util.ArrayList;
import java.util.List;

public class SwingCalculator {
    public static void main(String[] args)
    {
        SwingCalculator c=new SwingCalculator();
        System.out.println(">>>>"+c.calculator("5/5"));
    }

    public String calculator(String expression){
        String result;
        List<Double> numberList;
        List<Character> operatorsList;


        numberList = getNumber(expression);
        operatorsList = getOperator(expression);

        result = valueCalculator(numberList, operatorsList);

        return result;
    }

    private String valueCalculator(List<Double> numberList, List<Character> operatorsList){
        String result;
        double total = 0.0;
        int j=0;
        for (int i = 0; i < numberList.size()-1; i++){
            if (total ==0.0){
                double n1 = numberList.get(i);
                double n2 = numberList.get(i+1);
                char operator = operatorsList.get(i);
                total = opExec(n1, operator, n2);
            }

            else if (total > 0.0)
            {
                double n2 = numberList.get(i);
                char operator = operatorsList.get(j);
                total = opExec(total, operator, n2);
                j++;
            }
        }

        result = ""+total;
        return result;
    }

    private double opExec(double n1, char operator, double n2){
        double result = 0.0;

        if(operator == '+'){
            result = n1 + n2;
        }else if(operator == '-'){
            result = n1 - n2;
        }else if(operator == '/'){
            result = n1/n2;
        }else if(operator == '*'){
            result = n1*n2;
        }
        return result;

    }

    private List<Double> getNumber(String expression){
        List<Double> numberList = new ArrayList<>();

        String nString = "";
        for (int i = 0; i < expression.length(); i++){
            if(isOperator(expression.charAt(i))){
                Double n = Double.valueOf(nString);
                numberList.add(n);
                nString = "";
            } else{
                nString = nString.concat(""+expression.charAt(i));
            }
        }
        if(!nString.isEmpty()){
            Double n = Double.valueOf(nString);
            numberList.add(n);
        }
        return numberList;
    }

    private List<Character> getOperator(String expression){
        List<Character> operatorList = new ArrayList<>();

        for(int i = 0; i<expression.length();i++){
            if(isOperator(expression.charAt(i))){
                operatorList.add(expression.charAt(i));
            }
        }

        return operatorList;
    }

    private boolean isOperator(char caracter){
        boolean isOperator = false;
        if (String.valueOf(caracter).matches("[-+*/]")){
            isOperator = true;
        }

        return isOperator;
    }
}
