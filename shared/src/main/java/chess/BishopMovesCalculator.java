//This is the original

package chess;

import java.util.Collection;
import java.util.HashSet;

public class BishopMovesCalculator implements PieceMoveCalculator{
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove> moves = new HashSet<>();

        addDiagonalMoves(board, position, moves);

        return moves;
    }
    private void addDiagonalMoves(ChessBoard board, ChessPosition position, Collection<ChessMove> moves) {
        int[] directions = {-1 ,1};

        for (int rowDirection : directions) {
            for (int colDirection : directions) {
                int row = position.getRow();
                int col = position.getColumn();
                while (true) {
                    row += rowDirection;
                    col += colDirection;
                    if (row < 1 || row > 8 || col < 1 || col > 8) break;
                    ChessPosition newPosition = new ChessPosition(row, col);
                    if (!addMovesIfValid(board, position, newPosition, moves)) break;
                }
            }
        }
    }

    private boolean addMovesIfValid(ChessBoard board, ChessPosition startPosition, ChessPosition endPosition, Collection<ChessMove> moves) {
        ChessPiece pieceAtEnd = board.getPiece(endPosition);
        if (pieceAtEnd != null) {
            if (pieceAtEnd.getTeamColor() != board.getPiece(startPosition).getTeamColor()) {
                moves.add(new ChessMove(startPosition, endPosition, null));//I capture a piece
            } return false;//I'm blocked
        }
        moves.add(new ChessMove(startPosition, endPosition, null));
        return true;
        //Normal move
    }
}
