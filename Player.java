import java.io.FileWriter;
import java.io.IOException;

public abstract class Player {

    public abstract int chooseMove(GameState state);
    
    public void printMove(int[] piecePositions) {
        String filePath = GameMain.outputFilePath;
        try (FileWriter writer = new FileWriter(filePath, true)) {
            for (int pos : piecePositions) {
                writer.write(pos + " ");
            }
            writer.write("\n");
            
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
