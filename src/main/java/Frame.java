import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class Frame {
    private int height;
    private int width;
    private int[][] fields;
    private int zeroX, zeroY;
    private String solution = "";
    private int depth = 0;
    private List<Frame> nextFrames;

    public Frame(int height, int width) {
        this.height = height;
        this.width = width;
        this.fields = new int[height][width];
    }

    public Frame(Frame newFrame) {
        this.height = newFrame.getHeight();
        this.width = newFrame.getWidth();
        fields = new int[height][width];
        this.setSolution(newFrame.getSolution());

        for(int i=0; i<height; i++) {
            if (width >= 0) System.arraycopy(newFrame.fields[i], 0, fields[i], 0, width);
        }
        this.findZero();
    }

    public void setFieldValue(int height, int width, int value) {
        fields[height][width] = value;
    }

    public int getField(int height, int width) {
        return fields[height][width];
    }

    public void findZero() {
        for(int i=0; i<height; i++) {
            for(int j=0; j<width; j++) {
                if(fields[i][j] == 0) {
                    zeroX = j;
                    zeroY = i;
                }
            }
        }
    }

    public boolean canMove(String direction) {
        switch(direction) {
            case "U":
                if(zeroY != 0) { return true; } else return false;
            case "D":
                if(zeroY != height-1) { return true; } else return false;
            case "L":
                if(zeroX != 0) { return true; } else return false;
            case "R":
                if(zeroX != width-1) { return true; } else return false;
            default:
                return false;
        }
    }

    public void generateNextFrames(String order, String[] orderArray, Frame frame) {
        for (int i = 0; i < order.length(); i++) {
            if (frame.canMove(orderArray[i])) {
                Frame movedFrame = frame;
                movedFrame.move(orderArray[i]);
                frame.getNextFrames().add(movedFrame);
            }
        }
    }



//        {
//            if(IsMovePossible(direction) && IsNotGoingBack(direction))
//            {
//                this.nextStates.Add(this.Move(direction));
//            }
//        }


//    private void swapFields(int y1, int x1, int y2, int x2) {
//        int previous = getTile(y1, x1);
//        setTile(y1, x1, getTile(y2, x2));
//        setTile(y2, x2, previous);
//        zeroY = y2;
//        zeroX = x2;
//    }
    private void setTile(int y, int x, int tile) {
        fields[y][x] = tile;
    }
    private int getTile(int y, int x) {
        return fields[y][x];
    }

    public void swapFields(int x1, int y1, int x2, int y2) {
        int tmp = fields[y1][x1];
        fields[y1][x1] = fields[y2][x2];
        fields[y2][x2] = tmp;
        findZero();
    }

    public void move(String direction) {
        this.solution += direction;
        switch(direction) {
            case "U":
                swapFields(zeroX, zeroY, zeroX, zeroY - 1);
                break;
            case "D":
                swapFields(zeroX, zeroY, zeroX, zeroY + 1);
                break;
            case "L":
                swapFields(zeroX, zeroY, zeroX - 1, zeroY);
                break;
            case "R":
                swapFields(zeroX, zeroY, zeroX + 1, zeroY);
                break;
            default:
                break;
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

    @Override
    public boolean equals(Object obj) {
        if(!obj.getClass().equals(this.getClass())) return false;

        Frame frame = (Frame) obj;
        return Arrays.deepEquals(this.getFields(), frame.getFields());
    }
}
