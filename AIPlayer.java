
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

        public Node(int round, int[] piecePositions, int distance) {
            this.round = round;
            this.piecePositions = piecePositions;
            this.distance = distance;
        }
    }

    @Override
    public int chooseMove(GameState state) {
        // TODO: This needs to be updated.
        int round = state.getRound();
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {
            return Integer.compare(a.distance, b.distance);
        });

        int distance = calculateDistance(round, state.piecePositions, state.diceSequence);
        pq.add(new Node(round, state.piecePositions, distance));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            state.setPiecePositions(node.piecePositions);
            int[][] possibleMoves = state.generatePossibleMoves();
            for (int[] possibleMove : possibleMoves) {
                distance = calculateDistance(node.round, possibleMove, state.diceSequence);
                pq.add(new Node(node.round, possibleMove, distance));
            }
        }
        this.printMove(state);
        return 0;
    }

    private int calculateDistance(int round, int[] piecePositions, int[] diceSequence) {
        // TODO: This needs to be updated.
        int[] diceCount = new int[6];
        for (int i = round - 1; i < diceSequence.length; i++) {
            diceCount[diceSequence[i]]++;
        }
        return round;
    }
}
