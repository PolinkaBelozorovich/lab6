package Task3;

import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class MainPony {


    public static void main(String[] args) throws IOException {

        ArrayList<BigPony> list = new ArrayList();

        try (Scanner in = new Scanner(Paths.get("Hour"))) {
            while (in.hasNext()) {
                list.add(new LittlePony1(in.nextInt(), in.next(), in.nextInt()));
            }
        }

        try (Scanner in = new Scanner(Paths.get("Month"))) {
            while (in.hasNext()) {
                list.add(new LittlePony2(in.nextInt(), in.next(), in.nextInt()));
            }
        }

        Collections.sort(list, Collections.reverseOrder(BigPony.BySalary));
        System.out.println(list.toString());
        System.out.println("List of workers: \n");
        for (int i = 0; i < 5; i++) {
            System.out.println(list.get(i).getName());
        }

        System.out.println("List of id: \n");
        for (int i = list.size() - 1; i > list.size() - 4; i--) {
            System.out.println(list.get(i).getId());
        }

        try (FileWriter writer = new FileWriter("Result3", false)) {
            writer.write(list.toString());
            writer.flush();
        } catch (IOException ex) { System.out.println(ex.getMessage()); }

    }
}
