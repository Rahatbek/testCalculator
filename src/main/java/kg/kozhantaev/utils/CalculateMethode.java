package kg.kozhantaev.utils;

import kg.kozhantaev.models.Number;
import kg.kozhantaev.services.ActionService;
import kg.kozhantaev.services.NumberService;

import java.util.Scanner;

public class CalculateMethode {

    public static void calculate() {
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("Input: ");
            String line = scanner.nextLine();

            String delimiters = "\\s+|,\\s*|\\\\s*";
//            String delimiters = " ";

            if (line.equals("exit")) {
                break;
            }

            try {
                String symbols[] = line.trim().split(delimiters);

                if (symbols.length == 1) throw new Exception("throws Exception //т.к. строка не является математической операцией");

                if (symbols.length != 3) throw new Exception("throws Exception //т.к. формат математической " +
                        "операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");

                Number firstNumber = NumberService.parseAndValidate(symbols[0]);
                Number secondNumber = NumberService.parseAndValidate(symbols[2], firstNumber.getType());
                String result = ActionService.calculate(firstNumber, secondNumber, symbols[1]);
                System.out.println("Output: \n" + result);

            } catch (Exception e) {
                if (e.getMessage() == null) {
                    System.out.println("Output:\nthrows Exception //т.к. в римской системе нет отрицательных чисел");
                    break;
                }
                System.out.println("Output:\n" + e.getMessage());
                break;
            }
        }

        scanner.close();
    }
}
