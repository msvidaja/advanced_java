import java.io.FileInputStream;
import java.io.IOException;
public class ReadPrint {
    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("C:\\Users\\YASH PATEL\\OneDrive\\Desktop\\JAVA SEM-6\\Sample.txt")) {;
        int i;
        while ((i = fis.read()) != -1) {
            System.out.print((char) i);
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
