package ar.edu.utn.frc.tup.lciii.model.console;


import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Console {
    private final String VALID_YES = "^[Ss]$";
    private final String VALID_NO = "^[Nn]$";
    Scanner scanner = new Scanner(System.in);

    public Integer selectOptions(LinkedHashMap<Integer, String> listOptions) {
        while (true) {
            LetterByLetterPrinter.println("Ingrese una de las siguientes opciones");
            listOptions.forEach((key, option) -> {
                LetterByLetterPrinter.println(key + ": " + option);
            });
            Boolean isInteger = scanner.hasNextInt();
            if (isInteger) {
                Integer input = scanner.nextInt();
                scanner.nextLine();
                String value = listOptions.get(input);
                if (value != null) {
                    LetterByLetterPrinter.println("Seleccionó: " + value);
                    return input;
                }
            }
            scanner.nextLine();

        }
    }

    public String inputString() {

        return scanner.next();
    }

    public boolean inputYesNo() {
        boolean choice;

        do {
            LetterByLetterPrinter.println("Ingrese S (si) o N (no)");
            String input = scanner.nextLine();
            if (Pattern.matches(VALID_YES, input)) {
                choice = true;
                break;
            }
            if (Pattern.matches(VALID_NO, input)) {
                choice = false;
                break;
            }
        } while (true);
        return choice;
    }



public Integer inputInt() {
    while (true) {
        if (scanner.hasNextInt()) {
            int valor = scanner.nextInt();
            scanner.nextLine();
            return valor;
        } else {
            scanner.nextLine();
            LetterByLetterPrinter.println("Debe ingresar un numero.");
        }
    }
}

}
