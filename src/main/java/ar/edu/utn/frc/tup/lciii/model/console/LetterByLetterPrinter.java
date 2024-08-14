package ar.edu.utn.frc.tup.lciii.model.console;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class LetterByLetterPrinter {
    public static void println(String text) {
        try {
            for (int i = 0; i < text.length(); i++) {
                System.out.print(text.charAt(i));
                Thread.sleep(8); // Ajusta el tiempo de espera según lo deseado
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
