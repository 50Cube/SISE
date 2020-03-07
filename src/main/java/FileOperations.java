import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileOperations {

    public Frame readFrame(String file) throws IOException {
        String data = new String(Files.readAllBytes(Paths.get(file)));
        String[] split = data.split("\\s+");
        //System.out.println(data);

        int height = Integer.parseInt(split[0]);
        int width = Integer.parseInt(split[1]);
        int tmp = 2;
        Frame frame = new Frame(height, width);

        for(int i=0; i<height; i++) {
            for(int j=0; j<width; j++) {
                frame.setFieldValue(i,j,Integer.parseInt(split[tmp]));
                tmp++;
            }
        }

        return frame;
    }
}
