package Task5;

import java.io.*;
import java.util.*;

public class Pony3 {

    public static final Comparator<String> COMPARE = new Comparator<String>() {

        @Override
        public int compare(String a1, String a2) {
            return a1.compareToIgnoreCase(a2);

        }
    };

    public static void main(String[] args) throws FileNotFoundException {

        Scanner MLP = new Scanner(new FileReader("Input5"));
        LinkedList<String> link = new LinkedList();

        while (MLP.hasNext()) {
            String word = MLP.next();
            link.add(word);
        }

        Collections.sort(link, COMPARE);
        System.out.print(link);

    }
}