package com.example.calculatorapp;

public class Calculator {
    double numOne;
    double numTwo;

    public Calculator(){

    }
    public Calculator(double fNum, double sNum){
        this.numOne = fNum;
        this.numTwo = sNum;
    }

    public double getSum(){
        return this.numOne + this.numTwo;
    }

    public double getProd(){
        return this.numOne * this.numTwo;
    }

    public double getDiff(){
        return  this.numOne - this.numTwo;
    }

    public double getQou(){
        return this.numOne / this.numTwo;
    }

}
