package com.sam.learn.chess.pieces;

import com.sam.learn.chess.Alliance;
import com.sam.learn.chess.board.Board;
import com.sam.learn.chess.board.BoardUtils;
import com.sam.learn.chess.board.Move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.sam.learn.chess.board.Move.AttackMove;
import static com.sam.learn.chess.board.Move.LegalMove;

public class Pawn extends Piece {

    private final static int[] CANDIDATE_MOVES_COORDINATE = {8, 16};

    public Pawn(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int currentCandidateOffset : CANDIDATE_MOVES_COORDINATE) {
            final int candidateDestinationCoordinate = this.piecePosition + (this.getPieceAlliance().getDirection() * currentCandidateOffset);
            if (!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                continue;
            }
            if (currentCandidateOffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                legalMoves.add(new LegalMove(board, this, candidateDestinationCoordinate));
            } else if (currentCandidateOffset == 16 && this.isFirstMove()
                    && (BoardUtils.SECOND_ROW[this.piecePosition] && Alliance.BLACK.equals(this.getPieceAlliance()))
                    || (BoardUtils.SEVENTH_ROW[this.piecePosition] && Alliance.WHITE.equals(this.getPieceAlliance()))) {
                final int behindCandidateDestinationCoordinate = this.piecePosition + (this.pieceAlliance.getDirection() * 8);
                if (!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied()
                        && !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    legalMoves.add(new LegalMove(board, this, candidateDestinationCoordinate));
                }
            } else if (currentCandidateOffset == 7
                    && !((BoardUtils.EIGHTH_COLUMN[this.piecePosition] && Alliance.WHITE.equals(this.getPieceAlliance()))
                    || (BoardUtils.FIRST_COLUMN[this.piecePosition] && Alliance.BLACK.equals(this.getPieceAlliance())))) {
                if (board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                    if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
                        legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceOnCandidate));
                    }
                }
            } else if (currentCandidateOffset == 9
                    && !((BoardUtils.EIGHTH_COLUMN[this.piecePosition] && Alliance.WHITE.equals(this.getPieceAlliance()))
                    || (BoardUtils.FIRST_COLUMN[this.piecePosition] && Alliance.BLACK.equals(this.getPieceAlliance())))) {
                if (board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                    if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
                        legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceOnCandidate));
                    }
                }
            }
        }
        return Collections.unmodifiableList(legalMoves);
    }
}
