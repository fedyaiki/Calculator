/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Operations;

/**
 *
 * @author Федор
 */
public class Divide implements Calculator.Operation {

    public Divide() {
//        System.out.println("Hi, I'm Divide");
    }

    public double doOperation(double... o) {
        double result = o[0];
        for (int i = 1; i < o.length; i++) {
            result = result / o[i];
        }
        return result;
    }

}
