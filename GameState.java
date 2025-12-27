import java.util.Arrays;

public class GameState {
    public final int targetPiece;
    private int[] piecePositions;
    public final int[] removedPositions = {22};
    public final int[] diceSequence;
    public final int maxRound;

    public GameState (int targetPiece, int[] piecePositions, int[] diceSequence, int maxRound) {
        this.targetPiece = targetPiece;
        this.piecePositions = piecePositions;
        this.maxRound = maxRound;
        this.diceSequence = diceSequence;
    }

    public int[][] generatePossibleMoves(int round) {
        int diceNumber = this.diceSequence[round - 1];
        int[] moveablePieces = new int[2];
        int[][] possibleMoves = new int[16][6];
        int possibleMovesIdx = 0;
        
        int possibleMoveNumber = 0;
        if (piecePositions[diceNumber - 1] != -1) {
            moveablePieces[possibleMoveNumber++] = diceNumber;
        } else {
            for (int i = diceNumber - 1; i >= 1; i--) {
                if (piecePositions[i - 1] != -1)
                {
                    moveablePieces[possibleMoveNumber++] = i;
                    break;
                }
            }

            for (int i = diceNumber + 1; i <= 6; i++) {
                if (piecePositions[i - 1] != -1)
                {
                    moveablePieces[possibleMoveNumber++] = i;
                    break;
                }
            }
        }

        int[] DPOS = {-11, -10, -1, -9, 9, 1, 10, 11};
        for (int i = 0; i < possibleMoveNumber; i++) {
            int piece = moveablePieces[i];

            for (int dpos : DPOS) {
                int pos = piecePositions[piece - 1];
                if (dpos % 10 == -1 && pos % 10 == 0) continue;
                if (dpos % 10 == 1 && pos % 10 == 9) continue;
                if (dpos / 10 == -1 && pos / 10 == 0) continue;
                if (dpos / 10 == 1 && pos / 10 == 9) continue;

                pos += dpos;
                boolean isRemoved = false;
                for (int removedPos : removedPositions) {
                    if (pos == removedPos) {
                        isRemoved = true;
                    }
                }

                if (isRemoved) continue;
                int[] newPiecePositions = piecePositions.clone();
                for (int piece2 = 1; piece2 <= 6; piece2++) {
                    if (newPiecePositions[piece2 - 1] == pos) {
                        newPiecePositions[piece2 - 1] = -1;
                    }
                }
                newPiecePositions[piece - 1] = pos;
                if (newPiecePositions[targetPiece - 1] == -1) continue;
                possibleMoves[possibleMovesIdx++] = newPiecePositions;
            }
        }
        return Arrays.copyOf(possibleMoves, possibleMovesIdx);
    }

    public void setPiecePositions(int[] Positions) {
        this.piecePositions = Positions;
    }
    
    public int[] getPiecePositions() {
        return piecePositions;
    }
    
    public boolean isWinning() {
        return piecePositions[targetPiece - 1] == 0;
    }

}
