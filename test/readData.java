
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/*

*/
public class readData {

    public static void main(String[] args) throws FileNotFoundException {
        
        File file = new File("C:\\Users\\dinhd\\OneDrive\\Java Web\\MyProject\\test\\abc.txt");
        Scanner sc = new Scanner(file);
        BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(
					"C:\\Users\\dinhd\\OneDrive\\Java Web\\MyProject\\web\\txt\\n_1.txt"));
			String line = reader.readLine();
			while (line != null) {
				System.out.println(line);
				// read next line
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}


//C:\\Users\\dinhd\\OneDrive\\Java Web\\MyProject\\test\\abc.txt