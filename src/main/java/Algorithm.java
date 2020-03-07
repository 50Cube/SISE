import lombok.Data;

@Data
public abstract class Algorithm {
    private int solutionLength;
    private int visitedStates;
    private int processedStates;
    private int maxRecursionDepth;
    private int time;
    private Frame frame;

    public Algorithm(Frame frame) {
        this.frame = frame;
    }
}
