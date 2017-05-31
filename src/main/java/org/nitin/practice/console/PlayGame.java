package org.nitin.practice.console;

/**
 * Created by nitin1706 on 5/28/17.
 * 
 * This class contain methods pertaining to game states. Method of this class will be 
 * executed after on start and for every move of player.
 */
public class PlayGame {
	
	static ResumeOptions resumeOptionsObj = ResumeOptions.getResumeOptionsInstance();
    
    /**
    * This method prompts user for confirmation to start the game, 
    * or resume a previously played game.
    */
    public static PlayerDetails startGame(boolean play) {
        PlayerDetails player = null;

        System.out.println("Agree to play ?  : ");
        String userConfirmation = Utilities.getUserInput();

        if(Utilities.getConfirmation(userConfirmation)) {
            play = true;
        }

        else if ((userConfirmation.equalsIgnoreCase("maybe"))) {
            System.out.println("Lets try a game, hope you will like it");
            play = true;
        }

        else {
            System.out.println("You choose not to continue. See you sometime.");
            play = false;
        }

        if(play) {
            player = checkIfExiting();

            if(player == null) {
                player = new PlayerDetails();
                System.out.println("Create a Character: ");
                player.setName(Utilities.getUserInput());
                if(player.getName().equalsIgnoreCase("quit") 
                		|| player.getName().equalsIgnoreCase("exit")) {
                    player = null;
                	System.out.println("You cannot name player 'quit' or 'exit'. Please try again with a different character name");
                }
            }
            if (player != null) {
            	System.out.println();
            	System.out.println("Great... lets start then");
                System.out.println();
                System.out.println("Welcome to");
        		
        		System.out.println(" _");
        		System.out.println("| )    \\/ . |\\ | | __");
        		System.out.println(" _               |   |");
        		System.out.println("|_) () /\\ | | \\| |__-|");
        		System.out.println();
        		System.out.println();
        		
        		System.out.println("\n\nChoose from following actions: \n" +
                		"to find options of moves, type  'options'\n" +
                		"to quit game, type 'quit'\n" +
                		"to see score at any time, type 'score'\n" +
                		"to play a move, type move name\n\n");
            }
            else {
                //System.out.println("Seems like you entered player name as something which is not allowed");
                player = null;
                play = false;
            }
            
        }
        return player;
    }


    /**
    * This method will be executed to continue playing of game. 
    * Execution of this method will capture user's move, calculate impact of user's move and score.
    */
    public boolean playGame(boolean play, PlayerDetails player) {
        try {
            if(!healthCheck(player, play)) {
                return false;
            }
            System.out.print("Enter your move : ");
            
            String playerMove = Utilities.getUserInput();
            if(playerMove.equalsIgnoreCase("options")) {
                System.out.println(MovesEnum.getMovesCatalogue());
                return play;
            }
            else if(playerMove.equalsIgnoreCase("quit") || playerMove.equalsIgnoreCase("exit")) {
                System.out.println("Sure you want to exit ? : ");
                if(Utilities.getConfirmation(Utilities.getUserInput())) {
                    play = false;
                    return play;
                }
                else
                    return play;
            }
            else if(playerMove.equalsIgnoreCase("score") ||
                    playerMove.equalsIgnoreCase("scores")) {
                //System.out.println("Scores ==> " + playerName + " : " + playerHealth + " | Computer : " + enemyHealth);
                Utilities.displayScore(player);
                return play;
            }

            //System.out.println("Log: user's input: " + playerMove);
            int userImpact = MovesEnum.getImpactForMove(playerMove);
            //System.out.println("Log: " + playerName + " move's damage : " + userImpact);

            int enemysMoveImpact = (int)(Math.random() * 100 + 10);
            System.out.println("Log: Enemy Move's damage: " + enemysMoveImpact);


            if(userImpact > enemysMoveImpact) {
                System.out.println(player.getName() + " blows enemy hard!");
                player.setOpponentScore(player.getOpponentScore() - (userImpact - enemysMoveImpact));
                Utilities.displayScore(player);
                play = healthCheck(player, play);
            }
            else if(enemysMoveImpact > userImpact) {
                System.out.println("Ohh... Enemy's move smashed " + player.getName());
                player.setScore(player.getScore() - (enemysMoveImpact - userImpact));
                Utilities.displayScore(player);
                play = healthCheck(player, play);
            }
            else if(userImpact == enemysMoveImpact) {
                System.out.println("Great move! but.... great defense as well. Move Blocked By Enemy");
            }
        }
        catch (Exception ex) {
            System.out.println("Ooops!!!!! this move is not identified.");
            //play = false;
            return play;
        }
        return play;
    }

    /**
    * This method is to check if player has enough health to continue game. 
    * In case either player's or opponent's health is Zero, the game winner 
    * message will be displayed and continue game flag will be set to false.
    */
    private boolean healthCheck(PlayerDetails playerData, boolean play) {
        if(playerData.getScore() <= 0) {
            System.out.println("Ohhh...!!! "+ playerData.getName() + " has fallen... !\n" +
                    "Computer Wins!!!");
            play = false;
        }
        else if(playerData.getOpponentScore() <= 0) {
            System.out.println("Yoho...!!!  Enemy falls to ground... !\n" +
                    playerData.getName() + " Wins!!!");
            play = false;
        }
        return play;
    }

    /** 
    * Execution of this method happens to prompt user to resume a saved game. and to fetch the record of previous saved game.
    * Future Scope:  The message needs to be added in case player name is not found in records.
    */
    private static PlayerDetails checkIfExiting() {
        PlayerDetails player = null;
        System.out.println("Would you like to resume an existing game ?  : ");
        if(Utilities.getConfirmation(Utilities.getUserInput())) {
            System.out.println("Enter the Character used for last game : ");
            String playerName = Utilities.getUserInput();

            player = resumeOptionsObj.fetchPlayerRecord(playerName);
            if(player != null) {
            	resumeOptionsObj.resumeGame(playerName);
            }
            if(player == null) {
            	System.out.println("This Character doesn't Exist in previous Games. Start a new Game.");
            }
        }
        return player;
    }
}
