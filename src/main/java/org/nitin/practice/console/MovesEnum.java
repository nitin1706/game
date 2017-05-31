package org.nitin.practice.console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nitin1706 on 5/28/17.
 *
 * Enumeration to save all the moves in game, in case moves or related impacts needs to be changed in future,
 * only this file needs to be changed. Moves along with its impact on opponent's health can be altered as required.
 */
public enum MovesEnum {
    FACE_PUNCH ("facepunch", 60),
    KICK ("kick", 70),
    PUNCH ("punch", 90),
    ELBOW ("elbow", 50),
    ELBOW_HOOK ("elbowhook", 80);

    private String move;
    private int impact;

    MovesEnum(String move, int impact) {
        this.move = move;
        this.impact = impact;
    }

    /**
    * A static map which will store all possible moves along with their impact.
    * all the details of move, (description, impact and move) can be retrieved from this one map.
    */
    public static Map<String, MovesEnum> movesMap = new HashMap<>();
    
    /**
    * Static block to initialize map with all the moves and related details.
    */
    static {
        for(MovesEnum move : values())
            movesMap.put(move.getMove(), move);
    }

    /**
    * This is the method which will return impact of player's move on opponent's health.
    * this method will be called after every move command given by user to calculate score and health of players
    */
    public static int getImpactForMove(String move) {
        return movesMap.get(move).getImpact();
    }

    public String getMove() {
        return move;
    }

    public int getImpact() {
        return impact;
    }

    /**
    * This is the method which will return options of all possible moves in form of a map.
    */
    public static ArrayList<String> getMovesCatalogue() {
        ArrayList<String> moves = new ArrayList<>();
        for(MovesEnum move : values())
            moves.add(move.getMove());
        return moves;
    }
}
