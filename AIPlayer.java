
import java.util.PriorityQueue;

public class AIPlayer extends Player{
    // ============================================================
    // TODO: Implement chooseMove()
    // ------------------------------------------------------------
    // This method defines how the AI selects its next move.
    //
    // You are encouraged to implement any suitable AI or algorithmic
    // approach to decide the move, including rule-based strategy,
    // dynamic programming, greedy algorithm, and search-based algorithm
    //
    // Hardcoded solution (manually entering the correct moves)
    // is strictly not allowed.
    //
    // You may decide on the return type, parameters, and logic.
    // ============================================================

    // You may also add any other helper functions, variables,
    // and constructors needed for your implementation.

    class Node {
        int round;
        int[] piecePositions;
        int distance;
        Node previousNode;

        public Node(int round, int[] piecePositions, int distance, Node previousNode) {
            this.round = round;
            this.piecePositions = piecePositions;
            this.distance = distance;
            this.previousNode = previousNode;
        }
    }

    @Override
    public int chooseMove(GameState state) {
        int round = state.getRound();
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {
            return Integer.compare(a.distance, b.distance);
        });

        int distance = calculateDistance(round, state.piecePositions, state.diceSequence);
        pq.add(new Node(round, state.piecePositions, distance, null));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (node.piecePositions[state.targetPiece - 1] == -1) continue;
            
            state.setPiecePositions(node.piecePositions);
            state.setRound(node.round);
            
            // for (int i = 0; i < 6; i++) {
            //     System.out.print(node.piecePositions[i] + " ");
            // }
            // System.out.println();

            if (state.isWinning()) {
                int[][] chosenMoves = new int[30][6];
                int chosenMovesIdx = 0;
                do {
                    chosenMoves[chosenMovesIdx++] = node.piecePositions.clone();
                    node = node.previousNode;
                } while(node != null);
                for (int i = chosenMovesIdx - 1; i >= 0; i--) {
                    printMove(chosenMoves[i]);
                }
                return 1;
            }


            int[][] possibleMoves = state.generatePossibleMoves();
            for (int[] possibleMove : possibleMoves) {
                if (possibleMove[0] == 0 && possibleMove[1] == 0 && possibleMove[2] == 0 && possibleMove[3] == 0) continue;
                distance = calculateDistance(node.round + 1, possibleMove, state.diceSequence);
                pq.add(new Node(node.round + 1, possibleMove, distance, node));
            }
        }
        return 0;
    }

    private int calculateDistance(int round, int[] piecePositions, int[] diceSequence) {
        // TODO: This needs to be updated.
        int[] diceCount = new int[6];
        for (int i = round; i <= diceSequence.length; i++) {
            diceCount[diceSequence[i - 1] - 1]++;
        }
        return round;
    }
}
