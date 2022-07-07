package account;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadWriteFile {

    public void writeFile(ArrayList<User> users) {
        try {
            File file = new File("D:\\JAVA\\CaseStudy_Module2\\src\\data\\AccountStaff");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file, false));
            objectOutputStream.writeObject(users);
            objectOutputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<User> ReaderObject() {
        ArrayList<User> user1s = new ArrayList<>();
        try {
            File file = new File("D:\\JAVA\\CaseStudy_Module2\\src\\data\\AccountStaff");
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            user1s = (ArrayList<User>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println();
        }
        return user1s;
    }
}
