package calculator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {

    private float result;
//    флаг, отвечающий за наличие результата предыдущего вычисления
    private boolean setResult = false;
    private final char[] operations = {'+', '-', '*', '/'};
    private final String version = "v1.1";

//    метод, проверяющий, допустима ли операция
    public boolean checkOperation(char c) {
        boolean flag = false;
        for (int i = 0; i < operations.length; i++) {
            if (this.operations[i] == c) {
                flag = true;
            }
        }
        return flag;
    }
//    метод, считывающий float

    public float readFloat(Scanner sc, String number) throws GoodByeException {
        float op = 0;
        char ch;
        boolean flag = false;
        do {
            System.out.println("Operand " + number + ", please:");
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
//                    если пользователь хочет выйти
                        } else if ((ch = str.toCharArray()[0]) == 'x') {
                            throw new GoodByeException();
//                    если происходит обращение к предыдущему результату
                        } else if (ch == '_') {
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
    public char readChar(Scanner sc) throws GoodByeException {
        boolean flag = false;
        char[] chars = null;
        do {
            System.out.println("Operation, please:");
            chars = sc.next().toCharArray();
            if (chars[0] == 'x') {
                throw new GoodByeException();
            } else if ((chars.length != 1) || (!this.checkOperation(chars[0]))) {
                System.out.println("This operation is not supported yet");
                continue;
            } else {
                flag = true;
            }
        } while (!flag);
        return chars[0];
    }

//    метод, в котором происходит все интересное
    public void calculateIt() {
        float op1, op2;
        char ch;
//        ну куда же тут без сканнера!
        Scanner sc = new Scanner(System.in);
        System.out.println("Console calculator " + this.version + '\n' +
                "'_' stands for previous result" + '\n' + "Type 'x' to exit");
//        цикл на все время работы программы
        while (true) {
//            введение операндов и операции
            try {
                op1 = this.readFloat(sc, "1");
                ch = this.readChar(sc);
                op2 = this.readFloat(sc, "2");
            } catch (GoodByeException ge) {
                System.out.println(ge.toString());
                return;
            }
            try {
                switch (ch) {
                    case '+':
                        this.result = op1 + op2;
                        break;
                    case '-':
                        this.result = op1 - op2;
                        break;
                    case '*':
                        this.result = op1 * op2;
                        break;
                    case '/':
                        if (op2 == 0) {
                            throw new ArithmeticException();
                        }
                        this.result = op1 / op2;
                        break;
                }
            } catch (ArithmeticException ae) {
                System.out.println("Division by zero!");
                continue;
            }
            System.out.println("Result: " + this.result);
            this.setResult = true;
        }
    }

    public static void main(String[] args) {
        Calculator c = new Calculator();
        c.calculateIt();
    }

}
