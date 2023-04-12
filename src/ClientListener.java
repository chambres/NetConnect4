import java.io.ObjectInputStream;

public class ClientListener implements Runnable{
    private ObjectInputStream ois =null;
    private TTTFrame frame = null;

    public ClientListener(ObjectInputStream ois, TTTFrame frame) {
        this.ois = ois;
        this.frame = frame;
    }

    public void run()
    {
        try
        {
            while(true) {
                CommandFromServer com = (CommandFromServer) (ois.readObject());

                if (com.getCommand() == CommandFromServer.CONNECTED_AS_X) {
                    frame.setText("Waiting for O to connect");
                    frame.setPlayer('x');
                    frame.setTurn('x');
                }
                if (com.getCommand() == CommandFromServer.CONNECTED_AS_O) {
                    frame.setText("X's Turn");
                    frame.setPlayer('o');
                    frame.setTurn('x');
                }
                if (com.getCommand() == CommandFromServer.X_TURN)
                {
                    frame.setTurn('x');
                    if(frame.getPlayer()=='x')
                        frame.setText("It is your turn");
                    else
                        frame.setText("X's Turn");
                }
                if (com.getCommand() == CommandFromServer.O_TURN)
                {
                    frame.setTurn('x');
                    if(frame.getPlayer()=='x')
                        frame.setText("O's Turn");
                    else
                        frame.setText("It is your turn");
                }
                if (com.getCommand() == CommandFromServer.MOVE)
                {
                    int c = Integer.parseInt(com.getData().substring(0,1));
                    int r = Integer.parseInt(com.getData().substring(2,3));


                    char letter = com.getData().charAt(3);

                    System.out.println("hello" + c + letter + r);
                    frame.makeMove(c,r,letter);
                }
                if (com.getCommand() == CommandFromServer.X_WINS) {
                    frame.setText("X WINS!!! (R to restart)");

                }
                if (com.getCommand() == CommandFromServer.O_WINS) {
                    frame.setText("O WINS!!! (R to restart)");

                }
                if (com.getCommand() == CommandFromServer.TIE) {
                    frame.setText("CAT GAME. (R to restart)");
                }
                if(com.getCommand() == CommandFromServer.RESTART)
                    frame.reset();
            }
        }
        catch(Exception e)
        {
            System.out.println("Error");
            System.exit(0);
        }
    }
}
