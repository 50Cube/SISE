import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        if(args.length != 5)
            throw new IllegalArgumentException("Podano nieprawidlowa liczbe argumentow");

        FileOperations fileOperations = new FileOperations();
        System.out.println(fileOperations.readFrame(args[2]));

        switch (args[0]) {
            case "bfs":
                System.out.println("Wybrano strategie przeszukiwania wrzerz");
                break;
            case "dfs":
                System.out.println("Wybrano strategie przeszukiwania w glab");
                break;
            case "astr":
                System.out.println("Wybrano strategie najpierw najlepszy");
                break;
            default:
                throw new IllegalArgumentException("Podano zly argument wyboru stretegii");
        }
    }
}
