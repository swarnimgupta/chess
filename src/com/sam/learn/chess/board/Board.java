package com.sam.learn.chess.board;

import com.sam.learn.chess.Alliance;
import com.sam.learn.chess.pieces.*;
import com.sam.learn.chess.player.BlackPlayer;
import com.sam.learn.chess.player.Player;
import com.sam.learn.chess.player.WhitePlayer;

import java.util.*;

public class Board {

    private final List<Tile> gameBoard;
    private final Collection<Piece> whitePieces;
    private final Collection<Piece> blackPieces;
    private final WhitePlayer whitePlayer;
    private final BlackPlayer blackPlayer;

    public Board(BoardBuilder boardBuilder) {
        this.gameBoard = createGameBoard(boardBuilder);
        this.whitePieces = calculateActivePieces(this.gameBoard, Alliance.WHITE);
        this.blackPieces = calculateActivePieces(this.gameBoard, Alliance.BLACK);
        final Collection<Move> whiteStandardMoves = calculateLegalMoves(this.whitePieces);
        final Collection<Move> blackStandardMoves = calculateLegalMoves(this.blackPieces);
        this.whitePlayer = new WhitePlayer(this, whiteStandardMoves, blackStandardMoves);
        this.blackPlayer = new BlackPlayer(this, blackStandardMoves, whiteStandardMoves);
    }

    private static List<Tile> createGameBoard(BoardBuilder boardBuilder) {
        final List<Tile> tiles = new ArrayList<>(BoardUtils.NUM_TILES);
        for (int i = 0; i < BoardUtils.NUM_TILES; i++) {
            tiles.add(Tile.createTile(i, boardBuilder.boardConfig.get(i)));
        }
        return Collections.unmodifiableList(tiles);
    }

    public static Board createStandardBoard() {
        final BoardBuilder boardBuilder = new BoardBuilder();
        //Black Pieces
        boardBuilder.setPiece(new Rook(0, Alliance.BLACK));
        boardBuilder.setPiece(new Knight(1, Alliance.BLACK));
        boardBuilder.setPiece(new Bishop(2, Alliance.BLACK));
        boardBuilder.setPiece(new Queen(3, Alliance.BLACK));
        boardBuilder.setPiece(new King(4, Alliance.BLACK));
        boardBuilder.setPiece(new Bishop(5, Alliance.BLACK));
        boardBuilder.setPiece(new Knight(6, Alliance.BLACK));
        boardBuilder.setPiece(new Rook(7, Alliance.BLACK));
        boardBuilder.setPiece(new Pawn(8, Alliance.BLACK));
        boardBuilder.setPiece(new Pawn(9, Alliance.BLACK));
        boardBuilder.setPiece(new Pawn(10, Alliance.BLACK));
        boardBuilder.setPiece(new Pawn(11, Alliance.BLACK));
        boardBuilder.setPiece(new Pawn(12, Alliance.BLACK));
        boardBuilder.setPiece(new Pawn(13, Alliance.BLACK));
        boardBuilder.setPiece(new Pawn(14, Alliance.BLACK));
        boardBuilder.setPiece(new Pawn(15, Alliance.BLACK));
        //White Pieces
        boardBuilder.setPiece(new Pawn(48, Alliance.WHITE));
        boardBuilder.setPiece(new Pawn(49, Alliance.WHITE));
        boardBuilder.setPiece(new Pawn(50, Alliance.WHITE));
        boardBuilder.setPiece(new Pawn(51, Alliance.WHITE));
        boardBuilder.setPiece(new Pawn(52, Alliance.WHITE));
        boardBuilder.setPiece(new Pawn(53, Alliance.WHITE));
        boardBuilder.setPiece(new Pawn(54, Alliance.WHITE));
        boardBuilder.setPiece(new Pawn(55, Alliance.WHITE));
        boardBuilder.setPiece(new Rook(56, Alliance.WHITE));
        boardBuilder.setPiece(new Knight(57, Alliance.WHITE));
        boardBuilder.setPiece(new Bishop(58, Alliance.WHITE));
        boardBuilder.setPiece(new Queen(59, Alliance.WHITE));
        boardBuilder.setPiece(new King(60, Alliance.WHITE));
        boardBuilder.setPiece(new Bishop(61, Alliance.WHITE));
        boardBuilder.setPiece(new Knight(62, Alliance.WHITE));
        boardBuilder.setPiece(new Rook(63, Alliance.WHITE));
        //White to move
        boardBuilder.setMoverMaker(Alliance.WHITE);
        return boardBuilder.build();
    }

    private static Collection<Piece> calculateActivePieces(final List<Tile> gameBoard, final Alliance alliance) {
        final List<Piece> activePieces = new ArrayList<>();
        for (final Tile tile : gameBoard) {
            if (tile.isTileOccupied()) {
                if (tile.getPiece().getPieceAlliance() == alliance) {
                    activePieces.add(tile.getPiece());
                }
            }
        }
        return Collections.unmodifiableList(activePieces);
    }

    public Collection<Piece> getWhitePieces() {
        return this.whitePieces;
    }

    public Collection<Piece> getBlackPieces() {
        return this.blackPieces;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < BoardUtils.NUM_TILES; i++) {
            final String tileText = this.gameBoard.get(i).toString();
            builder.append(String.format("%3s", tileText));
            if ((i + 1) % BoardUtils.NUM_TILES_PER_ROW == 0) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    private Collection<Move> calculateLegalMoves(Collection<Piece> pieces) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final Piece piece : pieces) {
            legalMoves.addAll(piece.calculateLegalMoves(this));
        }
        return Collections.unmodifiableList(legalMoves);
    }

    public Tile getTile(final int coordinate) {
        return gameBoard.get(coordinate);
    }

    public Player whitePlayer() {
        return this.whitePlayer;
    }

    public Player blackPlayer() {
        return this.blackPlayer;
    }

    public static class BoardBuilder {

        Map<Integer, Piece> boardConfig;
        Alliance nextMoveMaker;

        public BoardBuilder() {
            this.boardConfig = new HashMap<>();
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
