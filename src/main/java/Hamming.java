public class Hamming extends Algorithm {

    private String[] direction = {"U", "R", "D", "L"};

    public Hamming(Frame frame) {
        super(frame);
    }

//    @Override
//    public Frame solve() {
//        this.getVisitedStates().add(frame);
//        Frame newFrame;
//
//        while(correctFieldsAmount(frame) != frame.getWidth() * frame.getHeight()) {
//            int correctFields = correctFieldsAmount(frame);
//            newFrame = this.findFrameWithMostCorrectFields();
//            this.getVisitedStates().add(newFrame);
//
//            for(String dir : direction) {
//                Frame movedFrame = new Frame(newFrame);
//                if(movedFrame.canMove(dir)) {
//                    movedFrame.move(dir);
//                    if(correctFieldsAmount(movedFrame) > correctFields && !this.getVisitedStates().contains(movedFrame)) {
//                        this.getVisitedStates().add(movedFrame);
//                        this.setFrame(movedFrame);
//                        break;
//                    }
//                }
//            }
//        }
//        return frame;
//    }

    @Override
    public Frame solve() {
        this.getStatesToVisit().add(frame);
        while(correctFieldsAmount(frame) != frame.getWidth() * frame.getHeight()) {
            Frame newFrame = this.findFrameWithMostCorrectFields(this.getStatesToVisit());
            this.getStatesToVisit().clear();
            this.getVisitedStates().add(newFrame);

            if(this.isSolved(newFrame)) {
                this.setFrame(newFrame);
                this.generateDetails();
                return this.frame;
            }

            for (String dir : direction) {
                if(newFrame.canMove(dir)) {
                    Frame movedFrame = new Frame(newFrame);
                    movedFrame.move(dir);

                    if(!this.getVisitedStates().contains(movedFrame))
                        this.getStatesToVisit().add(movedFrame);
                }
            }
        }
        return this.frame;
    }
}
