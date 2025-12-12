import java.util.Random;

public class RandomPlayer extends Player{
    
    @Override
    public int chooseMove(GameState state) {
        printMove(state.getPiecePositions());
        Random random = new Random();
        for (int i = 1; i <= state.maxRound; i++) {
            int[][] possibleMoves = state.generatePossibleMoves(i);
            int index = random.nextInt(possibleMoves.length);
            state.setPiecePositions(possibleMoves[index]);
            printMove(possibleMoves[index]);
            if (state.isWinning()) {
                return 1;
            }
        }
        return 0;
    }
}
