package com.sam.learn.chess.board;

import com.sam.learn.chess.Alliance;
import com.sam.learn.chess.pieces.Piece;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Board {

    private final List<Tile> gameBoard;

    public Board(BoardBuilder boardBuilder) {
        this.gameBoard = createGameBoard(boardBuilder);
    }

    private static List<Tile> createGameBoard(BoardBuilder boardBuilder) {
        final Tile[] tiles = new Tile[BoardUtils.NUM_TILES];
        for (int i = 0; i < BoardUtils.NUM_TILES; i++) {
            tiles[i] = Tile.createTile(i, boardBuilder.boardConfig.get(i));
        }
        return Collections.unmodifiableList(Arrays.asList(tiles));
    }

    public static Board createStandardBoard() {
        return null;
    }

    public Tile getTile(final int coordinate) {
        return null;
    }

    public static class BoardBuilder {

        Map<Integer, Piece> boardConfig;
        Alliance nextMoveMaker;

        public BoardBuilder() {
        }

        public BoardBuilder setPiece(final Piece piece) {
            this.boardConfig.put(piece.getPiecePosition(), piece);
            return this;
        }

        public BoardBuilder setMoverMaker(final Alliance nextMoveMaker) {
            this.nextMoveMaker = nextMoveMaker;
            return this;
        }

        public Board build() {
            return new Board(this);
        }
    }
}
