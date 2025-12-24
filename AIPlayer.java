
import java.util.PriorityQueue;

public class AIPlayer extends Player{

    class Node {
        int round;
        int[] piecePositions;
        int totalDistance;
        Node previousNode;

        public Node(int round, int[] piecePositions, int totalDistance, Node previousNode) {
            this.round = round;
            this.piecePositions = piecePositions;
            this.totalDistance = totalDistance;
            this.previousNode = previousNode;
        }
    }

    @Override
    public int chooseMove(GameState state) {
        int searchNodeNum = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {
            return Integer.compare(a.totalDistance, b.totalDistance);
        });

        pq.add(new Node(0, state.getPiecePositions(), 0, null));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            searchNodeNum++;
            state.setPiecePositions(node.piecePositions);
            
            if (state.isWinning()) {
                System.out.println("The solution is found after searching " + searchNodeNum + " positions.");
                int[][] chosenMoves = new int[30][6];
                int chosenMovesIdx = 0;
                while(node != null) {
                    chosenMoves[chosenMovesIdx++] = node.piecePositions;
                    node = node.previousNode;
                }
                for (int i = chosenMovesIdx - 1; i >= 0; i--) {
                    printMove(chosenMoves[i]);
                }
                return 1;
            }

            int round = node.round + 1;
            if (round > state.maxRound) continue;
            int[][] possibleMoves = state.generatePossibleMoves(round);
            for (int[] possibleMove : possibleMoves) {
                int totalDistance = calculateDistance(round, possibleMove, state.diceSequence, state.targetPiece, state.maxRound);
                if (totalDistance >= 1e8) continue;
                pq.add(new Node(round, possibleMove, totalDistance, node));
            }
        }
        return 0;
    }

    private int calculateDistance(int round, int[] piecePositions, int[] diceSequence, int targetPiece, int maxRound) {
        int[] diceCount = new int[6];
        for (int i = round; i <= diceSequence.length; i++) {
            diceCount[diceSequence[i - 1] - 1]++;
        }
        int targetPiecePosition = piecePositions[targetPiece - 1];
        int targetPieceDistance = Math.max(targetPiecePosition % 10, targetPiecePosition / 10);

        int pieceWeightCount = 0;
        for (int i = 1; i <= 6; i++) {
            if (piecePositions[i - 1] != -1) {
                pieceWeightCount += 6 - Math.abs(targetPiece - i);
            }
        }

        int pieceDistance = 0;
        for (int i = 0; i < 6; i++) {
            if (piecePositions[i] == -1) continue;
            for (int j = 0; j < 6; j++)
            {
                if (j == i) continue;
                if (piecePositions[j] == -1) continue;

                pieceDistance += Math.max(
                    Math.abs(piecePositions[i] % 10 - piecePositions[j] % 10),
                    Math.abs(piecePositions[i] / 10 - piecePositions[j] / 10)
                    );
            }
        }
        pieceDistance /= 2;

        int solvable = 0;
        if (targetPieceDistance + round > maxRound) solvable += 1e8;

        return round + pieceWeightCount + targetPieceDistance * 3 + pieceDistance + solvable;
    }
}