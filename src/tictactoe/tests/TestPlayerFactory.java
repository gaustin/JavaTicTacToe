package tictactoe.tests;

import tictactoe.game.Board;
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
        messenger = new MockMessenger(new Board(9));
    }
    
    public void testCreate() {
        Player xPlayer = PlayerFactory.create('X', PlayerTypes.Human, messenger);
        assertNotNull(xPlayer);
        
        Player oPlayer = PlayerFactory.create('O', PlayerTypes.NaiveComputer, messenger);
        assertNotNull(oPlayer);
    }
    
    public void testGetMinimax() {
    	assertEquals(PlayerTypes.MinimaxComputer, PlayerTypes.valueOf("MinimaxComputer"));
    }
}
