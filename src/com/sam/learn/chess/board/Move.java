package com.sam.learn.chess.board;

import com.sam.learn.chess.pieces.Piece;

public abstract class Move {

    private final Board board;
    private final Piece piece;
    private final int destinationCoordinate;

    private Move(final Board board, final Piece piece, final int destinationCoordinate) {
        this.board = board;
        this.piece = piece;
        this.destinationCoordinate = destinationCoordinate;
    }

    public static final class LegalMove extends Move {

        public LegalMove(final Board board, final Piece piece, final int destinationCoordinate) {
            super(board, piece, destinationCoordinate);
        }
    }

    public static final class AttackMove extends Move {

        private final Piece attackedPiece;

        public AttackMove(final Board board, final Piece piece, final int destinationCoordinate, final Piece attackedPiece) {
            super(board, piece, destinationCoordinate);
            this.attackedPiece = attackedPiece;
        }
    }
}
