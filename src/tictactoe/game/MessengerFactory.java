package tictactoe.game;

public class MessengerFactory {
    public static IMessenger create(MessengerTypes messengerType, Board board, IScorer scorer) {
        IMessenger messenger = null;
        
        switch(messengerType) {
        case SWING:
            messenger = new SwingMessenger(board, scorer);
            break;
        case CONSOLE:
            messenger = new ConsoleMessenger(board, scorer);
        default:
            break;
        }
        return messenger;
    }
}
