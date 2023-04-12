import java.io.ObjectOutputStream;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        System.out.println("hi");
        // create game data
        GameData gameData = new GameData();
        
        // create output stream
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(System.out);
        } catch (Exception ex) {}
        
        // create frame
        TTTFrame frame = new TTTFrame(gameData, oos);
        frame.setVisible(true);
    }
}