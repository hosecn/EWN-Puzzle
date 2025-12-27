import java.io.*;
import java.util.Arrays;

public class GameLoader {
    private int targetPiece;
    private int[] piecePositions;
    private int[] diceSequence;

    public GameLoader(String filePath) {
        
        try (FileReader fr = new FileReader(filePath); BufferedReader br = new BufferedReader(fr)) {
            String line;
            line = br.readLine();
            targetPiece = Integer.parseInt(line);
            
            line = br.readLine();
            piecePositions = Arrays.stream(line.split(" "))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .toArray();

            line = br.readLine();

            diceSequence = Arrays.stream(line.split(" "))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .toArray();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }



    public void printGameDetails(String playerName) {
        String filePath = "moves.txt";
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.println(playerName);

            for (int dice : diceSequence) {
                writer.print(dice + " ");
            }      
            writer.println();

            writer.println(targetPiece);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getTargetPiece() {
        return targetPiece;
    }

    public int[] getPiecePositions() {
        return piecePositions;
    }

    public int[] getDiceSequence() {
        return diceSequence;
    }
}
