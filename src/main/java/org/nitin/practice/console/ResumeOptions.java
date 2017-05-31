package org.nitin.practice.console;

import java.io.*;

/**
 * Created by nitin1706 on 5/28/17.
 */

/**
* This class if for saving and resuming option of game. Methods of this class will be called when 
* the intention of user is to save the current state of game or resume the game from a saved state
*
*/
public class ResumeOptions {
    /**
    * name of file for storing score of game.
    */
    static String propertiesFileName = "" + System.getProperty("user.home") + "/ConsoleGame.properties";
    String dataFileName = "" + System.getProperty("user.home") + "/";
    
    private ResumeOptions() {
	}
    
    private static ResumeOptions resumeOptionsObj = null;
    static {
    	resumeOptionsObj = new ResumeOptions();
    }
    
    public static ResumeOptions getResumeOptionsInstance() {
    	if(resumeOptionsObj == null) {
    		synchronized (ResumeOptions.class) {
				if(resumeOptionsObj == null) {
					resumeOptionsObj = new ResumeOptions();
				}
			}
    	}
    	return resumeOptionsObj;
    }

    /**
    * this method will prompt user to confirm if the game needs to be saved. 
    * Execution of this method will happen only when user chooses to exit / quit the game in between.
    */
    public boolean saveGameIfConfirmed(PlayerDetails playerData) {
    	if(playerData.getScore() == 0 || playerData.getOpponentScore() == 0) {
    		System.out.println("Game Over!!!");
    		return false;
    	}
        boolean gameSaved = false;
        boolean recordSaved = false;
        System.out.println("Save Game to resume later ? ");
        String saveGame = Utilities.getUserInput();
        if(Utilities.getConfirmation(saveGame)) {
            //System.out.println("Log: saving game");

            recordSaved = savePlayerRecord(playerData);
            if(recordSaved) {
            	playerData.setLastGameHistory(playerData.getLastGameHistory().append("Your Score was saved"));
                gameSaved = saveGame(playerData);
            }
            else if(!recordSaved) {
            	System.out.println("Game data couldn't be saved. Some error happened while saving game data");
            	recordSaved = false;
            }
            
            if(recordSaved && gameSaved) {
            	System.out.println("Your complete game has been saved. Resume later with same name");
            	recordSaved = true;
            }
            else if(recordSaved) {
            	System.out.println("Your score has been saved. You can resume later with same name");
            	recordSaved = true;
            }
        }
        else {
        	System.out.println("you didn't choose to save game data");
        	recordSaved = false;
        }
        return recordSaved;
    }
    
    /**
    * This method will save the record of game in main scores file from 
    * where the score will be read in case player wants to resume the game. 
    * Note: only the current state of game (name of player and score) will be saved. The logs pertaining to user 
    * will be saved in a separate file by calling a different method after saving record (this method).
    */
    private boolean savePlayerRecord(PlayerDetails playerData) {

        try {
            File file = new File(propertiesFileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile(), true));

            String content = "&" + playerData.getName() + "=" + playerData.getScore() + "=" + playerData.getOpponentScore();
            bw.write(content);
            bw.close();
            //System.out.println("record saving... Done");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
    * This method will fetch the record of game from main scores file where all the 
    * scores will be saved in case player wants to resume the game. 
    * Note: only the current state of game (name of player and score) will be fetched. The logs pertaining to user 
    * will be fetched from a separate file.
    */
    public PlayerDetails fetchPlayerRecord(String playerName) {
    	//System.out.println("Log: Entering fetchPlayerRecord() method");
        PlayerDetails player = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(propertiesFileName));
            String sCurrentLine = "";

            while ((sCurrentLine = br.readLine()) != null) {
                //System.out.println("reading file");
                String[] playersRecord = sCurrentLine.split("&");
                try {
                    for(String playerRecord : playersRecord) {
                        String[] nameScore = playerRecord.split("=");
                        if(nameScore[0].equals(playerName)) {
                            player = new PlayerDetails();
                            player.setName(playerName);
                            try {
                                player.setScore(Integer.parseInt(nameScore[1]));
                                player.setOpponentScore(Integer.parseInt(nameScore[2]));
                            }
                            catch (Exception ex) {
                                //System.out.println("Log: error in fetching name of player in records");
                                //ex.printStackTrace();
                            }
                        }
                    }
                }
                catch (Exception ex) {
                    System.out.println("Error while reading score from last game for player : " + playerName);
                }
                //System.out.println("Log: " + sCurrentLine);
            }
        } catch (IOException e) {
        	//System.out.println("Player with the name couldn't be loaded. Start a new Game.");
            //e.printStackTrace();
        }
        if(player!= null) {
            System.out.println("game resumed.");
            Utilities.displayScore(player);
        }
        return player;
    }

    /**
    * This method will save the logs of game for a user with name of player where all the 
    * logs will be saved in case player wants to resume the game. 
    * Note: only the logs of game (name of player and score) will be saved in this method. 
    */
    private boolean saveGame(PlayerDetails playerData) {
        //String fileName = "/Users/nitin1706/Desktop/" + playerData.getName() + ".txt";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(dataFileName + playerData.getName() + ".txt"));
            String content = playerData.saveData();
            bw.write(content);
            bw.close();
            //System.out.println("Log: Saving game... Done");
        } catch (IOException e) {
            e.printStackTrace();
        	System.out.println("Game couldn't be saved.");
            return false;
        }
        return true;
    }

    /**
    * This method will fetch the logs of game for a user with name of player where all the 
    * logs will be fetched in case player wants to resume the game from a previously played game.
    * Note: only the logs of game (name of player and score) will be fetched in this method. 
    */
    public PlayerDetails resumeGame(String playerName) {
        
        PlayerDetails player = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(dataFileName + playerName + ".txt"));
            String sCurrentLine = "";

            player = new PlayerDetails();
            int lineCount = 0;

            while ((sCurrentLine = br.readLine()) != null) {
                //System.out.println("Log: reading file");
                try {
                	if(lineCount == 0) {
                		String[] playerData = sCurrentLine.split(",");
                		player.setName(playerData[0]);
                        int score = Integer.parseInt(playerData[1]);
                        player.setScore(score);

                        int opponentScore = Integer.parseInt(playerData[2]);
                        player.setOpponentScore(opponentScore);

                        int totalGames = Integer.parseInt(playerData[3]);
                        player.setTotalGames(totalGames);

                        int gamesWon = Integer.parseInt(playerData[4]);
                        player.setGamesWon(gamesWon);

                        int maxScore = Integer.parseInt(playerData[5]);
                        player.setMaxScore(maxScore);
                        lineCount++;
                        continue;
                	}
                    player.setLastGameHistory(new StringBuilder(sCurrentLine));
                }
                catch (Exception ex) {
                    System.out.println("Error while reading score from last game for player : " + playerName);
                }
                if(player.getLastGameHistory() != null) {
                	System.out.println(player.getLastGameHistory());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        	//System.out.println("Last game couldn't be loaded completely");
        }
        return player;
    }

    /** 
    *  this method is just for testing of other methods in this class
    *
    */
    /*public static void main(String[] ar) {
        PlayerDetails abc = new PlayerDetails();
        abc.setName("ttk");
        abc.setScore(989);
        abc.setGamesWon(9);
        abc.setLastGameHistory(new StringBuilder("Practice makes it perfect"));
        abc.setMaxScore(99);
        abc.setTotalGames(11);

        System.out.println("Data saved ? : " + new ResumeOptions().saveGame(abc));
        System.out.println("reading: " + new ResumeOptions().resumeGame("ttk"));
    }*/
}
