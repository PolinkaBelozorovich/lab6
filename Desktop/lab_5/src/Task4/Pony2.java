package Task4;

import java.io.*;

public class Pony2 {

    public static void main(String args[]) throws IOException {

        System.out.print("Проверь удачу! ");
        int Number = (int) (Math.random() * 100), a = 0, b = 100;
        //System.out.print(Number + "\n");

        do {
            System.out.print("Введи число от " + a + " до " + b + " ;)");
            BufferedReader MLP = new BufferedReader(new InputStreamReader(System.in));
            String number = MLP.readLine();
            int num=0;
            do {
                try {
                    num = Integer.parseInt(MLP.readLine());
                } catch (NumberFormatException nfe) { System.err.println("Проверь правильность формата (целое число)!"); }
                if (num < a || num > b) {
                    System.out.println("Вне диапазона! " + "Введи число от " + a + " до " + b + " ;)");
                }
            } while (num < a || num > b);
            if (num < Number) {
                System.out.print("Больше");
                a = num + (int) (Math.random() * (b-num));
                System.out.println("Поищите в диапазоне [" + a + "," + b + "]");
            }
            if (num > Number) {
                System.out.print("Меньше");
                b = a + (int) (Math.random() * (num-a));
                System.out.println("Поищите в диапазоне [" + a + "," + b + "]");
            }
            if (num == Number) {
                System.out.println("WWWWIIIIIINNNNNN!!!!!!");
            }
        } while (true);

    }
}

