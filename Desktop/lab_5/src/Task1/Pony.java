package Task1;

import java.util.*;
import java.io.*;

public class Pony {

    public static void main(String[] args){

        LinkedList link = new LinkedList();
        ArrayList array = new ArrayList();
        TreeSet tree = new TreeSet();
        HashSet hash = new HashSet();
        try (FileWriter result = new FileWriter("Result1", false)) {


//add
            long start00 = System.nanoTime();
            for (int p = 0; p <= 666; p++) link.add(p);
            long end00 = System.nanoTime();
            long res00 = end00 - start00;
            result.write("Time of adding to LinkedList: " + res00 + "  nanosec\n");

            long start01 = System.nanoTime();
            for (int p = 0; p <= 666; p++) array.add(p);
            long end01 = System.nanoTime();
            long res01 = end01 - start01;
            result.write("Time of adding to ArrayList: " + res01 + "  nanosec\n");

            long start02 = System.nanoTime();
            for (int p = 0; p <= 666; p++) tree.add(p);
            long end02 = System.nanoTime();
            long res02 = end02 - start02;
            result.write("Time of adding to TreeSet: " + res02 + "  nanosec\n");

            long start03 = System.nanoTime();
            for (int p = 0; p <= 666; p++) hash.add(p);
            long end03 = System.nanoTime();
            long res03 = end03 - start03;
            result.write("Time of adding to HashSet: " + res03 + "  nanosec\n");

//search
            long start10 = System.nanoTime();
            link.contains(257);
            long end10 = System.nanoTime();
            long res10 = end10 - start10;
            result.write("Time of searching to LinkedList: " + res10 + "  nanosec\n");

            long start11 = System.nanoTime();
            array.contains(257);
            long end11 = System.nanoTime();
            long res11 = end11 - start11;
            result.write("Time of searching to ArrayList: " + res11 + "  nanosec\n");

            long start12 = System.nanoTime();
            tree.contains(257);
            long end12 = System.nanoTime();
            long res12 = end12 - start12;
            result.write("Time of searching to TreeSet: " + res12 + "  nanosec\n");

            long start13 = System.nanoTime();
            hash.contains(257);
            long end13 = System.nanoTime();
            long res13 = end13 - start13;
            result.write("Time of searching to HashSet: " + res13 + "  nanosec\n");

//remove
            long start20 = System.nanoTime();
            link.remove(257);
            long end20 = System.nanoTime();
            long res20 = end20 - start20;
            result.write("Time of removing to LinkedList: " + res20 + "  nanosec\n");

            long start21 = System.nanoTime();
            array.remove(257);
            long end21 = System.nanoTime();
            long res21 = end21 - start21;
            result.write("Time of removing to ArrayList: " + res21 + "  nanosec\n");

            long start22 = System.nanoTime();
            tree.remove(257);
            long end22 = System.nanoTime();
            long res22 = end22 - start22;
            result.write("Time of removing to TreeSet: " + res22 + "  nanosec\n");

            long start23 = System.nanoTime();
            hash.remove(257);
            long end23 = System.nanoTime();
            long res23 = end23 - start23;
            result.write("Time of removing to HashSet: " + res23 + "  nanosec\n");

            result.flush();

        } catch (IOException ex) { System.out.println(ex.getMessage()); }
    }
}
