package Task6;

import java.util.Scanner;

public class Pony4 {

    public static void main(String[] args) {

        System.out.println("Пустите кота на клавиатуру и нажмите Enter");

        Scanner MLP = new Scanner(System.in);
        String string = MLP.nextLine();
        String res = "";

        for (int i =  string.length() -1 ;i >= 0; i--) {
            res += string.charAt(i);
        }

        System.out.println("Добро пожаловать в Зазеркалье");

        System.out.println(res);

    }
}
