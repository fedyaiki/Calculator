package Calculator;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {

    private double result;
//    флаг, отвечающий за наличие результата предыдущего вычисления
    private boolean setResult = false;

//    метод, считывающий float
    public double readNumber(Scanner sc, String number) {
        double op = 0;
        char ch;
        boolean flag = false;
        do {
            System.out.println("Operand " + number + " :");
            try {
//                пробуем считать число
                op = sc.nextFloat();
//            в случае, если было введено не число
            } catch (InputMismatchException ime) {
                try {
                    try {
                        String str = sc.next();
//                    если символов больше одного - выбрасываем исключение
                        if (str.length() != 1) {
                            throw new FormatException();
                        } else if ((ch = str.toCharArray()[0]) == '_') {
                            if (!this.setResult) {
                                System.out.println("There is no previous result");
                                throw new FormatException();
                            } else {
                                op = this.result;
                                flag = true;
                            }
//                    иначе выбрасываем исключение
                        } else {
                            throw new FormatException();
                        }
//                    пробрасываем исключение на верхний уровень
                    } catch (FormatException fe) {
                        throw new FormatException();
                    }
//                последняя возможность словить исключение
                } catch (FormatException fe) {
                    System.out.println(fe.toString());
                    continue;
                }
            }
//            если было введено число, выходим из цикла
            flag = true;
        } while (!flag);
        return op;
    }

//    метод, считывающий операцию
    public Operation readOperation(Scanner sc) {

        boolean flag = false;
        String s = null;
        Operation operation = null;
        ConfigReader cr = new ConfigReader();
        HashMap<String, Operation> map = cr.readConfid("./Operations.txt");
        do {
            System.out.println("Operation :");
            s = sc.next();
            operation = map.get(s);
        } while (map.get(s) == null);
        return operation;
    }

//    метод, в котором происходит все интересное
    public void calculateIt() {
        double op1, op2;
        Operation operation;
//        ну куда же тут без сканнера!
        Scanner sc = new Scanner(System.in);
        System.out.println("Console calculator, '_' stands for previous result");
        while (true) {
//            введение операндов и операции
            try {
                op1 = this.readNumber(sc, "1");
                operation = this.readOperation(sc);
                op2 = this.readNumber(sc, "2");

                Method method = operation.getClass().getDeclaredMethod("doOperation", double[].class);
                double[] operands = {op1, op2};
                this.result = ((Double) method.invoke(operation, operands)).doubleValue();

                System.out.println("Result: " + this.result);
                this.setResult = true;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Calculator c = new Calculator();
        c.calculateIt();
//        ConfigReader cr = new ConfigReader();
//        cr.readConfid();
    }

}