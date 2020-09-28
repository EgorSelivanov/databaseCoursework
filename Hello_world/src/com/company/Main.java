package com.company;
import java.lang.String;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Input:");
        Scanner num = new Scanner(System.in);
        int first, second, result;
        System.out.println("Enter first num: ");
        first = num.nextInt();
        System.out.println("Enter second num: ");
        second = num.nextInt();
        for (int i = 0; i < 5; i++)
            first++;
        if (first >= 10 || second >= 10)
            System.out.print(first);
        else if (first == 4)
            System.out.print(second);
    }
}
