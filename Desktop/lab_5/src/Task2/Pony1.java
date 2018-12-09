package Task2;

import java.util.*;
import java.io.*;

public class Pony1 {

    public static void main(String[] args) {

        Collection Alfa = new ArrayList<>();
        Random random = new Random();

        for(int p = 0; p < 150; p++) Alfa.add(random.nextInt(200) + 1);

        ArrayList arr = new ArrayList<>();
        arr.addAll(Alfa);
        Collections.sort(arr, Comparator.reverseOrder());

        Collection Beta = new ArrayList<>();
        Beta.addAll(arr.subList(0,15));

        System.out.println(Beta.toString());
        try(FileWriter writer = new FileWriter("Result2", false);){
            writer.write(Beta.toString());
            writer.close();
        }
        catch (IOException ex) { System.out.println(ex.getMessage()); }
    }
}
