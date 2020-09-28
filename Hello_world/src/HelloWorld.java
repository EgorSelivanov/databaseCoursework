import com.sun.source.util.SourcePositions;

import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;

/**
 * Java. first program
 *
 * @author Egor
 * @version dated 27.07.2019
 */


public class HelloWorld {
    public static void main(String[] args) {
        Random random = new Random();
        String s;
        Scanner x = new Scanner(System.in);
        int[] y;
        y = new int[5];
        for (int i = 0; i < 5; i++) {
            y[i] = x.nextInt();
        }
        System.out.println("Hello");
        System.out.println();
        for (int i = 0; i < 5; i++) {
            System.out.println(y[i]);
        }
    }
}
