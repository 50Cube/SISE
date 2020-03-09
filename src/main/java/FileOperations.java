import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;

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
        frame.findZero();

        return frame;
    }

    public void writeSolution(String file, Algorithm algorithm) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        if(algorithm.isSolved(algorithm.getFrame())) {
            stringBuilder.append(algorithm.getFrame().getSolution().length());
            stringBuilder.append(System.lineSeparator());
            stringBuilder.append(algorithm.getFrame().getSolution());
        }
        else stringBuilder.append(-1);

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(stringBuilder.toString());
        writer.close();
    }

    public void writeDetails(String file, Algorithm algorithm) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        if(algorithm.isSolved(algorithm.getFrame()))
            stringBuilder.append(algorithm.getFrame().getSolution().length());
        else stringBuilder.append(-1);

        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(algorithm.getVisitedStatesAmount());

        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(algorithm.getProcessedStates());

        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(algorithm.getMaxRecursionDepth());

        stringBuilder.append(System.lineSeparator());
        DecimalFormat decimalFormat = new DecimalFormat("0.000");
        stringBuilder.append(decimalFormat.format(algorithm.getTime()));

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(stringBuilder.toString());
        writer.close();
    }
}
