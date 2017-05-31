package game1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.nitin.practice.console.GameApp;
import org.nitin.practice.console.PlayGame;
import org.nitin.practice.console.PlayerDetails;

public class JUnitTest_1 {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		PlayGame playGameObj = new PlayGame();
		PlayerDetails player = playGameObj.startGame(true);
		System.out.println("Player details : " + player);
	}

}
