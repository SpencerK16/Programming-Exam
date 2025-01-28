package chess;

import java.util.Collection;
import java.util.HashSet;

public class RookMovesCalculator implements PieceMoveCalculator{

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove> moves = new HashSet<>();

        addVerticalAndHorizontalMoves(board, position, moves);

        return moves;
    }

    private void addVerticalAndHorizontalMoves(ChessBoard board, ChessPosition position, Collection<ChessMove> moves) {
        int[] directions = {-1 ,1};
        //Adds in the Verticals
        for (int direction : directions) {
            int row = position.getRow();
            while (true) {
                row += direction;
                if (row < 1 || row > 8) break;
                ChessPosition newPosition = new ChessPosition(row, position.getColumn());
                if (!addMovesIfValid(board, position, newPosition, moves)) break;
            }
        }

        //Adds in the Horizontals
        for (int direction : directions) {
            int col = position.getColumn();
            while (true) {
                col += direction;
                if (col < 1 || col > 8) break;
                ChessPosition newPosition = new ChessPosition(position.getRow(), col);
                if (!addMovesIfValid(board, position, newPosition, moves)) break;
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
