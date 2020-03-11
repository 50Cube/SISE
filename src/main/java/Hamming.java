public class Hamming extends Algorithm {

    private String[] direction = {"U", "R", "D", "L"};

    public Hamming(Frame frame) {
        super(frame);
    }

    @Override
    public Frame solve() {
        Frame newFrame;

        while(correctFieldsAmount(frame) != frame.getWidth() * frame.getHeight()) {
            int correctFields = correctFieldsAmount(frame);
            newFrame = new Frame(frame);

            for(String dir : direction) {
                Frame movedFrame = new Frame(newFrame);
                if(movedFrame.canMove(dir)) {
                    movedFrame.move(dir);
                    if(correctFieldsAmount(movedFrame) > correctFields) {
                        this.setFrame(movedFrame);
                        correctFields = correctFieldsAmount(frame);
                    }
                }
            }
        }
        //this.setFrame(newFrame);
        return frame;
    }
}
