import lombok.Data;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Data
public abstract class Algorithm {
    protected int visitedStatesAmount;
    protected int numberOfStates;
    private int processedStates;
    private int maxRecursionDepth;
    private double time;
    protected Frame frame;
    private Frame solvedFrame;

    private Queue<Frame> visitedStates;

    public Algorithm(Frame frame) {
        this.frame = frame;
        generateSolvedFrame();
        visitedStates = new LinkedList<>();
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

    public boolean isSolved(Frame frame) {
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

    public abstract Frame solve();

    public Frame run() {
        long time = System.nanoTime();
        Frame frame = this.solve();
        this.setTime((System.nanoTime() - time) / 1000000.0);
        return frame;
    }

    protected void generateDetails() {
        this.setVisitedStatesAmount(this.getVisitedStates().size());
        this.setProcessedStates(this.getVisitedStatesAmount());
        this.setMaxRecursionDepth(this.frame.getSolution().length());
    }
}
