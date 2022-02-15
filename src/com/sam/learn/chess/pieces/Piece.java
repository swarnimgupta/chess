package com.sam.learn.chess.pieces;

import com.sam.learn.chess.Alliance;
import com.sam.learn.chess.board.Board;
import com.sam.learn.chess.board.Move;

import java.util.Collection;

public abstract class Piece {

    protected final PieceType pieceType;
    protected final int piecePosition;
    protected final Alliance pieceAlliance;
    protected final boolean isFirstMove;

    Piece(final PieceType pieceType, final int piecePosition, final Alliance pieceAlliance) {
        this.pieceType = pieceType;
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

    public PieceType getPieceType() {
        return this.pieceType;
    }

    public enum PieceType {
        BISHOP("B"), KING("K") {
            @Override
            public boolean isKing() {
                return true;
            }
        }, KNIGHT("N"), PAWN("P"), QUEEN("Q"), ROOK("R");

        private String pieceType;

        PieceType(final String pieceType) {
            this.pieceType = pieceType;
        }

        public boolean isKing() {
            return false;
        }

        @Override
        public String toString() {
            return this.pieceType;
        }
    }
}
