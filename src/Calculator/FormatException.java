package Calculator;

public class FormatException extends Exception {

    @Override
    public String toString() {
        return "Wrong format of the operand";
    }
}