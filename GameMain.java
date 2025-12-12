import java.util.Scanner;

public class GameMain {

    public static void main(String[] args) {
        System.out.println("Please choose a game mode.");
        System.out.println("Human Player : Press 1.");
        System.out.println("Random Player : Press 2.");
        System.out.println("AI Player : Press 3.");
        Player player;

        int playerNumber, levelNumber, maxRound;
        String playerName;
        try (Scanner sc = new Scanner(System.in)) {
            playerNumber = sc.nextInt();
            switch (playerNumber) {
                case 1 -> {
                    player = new HumanPlayer();
                    System.out.println("Please enter player name.");
                    playerName = sc.next();
                }
                case 2 -> {player = new RandomPlayer(); playerName = "Random Player";}
                case 3 -> {player = new AIPlayer(); playerName = "AI Player";}
                default -> throw new AssertionError();
            }
            System.out.println("Please select a level.");
            System.out.println("Press 1 / 2 / 3 / 4.");

            levelNumber = sc.nextInt();
        
            int maxRounds[] = {6, 11, 10, 15};
            maxRound = maxRounds[levelNumber - 1];
            String filePath = "./TestCases/level" + levelNumber + ".txt";
            GameLoader loader = new GameLoader(filePath);
            int targetPiece = loader.getTargetPiece();
            int[] piecePositions = loader.getPiecePositions();
            int[] diceSequence = loader.getDiceSequence();

            loader.printGameDetails(playerName);

            GameState state = new GameState(targetPiece, piecePositions, diceSequence, maxRound);

            int result = player.chooseMove(state);
            
            if (result == 1) {
                System.out.println("The puzzle is solved.");
            } else {
                System.out.println("The player failed to solve the puzzle.");
            }
        }
    }
}

