import lombok.Data;

@Data
public class Frame {
    private int height;
    private int width;
    private int fields[][];

    public Frame(int height, int width) {
        this.height = height;
        this.width = width;
        this.fields = new int[height][width];
    }

    public void setFieldValue(int height, int width, int value) {
        fields[height][width] = value;
    }
}
