public class BFS extends Algorithm {
    private String searchOrder;
    private String[] searchOrderArray;

    public BFS(Frame frame, String searchOrder) {
        super(frame);
        this.searchOrder = searchOrder;
        this.searchOrderArray = searchOrder.split("");
    }

    public Frame solve() {
        this.getStates().add(frame);

        while(!this.getStates().isEmpty()) {
            Frame newFrame = this.getStates().poll();

            if(this.isSolved(newFrame)) {
                this.setFrame(newFrame);
            }

            for (int i=0; i<this.searchOrder.length(); i++) {
                if(newFrame.canMove(this.searchOrderArray[i])) {
                    Frame movedFrame = new Frame(newFrame);
                    this.getSolution().append(movedFrame.move(searchOrderArray[i]));
                    this.getStates().add(movedFrame);
                }
            }
        }
        return this.frame;
    }
}
