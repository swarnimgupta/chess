package com.sam.learn.chess.pieces;

import com.sam.learn.chess.Alliance;
import com.sam.learn.chess.board.Board;
import com.sam.learn.chess.board.Move;

import java.util.Collection;

public abstract class Piece {

    protected final int piecePosition;
    protected final Alliance pieceAlliance;
    protected final boolean isFirstMove;

    Piece(final int piecePosition, final Alliance pieceAlliance) {
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
        this.isFirstMove = false;
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board);

    public Alliance getPieceAlliance() {
        return this.pieceAlliance;
    }

    public Integer getPiecePosition() {
        return this.piecePosition;
    }

    public boolean isFirstMove() {
        return this.isFirstMove;
    }

    public enum PieceType {
        BISHOP("B"), KING("K"), KNIGHT("N"), PAWN("P"), QUEEN("Q"), ROOK("R");

        private String pieceType;

        PieceType(final String pieceType) {
            this.pieceType = pieceType;
        }

        @Override
        public String toString() {
            return this.pieceType;
        }
    }
}
