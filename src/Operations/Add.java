/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Operations;

public class Add implements Calculator.Operation {

    public Add() {
//        System.out.println("Hi, I'm Add");
    }

    public double doOperation(double... o) {
        double result = 0;
        for (int i = 0; i < o.length; i++) {
            result += o[i];
        }
        return result;
    }

}
