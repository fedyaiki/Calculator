/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Operations;

public class Subtract implements Calculator.Operation {

    public Subtract() {
//        System.out.println("Hi, I'm Subtract");
    }

    public double doOperation(double... o) {
        double result = o[0];
        for (int i = 1; i < o.length; i++) {
            result -= o[i];
        }
        return result;
    }

}
