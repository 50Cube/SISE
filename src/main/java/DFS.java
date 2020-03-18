import java.util.Stack;

public class DFS extends Algorithm {
    private final int DEPTH_LIMIT = 22;
    private String searchOrder;
    private String[] searchOrderArray;
    private Stack<Frame> toVisit;

    public DFS(Frame frame, String searchOrder) {
        super(frame);
        this.searchOrder = searchOrder;
        this.searchOrderArray = searchOrder.split("");
        this.toVisit = new Stack<>();
    }

    @Override
    public Frame solve() {
        toVisit.add(frame);

        while(!toVisit.isEmpty()) {
            Frame newFrame = new Frame(toVisit.pop());
            if(this.getVisitedStates().contains(newFrame)) {
                continue;
            }
            this.getVisitedStates().add(newFrame);

            if(newFrame.getSolution().length() >= DEPTH_LIMIT) {
                continue;
            }

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
                        toVisit.add(movedFrame);
                }
            }
        }
        return this.frame;
    }
}
