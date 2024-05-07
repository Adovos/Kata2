

import java.util.*;
import java.util.regex.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение из двух чисел от 1 до 10 или от I до X, оператор +, -, :, *: ");
        String string = scanner.nextLine();
        String regexA = "\\b([1-9]|10)([-+*:])([1-9]|10)\\b";
        String regexT = "\\b([1-9]|10)\\b";
        Pattern patternA = Pattern.compile(regexA);
        Pattern patternT = Pattern.compile(regexT);


        String regexR = "\\b(I|II|III|IV|V|VI|VII|VIII|IX|X)([-+*:])(I|II|III|IV|V|VI|VII|VIII|IX|X)\\b";
        Pattern patternR = Pattern.compile(regexR);
        Matcher mA = patternA.matcher(string);
        Matcher mR = patternR.matcher(string);
        Matcher mT = patternT.matcher(string);

        boolean foundA = mA.find();
        boolean foundR = mR.find();
        boolean foundT =mT.find();

        Matcher m = foundA ? mA : foundR ? mR :foundT ? mT : null;


        if (foundA || foundR) {

            Integer first = getOperand(m.group(1));
            String opeR = m.group(2);
            Integer second = getOperand(m.group(3));
            Integer result = null;

            switch (opeR) {
                case "+" -> result = first + second;
                case "-" -> result = first - second;
                case "*" -> result = first * second;
                case ":" -> result = first / second;



            }


            if (result != null) {


                if (foundA) {
                    System.out.println("Результат: " + result);

                }
                else{
                    throw new Exception("Введено более двух операндов");
                }

                if(foundR){
                    System.out.println("Результат рим: " + result);

                }
                else{
                    throw new Exception("Введено более двух операндов");
                }


            }

        } else {
            throw new Exception("Не правильно выбран знак действия");

        }




    }

    static Integer getOperand(String opStr) {
        int result;

        try {
            result = Integer.parseInt(opStr);
        } catch (NumberFormatException e) {
            result = switch (opStr) {
                case "I" -> 1;
                case "II" -> 2;
                case "III" -> 3;
                case "IV" -> 4;
                case "V" -> 5;
                case "VI" -> 6;
                case "VII" -> 7;
                case "VIII" -> 8;
                case "IX" -> 9;
                case "X" -> 10;
                default -> throw e;
            };
        }
        return result;
    }


    static String convert(int number) {
        if (number < 0 || number > 100) {
            return "Это число не может быть преобразовано";
        }
        String romanOnes = romanDigit(number % 10, "I", "V", "X");
        number /= 10;

        String romanTens = romanDigit(number % 10, "X", "L", "C");
        number /= 10;

        String romanHundreds = romanDigit(number % 10, "C", "", "");

        return romanHundreds + romanTens + romanOnes;
    }

    static String romanDigit(int n, String one, String five, String ten) {
        if (n >= 1) {
            switch (n) {
                case 1:
                    return one;
                case 2:
                    return one+one;
                case 3:
                    return one+one+one;
                case 4:
                    return one+five;
                case 5:
                    return five;
                case 6:
                    return five+one;
                case 7:
                    return five+one+one;
                case 8:
                    return five+one+one+one;
                case 9:
                    return one+ten;
                default:
                    break;
            }
        }
        return "0";
    }
}