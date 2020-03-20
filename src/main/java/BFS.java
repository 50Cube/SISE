import java.util.LinkedList;
import java.util.Queue;

public class BFS extends Algorithm {
    private String searchOrder;
    private String[] searchOrderArray;
    private Queue<Frame> statesToVisit;

    public BFS(Frame frame, String searchOrder) {
        super(frame);
        this.searchOrder = searchOrder;
        this.searchOrderArray = searchOrder.split("");
        statesToVisit = new LinkedList<>();
    }

    @Override
    public Frame solve() {
        this.statesToVisit.add(frame);

        while(!this.statesToVisit.isEmpty()) {
            Frame newFrame = this.statesToVisit.poll();
            this.getVisitedStates().add(newFrame);

            if(this.isSolved(newFrame)) {
                this.setFrame(newFrame);
                this.generateDetails();
                return this.frame;
            }

            for (int i=0; i<this.searchOrder.length(); i++) {
                if(newFrame.canMove(this.searchOrderArray[i])) {
                    Frame movedFrame = new Frame(newFrame);
                    movedFrame.move(searchOrderArray[i]);

                    if(!this.getVisitedStates().contains(movedFrame))
                        this.statesToVisit.add(movedFrame);
                }
            }
        }

        return this.frame;
    }
}
