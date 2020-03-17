import java.util.*;

public class DFS extends Algorithm {
    private final int DEPTH_LIMIT = 22;
    private String searchOrder;
    private int maxDepth = Integer.MIN_VALUE;
    private String[] searchOrderArray;

    public DFS(Frame frame, String searchOrder) {
        super(frame);
        this.searchOrder = searchOrder;
        this.searchOrderArray = searchOrder.split("");

    }

    @Override
    public Frame solve() {

        boolean solutionFound = false;
        this.getStatesToVisit().add(frame);
        System.out.println(frame);
        Stack<Frame> toVisit = new Stack<Frame>();
        toVisit.push(frame);
        Map<Frame,Frame> visitedStates = new HashMap<Frame,Frame>();
        setVisitedStatesAmount(1);

        while(this.visitedStatesAmount != 0 && !solutionFound) {

            Frame newFrame = toVisit.pop();

            if (newFrame.getDepth() > this.maxDepth) {
                newFrame.setDepth(maxDepth);
            }
            if(isSolved(newFrame)){
                return newFrame;
            }
            else
            {
                if(newFrame.getDepth() > DEPTH_LIMIT){
                    continue;
                }

                if(visitedStates.containsKey(newFrame))
                {
                    if(newFrame.getDepth()>=visitedStates.get(newFrame).getDepth()){
                        continue;
                    }
                    else{
                        visitedStates.remove(newFrame);
                    }
                }
                visitedStates.put(frame,frame);
                newFrame.generateNextFrames(searchOrder,searchOrderArray,newFrame);
                List<Frame> nextFrames =  newFrame.getNextFrames();
             //TODO: reverse List   nextFrames.reverse();





            }

        }
    return frame;
    }
}
