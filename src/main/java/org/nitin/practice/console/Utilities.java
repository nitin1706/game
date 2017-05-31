package org.nitin.practice.console;

import java.util.Scanner;

/**
 * Created by nitin1706 on 5/28/17.
 * 
 * Decription and Future scope:  Utilities class holds all the methods which do the common utilities task. As of this version, 
 * user input prompts and responses are not done in this class. In scope, user input prompts and 
 * response capturing can be done in this cass.
 */
public class Utilities {
    public static void displayScore(PlayerDetails player) {
        if(player.getScore() < 0)
            player.setScore(0);
        if(player.getOpponentScore() < 0)
            player.setOpponentScore(0);
        System.out.println("Scores ==> " + player.getName() + " : "
                + player.getScore() + " | Computer : " + player.getOpponentScore());
    }

    /**
    * This method takes the input from user using Scanner object.
    * This method will be executed on the need of taking input from user.
    */
    public static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.next();
        return userInput;
    }

    /**
    * To print activities log on console. 
    * (In future scope, when a resume game will be loaded from saved state, 
    * all the activities or moves of user and computer will be displayed to user).
    * 
    * This method will also save the activity log to a file on user's system 
    * using a StringBuilder. Save game activity logs option will be 
    * available only if game is exited or quit without completing it.
    */
    public static void printAndSaveLog(String str, PlayerDetails player) {
        System.out.println(str);
        StringBuilder sb = player.getLastGameHistory();
        if(sb!= null) {
            sb = sb.append(str);
        }
        else {
            sb = new StringBuilder(str);
        }
        player.setLastGameHistory(sb);
    }
    
    public static boolean getConfirmation(String userInput) {
    	if(userInput.equalsIgnoreCase("Yes")
                || userInput.equalsIgnoreCase("yeah")
                || userInput.equalsIgnoreCase("sure")
                || userInput.equalsIgnoreCase("ok")
                || userInput.equalsIgnoreCase("y")
                || userInput.equalsIgnoreCase("agree")) {
    		return true;
    	}
		return false;
	}
}
