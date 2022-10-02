import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.print("Введите выражение: ");
        Scanner scan = new Scanner(System.in);
        System.out.println("Итог: " + calc(scan.nextLine()));
    }

    public static String calc(String input) {

        RomanNumeral romanTotal = null;
        String total = null;
        String[] getOperandsAndOperator = input.split(" ");
        String firstOperand = getOperandsAndOperator[0];
        char operator = getOperandsAndOperator[1].charAt(0);
        String secondOperand = getOperandsAndOperator[2];
        if (getOperandsAndOperator.length > 3) {
            throw new RuntimeException("Введите корректное выражение");
        }
        else if (operandIsArabic(firstOperand) && operandIsArabic(secondOperand)) {
            switch (operator) {
                case '+':
                    total = String.valueOf(Integer.parseInt(firstOperand) + Integer.parseInt(secondOperand));
                    break;
                case '-':
                    total = String.valueOf(Integer.parseInt(firstOperand) - Integer.parseInt(secondOperand));
                    break;
                case '*':
                    total = String.valueOf(Integer.parseInt(firstOperand) * Integer.parseInt(secondOperand));
                    break;
                case '/':
                    total = String.valueOf(Integer.parseInt(firstOperand) / Integer.parseInt(secondOperand));
                    break;
                default:
                    throw new RuntimeException("Введите корректный знак операции: +, -, *, /");
            }
        } else if (operandIsRoman(firstOperand) && operandIsRoman(secondOperand)) {
            RomanNumeral firstRoman = RomanNumeral.valueOf(firstOperand);
            RomanNumeral secondRoman = RomanNumeral.valueOf(secondOperand);
            switch (operator) {
                case '+':
                    total = String.valueOf(romanTotal = RomanNumeral.getRomByInt
                            (firstRoman.getArabic() + secondRoman.getArabic()));
                    break;
                case '-':
                    total = String.valueOf(romanTotal = RomanNumeral.getRomByInt
                            (firstRoman.getArabic() - secondRoman.getArabic()));
                    break;
                case '*':
                    total = String.valueOf(romanTotal = RomanNumeral.getRomByInt
                            (firstRoman.getArabic() * secondRoman.getArabic()));
                    break;
                case '/':
                    total = String.valueOf(romanTotal = RomanNumeral.getRomByInt
                            (firstRoman.getArabic() / secondRoman.getArabic()));
                    break;
                default:
                    throw new RuntimeException("Введите корректный знак операции: +, -, *, /");
            }
            if (romanTotal.getArabic()<1) {
                throw new RuntimeException("Введите корректное выражение");
            }

        }
        else throw new RuntimeException("Введите корректное выражение");
        return total;
    }

    public static boolean operandIsArabic(String input){
        boolean operandIsArabic = false;
        try {
            for (int i = 1; i <= 10; i++) {
                if (Integer.parseInt(input) == i) {
                    operandIsArabic = true;
                    break;
                }
            }
        } catch (NumberFormatException nfe) {
            operandIsArabic = false;
        }
        return operandIsArabic;
    }

    public static boolean operandIsRoman(String input){
        boolean operandIsRoman = false;
        try {
            for (int i = 1; i <= 10; i++) {
                for (RomanNumeral romanNumeral : RomanNumeral.values()) {
                    if ((romanNumeral == RomanNumeral.valueOf(input)) && (romanNumeral.getArabic() == i)) {
                        operandIsRoman = true;
                        break;
                    }
                }
            }
        } catch (IllegalArgumentException iae) {
            operandIsRoman = false;
        }
        return operandIsRoman;
    }
}