import lombok.Data;

@Data
public class Frame {
    private int height;
    private int width;
    private int[][] fields;
    private int zeroX, zeroY;

    public Frame(int height, int width) {
        this.height = height;
        this.width = width;
        this.fields = new int[height][width];
    }

    public Frame(Frame newFrame) {
        this.height = newFrame.getHeight();
        this.width = newFrame.getWidth();
        fields = new int[height][width];

        for(int i=0; i<height; i++) {
            for(int j=0; j<width; j++) {
                fields[j][i] = newFrame.fields[j][i];
            }
        }
        this.findZero();
    }

    public void setFieldValue(int height, int width, int value) {
        fields[width][height] = value;
    }

    public int getField(int height, int width) {
        return fields[width][height];
    }

    public void findZero() {
        for(int i=0; i<height; i++) {
            for(int j=0; j<width; j++) {
                if(fields[j][i] == 0) {
                    zeroX = i;
                    zeroY = j;
                }
            }
        }
    }

    public boolean canMove(String direction) {
        switch(direction) {
            case "U":
                if(zeroY != 0) return true;
                break;
            case "D":
                if(zeroY != height-1) return true;
                break;
            case "L":
                if(zeroX != 0) return true;
                break;
            case "R":
                if(zeroX != width-1) return true;
                break;
            default:
                return false;
        }
        return false;
    }

    public void swapFields(int x1, int y1, int x2, int y2) {
        int tmp = fields[x1][y1];
        fields[x1][y1] = fields[x2][y2];
        fields[x2][y2] = tmp;
    }

    public String move(String direction) {
        switch(direction) {
            case "U":
                swapFields(zeroX, zeroY, zeroX, zeroY - 1);
                return "U";
            case "D":
                swapFields(zeroX, zeroY, zeroX, zeroY + 1);
                return "D";
            case "L":
                swapFields(zeroX, zeroY, zeroX - 1, zeroY);
                return "L";
            case "R":
                swapFields(zeroX, zeroY, zeroX + 1, zeroY);
                return "R";
            default:
                return "";
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nHeight: " + this.height + '\n');
        sb.append("Width: " + this.width + '\n');
        sb.append("ZeroX: " + this.zeroX + '\n');
        sb.append("ZeroY: " + this.zeroY + '\n');
        sb.append(System.lineSeparator());

        for(int i = 0;i < height;i++){
            for(int j = 0;j < width;j++){
                sb.append(String.format("%3s",fields[i][j]));
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
