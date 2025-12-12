import java.util.Scanner;

public class HumanPlayer extends Player{

    @Override
    public int chooseMove(GameState state) {
        try (Scanner sc = new Scanner(System.in)){
            for (int round = 1; round <= state.maxRound; round++) {
                System.out.print("Current piece positions: ");
                for (int i = 0; i < 6; i++) {
                    System.out.print(state.getPiecePositions()[i] + " ");
                }
                System.out.println();
                System.out.println("Please choose your move: ");

                int[][] possibleMoves = state.generatePossibleMoves(round);
                
                for (int i = 0; i < possibleMoves.length; i++) {
                    System.out.print("Press " + (i + 1) + ": ");
                    for (int j = 0; j < 6; j++) {
                        System.out.print(possibleMoves[i][j] + " ");
                    }
                    System.out.println();
                }

                try {
                    int index = sc.nextInt();
                    state.setPiecePositions(possibleMoves[index - 1]);                    
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                if (state.isWinning()) {
                    return 1;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
}
