import lombok.Data;

@Data
public class Frame {
    private int height;
    private int width;
    private int fields[][];
    private int zeroX, zeroY;

    public Frame(int height, int width) {
        this.height = height;
        this.width = width;
        this.fields = new int[height][width];
        this.findZero();
    }

    public void setFieldValue(int height, int width, int value) {
        fields[height][width] = value;
    }

    public int getField(int height, int width) {
        return fields[height][width];
    }

    private void findZero() {
        for(int i=0; i<height; i++) {
            for(int j=0; j<width; j++) {
                if(fields[i][j] == 0) {
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
}
