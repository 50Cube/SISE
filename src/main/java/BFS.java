public class BFS extends Algorithm {
    private String searchOrder;
    private String[] searchOrderArray;

    public BFS(Frame frame, String searchOrder) {
        super(frame);
        this.searchOrder = searchOrder;
        this.searchOrderArray = searchOrder.split("");
    }

    public Frame solve() {
        this.getStatesToVisit().add(frame);

        while(!this.getStatesToVisit().isEmpty()) {
            Frame newFrame = this.getStatesToVisit().poll();
            this.getVisitedStates().add(newFrame);

            if(this.isSolved(newFrame)) {
                this.setFrame(newFrame);
                return this.frame;
            }

            for (int i=0; i<this.searchOrder.length(); i++) {
                if(newFrame.canMove(this.searchOrderArray[i])) {
                    Frame movedFrame = new Frame(newFrame);
                    movedFrame.move(searchOrderArray[i]);

                    if(!this.getVisitedStates().contains(movedFrame))
                        this.getStatesToVisit().add(movedFrame);
                }
            }
        }
        return this.frame;
    }

    public String generateSolutionString() {
        for(Frame f : this.getVisitedStates())
            this.getSolution().append(f.getMove());

        return this.getSolution().toString();
    }
}
