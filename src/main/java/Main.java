import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        if(args.length != 5)
            throw new IllegalArgumentException("Podano nieprawidlowa liczbe argumentow");

        FileOperations fileOperations = new FileOperations();
        Frame frame = fileOperations.readFrame(args[2]);
        System.out.println(frame);

        switch (args[0]) {
            case "bfs":
                System.out.println("Wybrano strategie przeszukiwania wrzerz");
                BFS bfs = new BFS(frame,args[1]);
                fileOperations.writeSolution(args[3],bfs);
                fileOperations.writeDetails(args[4],bfs);
                break;
            case "dfs":
                System.out.println("Wybrano strategie przeszukiwania w glab");
                DFS dfs = new DFS(frame,args[1]);
                fileOperations.writeSolution(args[3],dfs);
                fileOperations.writeDetails(args[4],dfs);
                break;
            case "astr":
                System.out.println("Wybrano strategie najpierw najlepszy");
                break;
            default:
                throw new IllegalArgumentException("Podano zly argument wyboru stretegii");
        }
    }
}
