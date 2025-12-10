import java.util.ArrayList;
import java.util.Scanner;

public class GameMain {

    public static void main(String[] args) {
        // ============================================================
        // TODO: Implement the main() function
        // ------------------------------------------------------------
        // This is the main entry point of the program.
        //
        // The function should perform the following tasks:
        //
        
        System.out.println("Please choose a game mode.");
        System.out.println("Human Player : Press 1");
        System.out.println("Random Player : Press 2");
        System.out.println("AI Player : Press 3");
        Player player = null;

        int playerNumber;
        try (Scanner sc = new Scanner(System.in)) {
            playerNumber = sc.nextInt();
        }

        switch (playerNumber) {
            case 1 -> player = new HumanPlayer();
            case 2 -> player = new RandomPlayer();
            case 3 -> player = new AIPlayer();
            default -> throw new AssertionError();
        }

        System.out.println("Please select a level.");
        System.out.println("Press 1 / 2 / 3 / 4.");

        int levelNumber;
        try (Scanner sc = new Scanner(System.in)) {
            levelNumber = sc.nextInt();
        }
        String filePath = "./TestCases/level" + (levelNumber - 1) + ".txt";
        GameLoader loader = new GameLoader(filePath);
        int targetPiece = loader.getTargetPiece();
        int[] piecePositions = loader.getPiecePositions();
        int[] diceSequence = loader.getDiceSequence();

        loader.printGameDetails();

        GameState state = new GameState(targetPiece, piecePositions, diceSequence, 1);

        int result = player.chooseMove(state);
        // 4. Call the chooseMove() function of the player
        //    to perform their move based on the current game state.
        //
        // 5. Display the result of the game

        // You may also add any other helper functions, variables,
        // and constructors needed for your implementation.
        
    }
}

