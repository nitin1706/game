package org.nitin.practice.console;

/**
 * Created by nitin1706 on 5/28/17.
 *
 * This is the main class of game which will start execution of game.
 */
public class GameApp {
    
    
    public void playGame() {
    	PlayerDetails player = PlayGame.startGame(true);
        if(player == null)
            return;

        PlayGame gameConsole = new PlayGame();

        boolean play = true;
        while(play) {
            //System.out.print("Starting to play : " + player);
            //Utilities.printAndSaveLog("Starting to play : " + player, player);
            play = gameConsole.playGame(play, player);
        }

        ResumeOptions resumeOptionsObj = ResumeOptions.getResumeOptionsInstance();
        resumeOptionsObj.saveGameIfConfirmed(player);
        //boolean gameSaved = resumeOptionsObj.saveGameIfConfirmed(player);
        //Utilities.printAndSaveLog("Your game saved ? : " + gameSaved, player);  // log file cannot be here
        //System.out.print("Your game saved ? : " + gameSaved);
        System.out.println("Exiting game now...");
    }
}
