import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileOperations {

    public Frame readFrame(String file) throws IOException {
        String data = new String(Files.readAllBytes(Paths.get(file)));
        String[] split = data.split("\\s+");

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

    public void writeSolution(String file, Algorithm algorithm) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        if(algorithm.isSolved()) {
            stringBuilder.append(algorithm.getSolution().length());
            stringBuilder.append(System.lineSeparator());
            stringBuilder.append(algorithm.getSolution());
        }
        else stringBuilder.append(-1);

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(stringBuilder.toString());
        writer.close();
    }

    public void writeDetails(String file, Algorithm algorithm) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        if(algorithm.isSolved())
            stringBuilder.append(algorithm.getSolution().length());
        else stringBuilder.append(-1);

        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(algorithm.getVisitedStates());

        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(algorithm.getProcessedStates());

        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(algorithm.getMaxRecursionDepth());

        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(algorithm.getTime());

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(stringBuilder.toString());
        writer.close();
    }
}
