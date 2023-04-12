import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ServersListener implements Runnable{
    private ObjectInputStream ois =null;
    private ObjectOutputStream oos =null;
    private static ArrayList<ObjectOutputStream> outputStreams = new ArrayList<>();
    private char player = 'x';
    private static char turn = 'x';
    private GameData gameData;
    private static boolean xRestart, oRestart;

    public ServersListener(GameData gameData,ObjectInputStream ois, ObjectOutputStream oos, char player) {
        this.ois = ois;
        this.oos = oos;
        this.gameData  =gameData;
        this.player = player;
        outputStreams.add(oos);
    }

    public void run()
    {
        try
        {
            while(true) {
                CommandFromClient com = (CommandFromClient) (ois.readObject());
                if (!gameData.isWinner('x') && !gameData.isWinner('o')
                        && !gameData.isCat()) {
                    if (com.getCommand() == CommandFromClient.MOVE && player == turn) {
                        int c = Integer.parseInt(com.getData().substring(0, 1));
                        int r = Integer.parseInt(com.getData().substring(2));

                        System.out.println("column :"  + c);

                        if (gameData.getGrid()[r][c] == ' ') {
                            gameData.getGrid()[r][c] = player;
                            for (ObjectOutputStream os : outputStreams)
                                os.writeObject(new CommandFromServer(CommandFromServer.MOVE, com.getData() + player));
                            if (turn == 'x') {
                                turn = 'o';
                                for (ObjectOutputStream os : outputStreams)
                                    os.writeObject(new CommandFromServer(CommandFromServer.O_TURN, ""));
                            } else {
                                turn = 'x';
                                for (ObjectOutputStream os : outputStreams)
                                    os.writeObject(new CommandFromServer(CommandFromServer.X_TURN, ""));
                            }

                            if (gameData.isWinner('x'))
                                for (ObjectOutputStream os : outputStreams)
                                    os.writeObject(new CommandFromServer(CommandFromServer.X_WINS, ""));
                            if (gameData.isWinner('o'))
                                for (ObjectOutputStream os : outputStreams)
                                    os.writeObject(new CommandFromServer(CommandFromServer.O_WINS, ""));
                            if (gameData.isCat())
                                for (ObjectOutputStream os : outputStreams)
                                    os.writeObject(new CommandFromServer(CommandFromServer.TIE, ""));
                        }
                    }
                } else if (com.getCommand() == CommandFromClient.RESTART) {
                    if (player == 'x') {
                        xRestart = true;
                    } else {
                        oRestart = true;
                    }

                    if (xRestart && oRestart) {
                        System.out.println("reset");
                        for (ObjectOutputStream os : outputStreams)
                            os.writeObject(new CommandFromServer(CommandFromServer.RESTART, ""));
                        gameData.reset();
                        turn = 'x';
                        for (ObjectOutputStream os : outputStreams)
                            os.writeObject(new CommandFromServer(CommandFromServer.X_TURN, ""));

                        oRestart = xRestart = false;
                    }

                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Error");
            System.exit(0);
        }
    }
}
