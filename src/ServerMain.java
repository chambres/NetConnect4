import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain
{
    public static void main(String[] args) {
        try
        {
            GameData gameData = new GameData();
            ServerSocket socketSocket = new ServerSocket(8002);
            Socket socketToX = socketSocket.accept();
            ObjectInputStream oisX = new ObjectInputStream(socketToX.getInputStream());
            ObjectOutputStream oosX = new ObjectOutputStream(socketToX.getOutputStream());
            System.out.println("x has connected");
            Thread t = new Thread(new ServersListener(gameData,oisX,oosX,'x'));
            t.start();
            oosX.writeObject(new CommandFromServer(CommandFromServer.CONNECTED_AS_X,""));

            Socket socketToO = socketSocket.accept();
            ObjectInputStream oisO = new ObjectInputStream(socketToO.getInputStream());
            ObjectOutputStream oosO = new ObjectOutputStream(socketToO.getOutputStream());
            t = new Thread(new ServersListener(gameData,oisO,oosO,'o'));
            t.start();
            oosO.writeObject(new CommandFromServer(CommandFromServer.CONNECTED_AS_O,""));
            oosX.writeObject(new CommandFromServer(CommandFromServer.X_TURN,""));


        }
        catch (Exception e)
        {System.out.println(e);}
    }
}
