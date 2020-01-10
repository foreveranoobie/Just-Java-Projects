import java.io.File;
import java.nio.file.Files;

public class ReadingFile {
    public static void main(String[] arg){
        File file = new File("C:\\");
        File[] files = file.listFiles((File f) -> f.isDirectory());
        for(File f : files){
            System.out.println(f.getName());
        }
    }
}
