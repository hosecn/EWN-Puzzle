public class GameState {
    int targetPiece;
    int[] piecePositions;
    int[] removedPositions = {22};
    int[] diceSequence;
    int round;
    int diceNumber;


    public GameState (int targetPiece, int[] piecePositions, int[] diceSequence, int round) {
        this.targetPiece = targetPiece;
        this.piecePositions = piecePositions;
        this.round = round;
        this.diceSequence = diceSequence;
        this.diceNumber = this.diceSequence[this.round - 1];
    }

    public int[][] generatePossibleMoves() {
        int[] moveablePieces = new int[2];
        int[][] possibleMoves = new int[16][6];
        int possibleMovesIdx = 0;
        
        int idx = 0;
        for (int i = 0; i <= 6; i++) {
            if (diceNumber - 1 - i >= 0 && diceNumber - 1 - i <= 6) {
                if (piecePositions[diceNumber - 1 - i] != -1) {
                    moveablePieces[idx++] = i;
                }
            }
            if (diceNumber - 1 + i >= 0 && diceNumber - 1 + i <= 6) {
                if (piecePositions[diceNumber - 1 + i] != -1) {
                    moveablePieces[idx++] = i;
                }
            }
            if (moveablePieces[0] != 0) break;
        }

        int[] DPOS = {-11, -10, -1, -9, 9, 1, -1, 11};
        for (int i = 0; i < 2; i++) {
            int piece = moveablePieces[i];
            if (piece == 0) continue;

            for (int dpos : DPOS) {
                int pos = piecePositions[piece - 1];
                pos += dpos;
                boolean isRemoved = false;
                for (int removedPos : removedPositions)
                    if (pos == removedPos)
                        isRemoved = true;

                if (isRemoved) continue;
                int[] newPiecePositions = piecePositions.clone();
                for (int piece2 = 0; piece2 < 6; piece2++) {
                    if (newPiecePositions[piece2] == pos) {
                        newPiecePositions[piece2] = -1;
                    }
                }
                newPiecePositions[piece - 1] = pos;
                possibleMoves[possibleMovesIdx++] = newPiecePositions;
            }
        }
        return possibleMoves;
    }

    public void setPiecePositions(int[] Positions) {
        this.piecePositions = Positions;
    }

    public boolean isWinning() {
        return piecePositions[targetPiece - 1] == 0;
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

    public int getRound() {
        return round;
    }
}
