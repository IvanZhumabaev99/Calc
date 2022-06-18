package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.println("введите пример:");
            Scanner str = new Scanner(System.in);
            String string = str.nextLine();
            System.out.println("Результат:");
            System.out.println(calc(string));
        }
    }
    public static String calc(String input) throws IOException {
        boolean x, y, arabic, rim;
        x = input.matches("\\d\\+\\d|10\\+10|\\d\\+10|10\\+\\d|\\d\\-\\d|10\\-10|\\d\\-10|10\\-\\d|\\d\\*\\d|10\\*10|\\d\\*10|10\\*\\d|\\d\\/\\d|10\\/10|\\d\\/10|10\\/\\d");
        y = input.matches("[IVX]+[\\+|\\-|\\*|\\/][IVX]+");

        if (x != true && y != true) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("Не корректный ввод.");
                System.exit(0);
            }
        }

        List<String> list = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(input, "\\+\\-\\*\\/", true);

        while (st.hasMoreTokens()) {
            list.add(st.nextToken());
        }
        String result;


        if (x == true) {
            if ("/".equals(list.get(1)) && 0 == Integer.parseInt(list.get(2))) {
                throw new IOException("Делить на ноль нельзя!");
            }
            result = calculator(Integer.parseInt(list.get(0)), Integer.parseInt(list.get(2)), list.get(1));
        } else {
            String promResult = calculator(RomanNumerals.romanToArabic(list.get(0)), RomanNumerals.romanToArabic(list.get(2)), list.get(1));
            if (Integer.parseInt(promResult) < 1) {
                throw new IOException("Число меньше единицы.");
            }
            result = RomanNumerals.arabicToRoman(
                    Integer.parseInt(promResult)
            );
        }
        return result;
    }
    public static String calculator(int a, int b, String input) {

        switch (input) {
            case "+":
                return String.valueOf(a + b);
            case "-":
                return String.valueOf(a - b);
            case "*":
                return String.valueOf(a * b);
            case "/":
                return String.valueOf(a / b);
            default:
                return null;
        }
    }
}
