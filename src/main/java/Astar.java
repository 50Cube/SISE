import java.util.HashMap;
import java.util.Map;

public class Astar extends Algorithm {

    private String[] direction = {"U", "R", "D", "L"};
    private Map<Frame, Integer> statesToVisit;
    private String heuristic;

    public Astar(Frame frame, String heuristic) {
        super(frame);
        this.statesToVisit = new HashMap<>();
        this.heuristic = heuristic;
    }

    @Override
    public Frame solve() {
        this.statesToVisit.put(frame, Integer.MAX_VALUE - 1);
        while(!statesToVisit.isEmpty()) {
            Frame newFrame = findFrameWithTheLowestHeuristic();
            this.getVisitedStates().add(newFrame);
            this.incraseDepth(newFrame);

            if(this.isSolved(newFrame)) {
                this.setFrame(newFrame);
                this.generateDetails();
                return this.frame;
            }

            for (String dir : direction) {
                if(newFrame.canMove(dir)) {
                    Frame movedFrame = new Frame(newFrame);
                    movedFrame.move(dir);

                    if(!this.getVisitedStates().contains(movedFrame)) {
                        int heuristicDistance = (heuristic.equalsIgnoreCase("hamm")) ? Hamming(movedFrame) : Manhattan(movedFrame);
                        heuristicDistance += movedFrame.getSolution().length();
                        this.statesToVisit.put(movedFrame, heuristicDistance);
                    }
                }
            }
        }
        return this.frame;
    }

    private Frame findFrameWithTheLowestHeuristic() {
        Frame tmpFrame = new Frame(this.getFrame().getHeight(), this.getFrame().getWidth());
        int tmp = Integer.MAX_VALUE;

        for(Frame frame : statesToVisit.keySet()) {
            if(statesToVisit.get(frame) < tmp) {
                tmp = statesToVisit.get(frame);
                tmpFrame = new Frame(frame);
            }
        }

        for(Frame frame : statesToVisit.keySet()) {
            if(tmpFrame.equals(frame)) {
                statesToVisit.remove(frame);
                break;
            }
        }

        return tmpFrame;
    }

    private int Hamming(Frame frame) {
        int count = 0;

        for(int i=0; i<frame.getHeight(); i++) {
            for(int j=0; j<frame.getWidth(); j++) {
                if(frame.getField(i,j) != this.getSolvedFrame().getField(i,j))
                    count++;
            }
        }

        return count;
    }

    private int Manhattan(Frame frame) {
        int count = 0;

        for(int i=0; i<frame.getHeight(); i++) {
            for(int j=0; j<frame.getWidth(); j++) {
                int value = frame.getField(i,j);
                if(value != 0) {
                    int solvedX = this.getSolvedFrame().getCoordinatesByValue(value)[0];
                    int solvedY = this.getSolvedFrame().getCoordinatesByValue(value)[1];
                    count += Math.abs(i - solvedX) + Math.abs(j - solvedY);
                }
            }
        }

        return count;
    }
}
