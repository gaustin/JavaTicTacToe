package tictactoe.tests;

import tictactoe.game.IMessenger;
import tictactoe.mocks.MockMessenger;
import tictactoe.players.Player;
import tictactoe.players.PlayerFactory;
import tictactoe.players.PlayerTypes;
import junit.framework.TestCase;

public class TestPlayerFactory extends TestCase {
	PlayerFactory factory;
	IMessenger messenger;
	public void setUp() {
		factory = new PlayerFactory();
		messenger = new MockMessenger();
	}
	
	public void testCreate() {
		Player xPlayer = factory.create('X', PlayerTypes.Human, messenger);
		assertNotNull(xPlayer);
		
		Player oPlayer = factory.create('O', PlayerTypes.NaiveComputer, messenger);
		assertNotNull(oPlayer);
	}
}
