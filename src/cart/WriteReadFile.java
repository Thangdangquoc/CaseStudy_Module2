package cart;

import java.io.*;
import java.util.ArrayList;

public class WriteReadFile<E> {
    public void writeFile(ArrayList<E> orders, String url) {
        try {
            File file = new File(url);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file, false));
            objectOutputStream.writeObject(orders);
            objectOutputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<E> ReaderObject(String url) {
        ArrayList<E> orders = new ArrayList<>();
        try {
            File file = new File(url);
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            orders = (ArrayList<E>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return orders;
    }
}
