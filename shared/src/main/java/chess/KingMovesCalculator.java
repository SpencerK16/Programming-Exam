package chess;

import java.util.Collection;
import java.util.HashSet;

public class KingMovesCalculator implements PieceMoveCalculator{
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove> moves = new HashSet<>();

        addKingMoves(board, position, moves);

        return moves;
    }

    private void addKingMoves(ChessBoard board, ChessPosition position, Collection<ChessMove> moves) {
        int[] rowDirections = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colDirections = {-1, 0 ,1, -1, 1, -1, 0 ,1};

        for (int i = 0; i < rowDirections.length; i++) {
            int row = position.getRow() + rowDirections[i];
            int col = position.getColumn() + colDirections[i];
            if (row >= 1 && row <= 8 && col >= 1 && col <= 8) {
                ChessPosition newPosition = new ChessPosition(row, col);
                addMovesIfValid(board, position, newPosition, moves);
            }

        }
    }

    private void addMovesIfValid(ChessBoard board, ChessPosition startPosition, ChessPosition endPosition, Collection<ChessMove> moves) {
        ChessPiece pieceAtEnd = board.getPiece(endPosition);
        if (pieceAtEnd != null) {
            if (pieceAtEnd.getTeamColor() != board.getPiece(startPosition).getTeamColor()) {
                moves.add(new ChessMove(startPosition, endPosition, null));//I capture a piece
            } return;//I'm blocked
        }
        moves.add(new ChessMove(startPosition, endPosition, null));
        //Normal move
    }
}
