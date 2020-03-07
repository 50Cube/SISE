import lombok.Data;

@Data
public abstract class Algorithm {
    private int visitedStates;
    private int processedStates;
    private int maxRecursionDepth;
    private double time;
    protected Frame frame;
    private Frame solvedFrame;
    private String solution;

    public Algorithm(Frame frame) {
        this.frame = frame;
        generateSolvedFrame();
    }

    private void generateSolvedFrame() {
        this.solvedFrame = new Frame(frame.getHeight(),frame.getWidth());

        int tmp = 1;
        for(int i=0; i<frame.getHeight(); i++) {
            for(int j=0; j<frame.getWidth(); j++) {
                solvedFrame.setFieldValue(i,j,tmp);
                tmp++;
            }
        }
        solvedFrame.setFieldValue(frame.getHeight() - 1, frame.getWidth() - 1, 0);
    }

    public boolean isSolved() {
        boolean tmp = true;

        if(frame.getWidth() == solvedFrame.getWidth() && frame.getHeight() == solvedFrame.getHeight()) {
            for(int i=0; i<frame.getHeight(); i++) {
                for(int j=0; j<frame.getWidth(); j++) {
                    if(frame.getField(i,j) != solvedFrame.getField(i,j))
                        tmp = false;
                }
            }
        }
        else tmp = false;

        return tmp;
    }
}
