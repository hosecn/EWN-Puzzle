import java.io.FileWriter;
import java.io.IOException;

public abstract class Player {

    public abstract int chooseMove(GameState state);
    
    public void printMove(GameState state) {
        String filePath = "moves.txt";
        try (FileWriter writer = new FileWriter(filePath)) {
            for (int pos : state.getPiecePositions()) {
                writer.write(pos + " ");
            }
            writer.write("\n");
            
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
