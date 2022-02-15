package com.sam.learn.chess.player;

import com.sam.learn.chess.board.Board;
import com.sam.learn.chess.board.Move;

public class MoveTransition {
    
    private final Board transitionBoard;
    private final Move move;
    private final MoveStatus moveStatus;

    public MoveTransition(final Board transitionBoard, final Move move, final MoveStatus moveStatus) {
        this.transitionBoard = transitionBoard;
        this.move = move;
        this.moveStatus = moveStatus;
    }
}
