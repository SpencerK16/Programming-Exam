//This is the original
package chess;

import java.util.Collection;
import java.util.HashSet;


public class PawnMovesCalculator implements PieceMoveCalculator{
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        Collection<ChessMove> moves = new HashSet<>();
        ChessPiece pawn = board.getPiece(position);
        int direction = (pawn.getTeamColor() == ChessGame.TeamColor.WHITE) ? 1 : -1;
        addForwardMoves(board, position, direction, moves);
        addCaptureMoves(board, position, direction, moves);

        return moves;
    }

    private void addForwardMoves(ChessBoard board, ChessPosition position, int direction, Collection<ChessMove> moves) {
        int startRow = position.getRow();
        int startCol = position.getColumn();
        // Move on square forward
        int row = startRow + direction;
        if (row <= 8 && row >= 1) {
            ChessPosition newPosition = new ChessPosition(row, startCol);
            if (board.getPiece(newPosition) == null) {
                if (isPromotionalRow(row, direction)) {
                    addPromotionalMoves(position, newPosition, moves);
                }
                moves.add(new ChessMove(position, newPosition, null));
            }
            if ((row == 2 && direction == 1) || (row == 7 && direction == -1)) {
                row = startRow + 2 *direction;
                newPosition = new ChessPosition(row, startCol);
                moves.add(new ChessMove(position, newPosition, null));
            }
        }

    }

    private void addCaptureMoves(ChessBoard board, ChessPosition position, int direction, Collection<ChessMove> moves) {
        int startRow = position.getRow();
        int startCol = position.getColumn();
        int[] captureDirections = {-1, 1};

        int row = startRow + direction;
        for (int captureDirection : captureDirections) {
            int col = startCol + captureDirection;
            if (row <= 8 && row >= 1 && col <= 8 && col >= 1) {
                ChessPosition newPosition = new ChessPosition(row, col);
                if (board.getPiece(newPosition) != null){
                    if(board.getPiece(newPosition).getTeamColor() != board.getPiece(position).getTeamColor()){
                        if(isPromotionalRow(row, direction)){
                            addPromotionalMoves(position, newPosition, moves);
                        }
                        moves.add(new ChessMove(position, newPosition, null));
                    }
                }

            }
        }


    }

    private boolean isPromotionalRow(int row, int direction){
        return ((direction ==1 && row == 8) || (direction == -1 && row == 1));
    }

    private void addPromotionalMoves(ChessPosition startPosition, ChessPosition endPosition, Collection<ChessMove> moves){
        moves.add(new ChessMove(startPosition, endPosition, ChessPiece.PieceType.QUEEN));
        moves.add(new ChessMove(startPosition, endPosition, ChessPiece.PieceType.BISHOP));
        moves.add(new ChessMove(startPosition, endPosition, ChessPiece.PieceType.KNIGHT));
        moves.add(new ChessMove(startPosition, endPosition, ChessPiece.PieceType.ROOK));
    }
}
