import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain
{
    public static void main(String[] args)
    {
        GameData gameData = new GameData();


        Scanner keyboard = new Scanner(System.in);
        //System.out.print("Enter the ip address of the server: ");
        //String ip = keyboard.nextLine();
        String ip = "127.0.0.1";

        try {
            Socket socket = new Socket(ip,8002);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            TTTFrame frame = new TTTFrame(gameData,oos);
            Thread t = new Thread(new ClientListener(ois,frame));
            t.start();
            frame.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
