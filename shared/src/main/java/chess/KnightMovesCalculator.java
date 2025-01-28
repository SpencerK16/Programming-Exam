package chess;

import java.util.Collection;
import java.util.HashSet;

public class KnightMovesCalculator implements PieceMoveCalculator{
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove> moves = new HashSet<>();

        addKnightMoves(board, position, moves);

        return moves;
    }
    private void addKnightMoves(ChessBoard board, ChessPosition position, Collection<ChessMove> moves) {
        int[][] directions = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2},
                {1, -2}, {1, 2}, {2, -1}, {2, 1}};

        for (int[] direction : directions) {
            int row = position.getRow() + direction[0];
            int col = position.getColumn() + direction[1];
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
